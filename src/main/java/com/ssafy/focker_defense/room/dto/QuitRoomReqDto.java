package com.ssafy.focker_defense.room.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuitRoomReqDto {

    private String userId;

    @Setter
    private String roomId;

}
