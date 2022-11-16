package org.yaksha.prashna.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.yaksha.prashna.cache.models.CachedGroup;
import org.yaksha.prashna.util.CommonUtils;
import org.yaksha.prashna.websockets.stomp.models.GroupMessage;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class GroupService {
    private final UserService userService;
    private final CacheManager cacheManager;
    private Cache groupCache;
    private ConcurrentHashMap<String, Object> groupsConcurrentLocks = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        groupCache = cacheManager.getCache("groups");
    }

    @CachePut(cacheNames = "groups", key = "#result.groupId")
    public CachedGroup createGroup(@NonNull GroupMessage groupMessage) {
        String newGroupId = CommonUtils.randomStringGenerate(4);
        log.debug("Creating a group with name :{}", newGroupId);

        CachedGroup cachedGroup = CachedGroup
                .builder()
                .groupId(newGroupId)
                .users(new ArrayList<>())
                .build();

        cachedGroup.getUsers().add(groupMessage.getFromUser());
        log.debug("Created group with name:{} by user :{}", newGroupId, groupMessage.getFromUser());
        groupsConcurrentLocks.putIfAbsent(newGroupId, new Object());
        return cachedGroup;
    }

    public CachedGroup getGroupById(@NonNull String groupId) {
        return groupCache.get(groupId, CachedGroup.class);
    }

    public void joinGroup(@NonNull GroupMessage groupMessage) {


        Object lockObject = groupsConcurrentLocks.get(groupMessage.getGroupId());
        if (lockObject == null) {
            log.error("Can not lock the group for update");
        }
        synchronized (lockObject) {
            log.debug("Synchronized on groupId: {}", groupMessage.getGroupId());
            CachedGroup cachedGroup = groupCache.get(groupMessage.getGroupId(), CachedGroup.class);
            cachedGroup.getUsers().add(groupMessage.getFromUser());
            log.debug("Added user:{} to group Id: {}", groupMessage.getFromUser(), groupMessage.getGroupId());
        }

        userService.createUser(groupMessage.getFromUser());
    }

}
