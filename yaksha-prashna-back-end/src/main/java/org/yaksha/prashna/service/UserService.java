package org.yaksha.prashna.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.yaksha.prashna.cache.models.CachedUser;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final CacheManager cacheManager;
    private Cache userCache;

    @PostConstruct
    public void init() {
        userCache = cacheManager.getCache("users");
    }

    @CachePut(cacheNames = "users" ,key = "#result.userName")
    public CachedUser createUser(String userName) {
        log.debug("Creating a user object for user name:{}", userName);
        return CachedUser.builder().userName(userName).build();
    }
    public CachedUser getUserById(String userName){
        log.debug("Getting user by user name:{}",userName);
        return userCache.get(userName,CachedUser.class);
    }
}
