package com.ssafy.focker_defense.room.service;

import com.ssafy.focker_defense.room.dto.CreateRoomReqDto;
import com.ssafy.focker_defense.room.dto.EnterRoomReqDto;
import com.ssafy.focker_defense.room.dto.EnterRoomResDto;
import com.ssafy.focker_defense.room.dto.QuitRoomReqDto;
import com.ssafy.focker_defense.room.entity.Room;
import com.ssafy.focker_defense.room.error.RoomException;
import com.ssafy.focker_defense.user.dto.FindUserReqDto;
import com.ssafy.focker_defense.user.dto.FindUserResDto;
import com.ssafy.focker_defense.user.entity.User;
import com.ssafy.focker_defense.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final UserService userService;

    private final Map<String, Room> existsRoomList = new ConcurrentHashMap<>();

    public EnterRoomResDto createRoom(CreateRoomReqDto createRoomReqDto) {

        Room newRoom = Room.builder()
                .roomId(UUID.randomUUID().toString())
                .creatorId(createRoomReqDto.getCreatorId())
                .limitUserCnt(createRoomReqDto.getLimitUserCnt())
                .title(createRoomReqDto.getTitle())
                .build();

        // 해당 유저가 존재하는지 검증
        User findUser = userService.findUserByUserId(newRoom.getCreatorId());

        existsRoomList.put(newRoom.getRoomId(), newRoom);

        return EnterRoomResDto
                .builder()
                .roomId(newRoom.getRoomId())
                .build();
    }

    public EnterRoomResDto enterRoom(EnterRoomReqDto enterRoomReqDto) {

        Room findRoom = existsRoomList.get(enterRoomReqDto.getRoomId());

        if(findRoom == null) {

            throw new RoomException("해당 방은 존재하지 않습니다.");
        }

        User user = User.builder()
                .id(enterRoomReqDto.getUserId())
                .nickname(enterRoomReqDto.getNickname())
                .build();

        findRoom.addUser(user);

        return EnterRoomResDto.builder()
                .roomId(findRoom.getRoomId())
                .build();
    }

    public void quitRoom(QuitRoomReqDto quitRoomReqDto) {

        Room findRoom = existsRoomList.get(quitRoomReqDto.getRoomId());

        User findUser = userService.findUserByUserId(quitRoomReqDto.getUserId());

        findRoom.removeUser(findUser);
    }
}
