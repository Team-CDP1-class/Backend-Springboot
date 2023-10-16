package com.knu.cdp1.DTO.StoryCard;

import com.knu.cdp1.vo.StoryCardVO;
import lombok.Getter;

@Getter
public class StoryCardResDTO {
    private int Id;
    private String premise;
    private String setting;
    private String characters;
    private String outline;
    private String storycard_name;

    public StoryCardResDTO(StoryCardVO entity) {
        this.Id = entity.getId();
        this.premise = entity.getPremise();
        this.setting = entity.getSetting();
        this.characters = entity.getCharacters();
        this.outline = entity.getOutline();
        this.storycard_name = entity.getStorycard_name();
    }
}
