package org.yaksha.prashna.controllers.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaksha.prashna.cache.models.CachedGroup;
import org.yaksha.prashna.service.GroupService;
import org.yaksha.prashna.websockets.stomp.models.GroupEventType;
import org.yaksha.prashna.websockets.stomp.models.GroupMessage;

@Slf4j
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupWebController {

    private final GroupService groupService;

    @PostMapping
    @CrossOrigin(origins = "*")
    public GroupMessage createGroup(@RequestBody GroupMessage groupMessage) {

        if (groupMessage.getEventType() != GroupEventType.CREATED ) {
            log.warn("Invalid event type {}", groupMessage.getEventType());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }

        CachedGroup cachedGroup = groupService.createGroup(groupMessage);
        groupMessage.setGroupId(cachedGroup.getGroupId());
        return groupMessage;
    }

}
