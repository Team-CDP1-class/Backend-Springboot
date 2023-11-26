package com.knu.cdp1.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.knu.cdp1.DTO.Analysis.StoryCardAnalysis;
import com.knu.cdp1.DTO.StoryCard.StoryCardResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LangChainService {

    public StoryCardAnalysis analysisStoryCard(StoryCardResDTO storycard){
        String fastApiUrl = "http://localhost:8099/api/analysis/storycard";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<>(storycard, headers);
        ResponseEntity<String> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST, request, String.class);

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
}
