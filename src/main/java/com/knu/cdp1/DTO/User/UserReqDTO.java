package com.knu.cdp1.DTO.User;

import com.knu.cdp1.vo.UserVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDTO {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String birth;

    @Builder
    public UserReqDTO(
            String email, String password, String nickname,
            String name, String birth) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
    }

    public UserVO toEntity() {
        return UserVO.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .birth(birth)
                .build();
    }
}
