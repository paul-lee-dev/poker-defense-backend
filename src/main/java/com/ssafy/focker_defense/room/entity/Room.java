package com.ssafy.focker_defense.room.entity;

import com.ssafy.focker_defense.room.dto.CreateRoomReqDto;
import com.ssafy.focker_defense.user.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

    private String roomId;

    private String creatorId;

    private String title;

    @Builder.Default
    private RoomStatus status = RoomStatus.WAITING;

    private Integer limitUserCnt;

    @Builder.Default
    private List<User> userList = new ArrayList<>();

    public synchronized void addUser(User user) {

        userList.add(user);
    }

    public void removeUser(User user) {

        userList.remove(user);
    }

}
