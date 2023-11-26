package com.knu.cdp1.service;

import com.knu.cdp1.DTO.Analysis.AnalysisRes;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LangChainService {

    public void analysisStoryCard(StoryCardResDTO storycard) {
        String fastApiUrl = "http://localhost:8099/api/analysis/storycard";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<>(storycard, headers);
        ResponseEntity<?> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST, request, String.class);

        System.out.println(response);
    }
}
