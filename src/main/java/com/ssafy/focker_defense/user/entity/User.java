package com.ssafy.focker_defense.user.entity;

import com.ssafy.focker_defense.user.dto.LoginReqDto;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class User {

    private String id;

    private String nickname;

    public String createUser(LoginReqDto loginReqDto) {

        this.id = UUID.randomUUID().toString();
        this.nickname = loginReqDto.getNickname();

        return this.id;
    }
}
