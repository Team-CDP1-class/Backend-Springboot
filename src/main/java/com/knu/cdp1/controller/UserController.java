package com.knu.cdp1.controller;

import com.knu.cdp1.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.knu.cdp1.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/test")
    public String addUser(@RequestBody UserVO userVO){
        userRepository.save(userVO);
        return "";
    }
}
