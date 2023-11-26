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
}

@Getter
@Setter
class Keyword {
    private String keyword;
    private String reason;
}

@Getter
@Setter
class SimilarStory {
    private String title;
    private String story;
    private List<Similarity> similarity;
}

@Getter
@Setter
class Similarity {
    private int percent;
    private String reason;
}
