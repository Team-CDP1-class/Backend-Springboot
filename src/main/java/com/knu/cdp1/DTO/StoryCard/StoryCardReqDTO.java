package com.knu.cdp1.DTO.StoryCard;

import com.knu.cdp1.vo.StoryCardVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoryCardReqDTO {
    private String premise;
    private String setting;
    private String characters;
    private String outline;
    private String storycard_name;

    @Builder
    public StoryCardReqDTO(
            String premise, String setting, String characters,
            String outline, String storycard_name) {
        this.premise = premise;
        this.setting = setting;
        this.characters = characters;
        this.outline = outline;
        this.storycard_name = storycard_name;
    }

    public StoryCardVO toEntity() {
        return StoryCardVO.builder()
                .premise(premise)
                .setting(setting)
                .characters(characters)
                .outline(outline)
                .storycard_name(storycard_name)
                .build();
    }
}
