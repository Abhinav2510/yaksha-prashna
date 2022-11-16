package org.yaksha.prashna.controllers.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.yaksha.prashna.service.GroupService;
import org.yaksha.prashna.websockets.stomp.models.GroupMessage;


@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final SimpMessagingTemplate simpleMessageTemplate;
    private final GroupService groupService;

    @MessageMapping("/yakshaprashna/{groupId}")
    @CrossOrigin(origins = "*")
    public void groupHandler(@DestinationVariable("groupId") String groupId, GroupMessage group) {
        log.debug("Got message to topic: {}", group);

        if (groupService.getGroupById(groupId) == null) {
            return;
        }
        routeMessage(group);

    }

    private void routeMessage(GroupMessage groupMessage) {

        switch (groupMessage.getEventType()) {
            case JOINED:
                joinGroup(groupMessage);
                break;
            default:
                log.debug("Not suitable handler for eventType: {}", groupMessage.getGroupId());
                return;
        }
    }

    private void joinGroup(GroupMessage groupMessage) {
        try {
            if (groupMessage.getGroupId() == null || groupMessage.getGroupId().equalsIgnoreCase("")) {
                log.warn("Group Id is null.");
                return;
            }
            groupService.joinGroup(groupMessage);
            log.debug("Broadcasting user joined event to all the subscribers of group: {},message :{}", groupMessage.getGroupId(), groupMessage);
            simpleMessageTemplate.convertAndSend("/topic/group/" + groupMessage.getGroupId(), groupMessage);
        } catch (Exception exception) {
            log.error("Exception :{} while joining group: {} user: {} ", exception.getLocalizedMessage(), groupMessage.getGroupId(), groupMessage.getFromUser());
        }

    }

}
