package com.knu.cdp1.vo;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="story_card")
public class StoryCardVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserVO user;

    @Column(columnDefinition = "nvarchar(1000)")
    private String premise;
    @Column(columnDefinition = "nvarchar(1000)")
    private String setting;
    @Column(columnDefinition = "nvarchar(1000)")
    private String characters;
    @Column(columnDefinition = "nvarchar(1000)")
    private String outline;
    @Column(columnDefinition = "nvarchar(100)")
    private String storycard_name;

    public void update(
            String premise, String setting, String characters,
            String outline, String storycard_name) {
        this.premise = premise;
        this.setting = setting;
        this.characters = characters;
        this.outline = outline;
        this.storycard_name = storycard_name;
    }

}
