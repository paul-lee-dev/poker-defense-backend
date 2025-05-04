package com.ssafy.focker_defense.room.controller;

import com.ssafy.focker_defense.room.dto.CreateRoomReqDto;
import com.ssafy.focker_defense.room.dto.EnterRoomReqDto;
import com.ssafy.focker_defense.room.dto.EnterRoomResDto;
import com.ssafy.focker_defense.room.dto.QuitRoomReqDto;
import com.ssafy.focker_defense.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequestMapping("/room")
@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/{roomId}")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomReqDto createRoomReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        EnterRoomResDto result = roomService.createRoom(createRoomReqDto);

        return ResponseEntity.status(200).headers(headers).body(result);
    }

    /**
     * Message 엔드포인트로 데이터와 함께 호출을 하면 "/sub/{roomId}"를 수신하는 사용자에게 메시지를 전달
     */
    @MessageMapping("/enter/{roomId}")
    @SendTo("/sub/{roomId}")    // 소켓 연결
    public ResponseEntity<?> enterRoom(@DestinationVariable Long roomId, @RequestBody EnterRoomReqDto enterRoomReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        EnterRoomResDto result = roomService.enterRoom(enterRoomReqDto);

        return ResponseEntity.status(200).headers(headers).body(result);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> quitRoom(@PathVariable String roomId, @RequestBody QuitRoomReqDto quitRoomReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        quitRoomReqDto.setRoomId(roomId);
        roomService.quitRoom(quitRoomReqDto);

        return ResponseEntity.status(200).headers(headers).body(null);
    }


}
