package com.knu.cdp1.controller;

import com.knu.cdp1.DTO.Authentication.AuthRes;
import com.knu.cdp1.DTO.User.UserReqDTO;
import com.knu.cdp1.service.UserService;
import com.knu.cdp1.vo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<?> register(@RequestBody UserReqDTO reqDTO){
        System.out.println("save User");
        Message message = new Message();

        String res = userService.register(reqDTO);
        if (res != null) message.buildMessage(true, HttpStatus.CREATED, "요청에 성공했습니다.", res);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 이메일 중복 확인
    @PostMapping("/users/email")
    public ResponseEntity<?> checkEmail(@RequestBody UserReqDTO reqDTO) {
        System.out.println("check Email: " + reqDTO.getEmail());
        Message message = new Message();

        boolean res = userService.findByEmail(reqDTO.getEmail());
        if (res) message.buildMessage(true, HttpStatus.OK, "사용 가능한 이메일입니다.", null);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "중복된 이메일입니다.", null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<?> lonIn(@RequestBody UserReqDTO reqDTO) {
        System.out.println("logIn User");
        Message message = new Message();

        AuthRes res = userService.logIn(reqDTO);
        message.buildMessage(true, HttpStatus.OK, "로그인 성공", res);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 로그아웃
//    @PostMapping("/users/logout")
//    public ResponseEntity<?> logOut() { }

    // 회원정보 수정
    @PatchMapping("/users")
    public ResponseEntity<?> modify(@RequestBody UserReqDTO reqDTO) {
        System.out.println("modify User");
        Message message = new Message();

        String res = userService.update(reqDTO);
        if (res != null) message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", res);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }
}
