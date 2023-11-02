package com.knu.cdp1.controller;

import com.knu.cdp1.DTO.StoryCard.StoryCardReqDTO;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import com.knu.cdp1.service.StoryCardService;
import com.knu.cdp1.vo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://192.168.56.1:3000/", allowedHeaders = "*")
public class StoryCardController {

    private final StoryCardService storyCardService;

    // 스토리카드 저장
    @PostMapping("/storycard")
    public ResponseEntity<?> save(@RequestBody StoryCardReqDTO reqDTO) {
        System.out.println("save StoryCard");
        Message message = new Message();

        int res = storyCardService.save(reqDTO);
        if (res > 0) message.buildMessage(true, HttpStatus.CREATED, "요청에 성공했습니다.",res);
        else message.buildMessage(false, HttpStatus.BAD_REQUEST, "요청에 실패했습니다.",null);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 조회
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
    @GetMapping("/storycard")
    public ResponseEntity<?> findAll() {
        System.out.println("findAll StoryCard");
        Message message = new Message();

        List<StoryCardResDTO> resDTO = storyCardService.findAll();
        message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", resDTO);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

    // 스토리카드 수정
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
    @DeleteMapping("/storycard/{storycardId}")
    public ResponseEntity<?> delete(@PathVariable("storycardId") Long storycardId) {
        System.out.println("delete Story");
        Message message = new Message();

        storyCardService.deleteById(storycardId);
        message.buildMessage(true, HttpStatus.OK, "요청에 성공했습니다.", storycardId);

        return ResponseEntity.status(message.getStatusCode()).body(message);
    }

}
