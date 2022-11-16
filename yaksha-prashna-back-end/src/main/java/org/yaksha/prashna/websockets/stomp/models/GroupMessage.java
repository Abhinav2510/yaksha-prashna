package org.yaksha.prashna.websockets.stomp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public  class GroupMessage {
    String groupId;
    String fromUser;
    GroupEventType eventType;
}
