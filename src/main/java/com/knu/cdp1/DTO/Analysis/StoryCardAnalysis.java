package com.knu.cdp1.DTO.Analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoryCardAnalysis {
    @JsonProperty("keywords")
    private List<Keyword> keywords;

    @JsonProperty("similarStory")
    private List<SimilarStory> similarStories;

    // Getter 및 Setter 메서드 생략 (Lombok 등을 사용하면 편리하게 생성 가능)
}

@Getter
@Setter
class Keyword {
    private String keyword;
    private String reason;

    // Getter 및 Setter 메서드 생략
}

@Getter
@Setter
class SimilarStory {
    private String title;
    private String story;
    private List<Similarity> similarity;

    // Getter 및 Setter 메서드 생략
}

@Getter
@Setter
class Similarity {
    private int percent;
    private String reason;

    // Getter 및 Setter 메서드 생략
}
