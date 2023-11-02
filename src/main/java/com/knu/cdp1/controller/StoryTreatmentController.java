package com.knu.cdp1.controller;

import com.knu.cdp1.vo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoryTreatmentController {

    @PostMapping("/storyTreatment")
    public ResponseEntity<?> analysis(@RequestBody String StoryTreatment) {
        System.out.println("Story Treatment Analysis");
        Message message = new Message();

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }
}
