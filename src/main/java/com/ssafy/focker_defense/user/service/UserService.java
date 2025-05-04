package com.ssafy.focker_defense.user.service;

import com.ssafy.focker_defense.user.dto.*;
import com.ssafy.focker_defense.user.entity.User;
import com.ssafy.focker_defense.user.error.UserException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, User> loginedUserList = new ConcurrentHashMap<>();

    public LoginResDto login(LoginReqDto loginReqDto) {

        User newUser = new User();

        newUser.createUser(loginReqDto);

        loginedUserList.put(newUser.getId(), newUser);

        return LoginResDto.builder()
                .userId(newUser.getId())
                .build();
    }

    public FindUserResDto findUser(FindUserReqDto findUserReqDto) {

        User findUser = findUserByUserId(findUserReqDto.getUserId());

        return FindUserResDto.builder()
                .userId(findUser.getId())
                .nickname(findUser.getNickname())
                .build();
    }

    public void logout(LogoutReqDto logoutReqDto) {

        loginedUserList.remove(logoutReqDto.getUserId());
    }

    public User findUserByUserId(String userId) {

        User findUser = loginedUserList.get(userId);

        if(findUser == null) {

            throw new UserException("해당 유저가 존재하지 않거나 로그아웃 상태입니다.");
        }

        return findUser;
    }
}
