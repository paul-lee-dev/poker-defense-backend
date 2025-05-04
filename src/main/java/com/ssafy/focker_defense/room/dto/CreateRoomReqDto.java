package com.ssafy.focker_defense.room.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreateRoomReqDto {

    private String creatorId;

    private String title;

    private Integer limitUserCnt;

}
