package com.knu.cdp1.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knu.cdp1.DTO.Analysis.StoryCardAnalysis;
import com.knu.cdp1.DTO.Analysis.StoryTreatmentAnalysis;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import com.knu.cdp1.repository.LogRepository;
import com.knu.cdp1.vo.LogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LangChainService {

    private final LogRepository logRepository;

    // 스토리카드 분석 요청
    @Transactional
    public StoryCardAnalysis analysisStoryCard(StoryCardResDTO storycard){
        String fastApiUrl = "http://0.0.0.0:8099/api/analysis/storycard";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<>(storycard, headers);

        // 응답 시간 체크 시작
        long startTime = System.currentTimeMillis();
        ResponseEntity<String> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST, request, String.class);
        // 응답 시간 체크 끝
        long endTime = System.currentTimeMillis();
        long secDiffTime = (endTime - startTime)/1000;

        if (response.getStatusCode().is2xxSuccessful()) { // 요청 성공
            LogVO log = LogVO.builder()
                    .type("STORYCARD")
                    .reqByte(storycard.getBytes())
                    .resByte(response.getBody().getBytes().length)
                    .resTime(secDiffTime)
                    .isSuccess(true)
                    .build();
            logRepository.save(log);
        } else if (response.getStatusCode().is4xxClientError()) {    // 요청 실패
            LogVO log = LogVO.builder()
                    .type("STORYCARD")
                    .reqByte(storycard.getBytes())
                    .resByte(0)
                    .resTime(secDiffTime)
                    .isSuccess(false)
                    .build();
            logRepository.save(log);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode targetNode = jsonNode.get("data");
            if (targetNode == null) return null;
            return objectMapper.convertValue(targetNode, StoryCardAnalysis.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 스토리트리트먼트 분석 요청
    @Transactional
    public StoryTreatmentAnalysis analysisStoryTreatment(String contents) {
        String fastApiUrl = "http://0.0.0.0:8099/api/analysis/storytreatment";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String requestBody = "{\"contents\":\"" + contents + "\"}";
        HttpEntity<?> request = new HttpEntity<>(requestBody, headers);

        long startTime = System.currentTimeMillis(); // 응답 시간 체크 시작
        ResponseEntity<String> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST, request, String.class);
        long endTime = System.currentTimeMillis(); // 응답 시간 체크 끝
        long secDiffTime = (endTime - startTime) / 1000;

        if (response.getStatusCode().is2xxSuccessful()) { // 요청 성공
            LogVO log = LogVO.builder()
                    .type("TREATMENT")
                    .reqByte(contents.getBytes().length)
                    .resByte(response.getBody().getBytes().length)
                    .resTime(secDiffTime)
                    .isSuccess(true)
                    .build();
            logRepository.save(log);
        } else if (response.getStatusCode().is4xxClientError()) {    // 요청 실패
            LogVO log = LogVO.builder()
                    .type("TREATMENT")
                    .reqByte(contents.getBytes().length)
                    .resByte(0)
                    .resTime(secDiffTime)
                    .isSuccess(false)
                    .build();
            logRepository.save(log);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode targetNode = jsonNode.get("data");
            if (targetNode == null) return null;
            return objectMapper.convertValue(targetNode, StoryTreatmentAnalysis.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
