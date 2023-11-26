package com.knu.cdp1.DTO.Analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoryTreatmentAnalysis {
    @JsonProperty("stageResult")
    private List<StageResult> stageResults;

    @JsonProperty("overallResult")
    private List<OverallResult> overallResults;
}
@Getter
@Setter
class StageResult {
    private String stage;
    private String summary;
    private List<EmotionResult> emotionResult;
}
@Getter
@Setter
class EmotionResult {
    private String emotion;
    private int score;
}
@Getter
@Setter
class OverallResult {
    private String emotion;
    private int score;
    private String reason;
}
