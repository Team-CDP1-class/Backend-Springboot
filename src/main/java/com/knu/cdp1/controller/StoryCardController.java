package com.knu.cdp1.controller;

import com.knu.cdp1.DTO.Analysis.StoryCardAnalysis;
import com.knu.cdp1.DTO.StoryCard.StoryCardReqDTO;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import com.knu.cdp1.service.JwtService;
import com.knu.cdp1.service.LangChainService;
import com.knu.cdp1.service.StoryCardService;
import com.knu.cdp1.vo.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "StoryCard API", description = "")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoryCardController {

    private final StoryCardService storyCardService;
    private final LangChainService langChainService;
    private final JwtService jwtService;

    // 스토리카드 저장
    @Operation(summary = "스토리카드 저장", description = "설명")
    @PostMapping("/storycard")
    public ResponseEntity<?> save(@RequestBody StoryCardReqDTO reqDTO) {
        System.out.println("save StoryCard");
        Message message = new Message();

        int res = storyCardService.save(reqDTO);
        if (res > 0) message.buildMessage(true, HttpStatus.CREATED, "요청에 성공했습니다.",res);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.",null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 분석
    @Operation(summary = "스토리카드 분석", description = "설명")
    @PostMapping("/storycard/{storycardId}")
    public ResponseEntity<?> analysis(@PathVariable("storycardId") Long storycardId) {
        System.out.println("Analysis StoryCard");
        Message message = new Message();

        StoryCardResDTO resDTO = storyCardService.findById(storycardId);
        StoryCardAnalysis analysis = langChainService.analysisStoryCard(resDTO);
        if (analysis == null) message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("storyCardAnalysis", analysis);
            message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", resultMap);
        }

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 조회
    @Operation(summary = "스토리카드 조희", description = "설명")
    @GetMapping("/storycard/{storycardId}")
    public ResponseEntity<?> findById(@PathVariable("storycardId") Long storycardId) {
        System.out.println("find StoryCard");
        Message message = new Message();

        StoryCardResDTO resDTO = storyCardService.findById(storycardId);
        if (resDTO != null) message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", resDTO);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 목록 조회
    @Operation(summary = "스토리카드 목록 조회", description = "설명")
    @GetMapping("/storycard")
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization", required = true) String token) {
        System.out.println("findAll StoryCard");
        Message message = new Message();

        String userEmail = jwtService.extractUsername(token.substring(7));
        System.out.println(userEmail);

        List<StoryCardResDTO> resDTO = storyCardService.findByEmail(userEmail);
        message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", resDTO);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 수정
    @Operation(summary = "스토리카드 수정", description = "설명")
    @PatchMapping("/storycard/{storycardId}")
    public ResponseEntity<?> modify(
            @RequestBody StoryCardReqDTO reqDTO,
            @PathVariable("storycardId") Long storycardId) {
        System.out.println("modify StoryCard");
        Message message = new Message();

        Long res = storyCardService.update(storycardId, reqDTO);
        if (res > 0) message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", res);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.", null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 삭제
    @Operation(summary = "스토리카드 삭제", description = "설명")
    @DeleteMapping("/storycard/{storycardId}")
    public ResponseEntity<?> delete(@PathVariable("storycardId") Long storycardId) {
        System.out.println("delete Story");
        Message message = new Message();

        storyCardService.deleteById(storycardId);
        message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", storycardId);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }
}
