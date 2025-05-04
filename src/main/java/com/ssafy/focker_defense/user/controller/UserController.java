package com.ssafy.focker_defense.user.controller;

import com.ssafy.focker_defense.user.dto.*;
import com.ssafy.focker_defense.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> login(LoginReqDto loginReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        LoginResDto result = userService.login(loginReqDto);

        return ResponseEntity.status(201).headers(headers).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUser(FindUserReqDto findUserReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        FindUserResDto result = userService.findUser(findUserReqDto);

        return ResponseEntity.status(200).headers(headers).body(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> logout(LogoutReqDto logoutReqDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        userService.logout(logoutReqDto);

        return ResponseEntity.status(200).headers(headers).body(null);
    }
}
