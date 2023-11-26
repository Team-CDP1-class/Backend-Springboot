package com.knu.cdp1.controller;

import com.knu.cdp1.DTO.Analysis.StoryTreatmentAnalysis;
import com.knu.cdp1.service.LangChainService;
import com.knu.cdp1.vo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoryTreatmentController {

    private final LangChainService langChainService;
    @PostMapping("/storyTreatment")
    public ResponseEntity<?> analysis(@RequestBody String StoryTreatment) {
        System.out.println("Story Treatment Analysis");
        Message message = new Message();

        StoryTreatmentAnalysis analysis = langChainService.analysisStoryTreatment(StoryTreatment);
        if (analysis == null) message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("storyTreatmentAnalysis", analysis);
            message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", resultMap);
        }

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }
}
