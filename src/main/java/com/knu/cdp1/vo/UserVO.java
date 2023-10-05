package com.knu.cdp1.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="users")
public class UserVO {
    @Id
    private String email;

    private String password;
    private String nickname;
    private String name;
    private String birth;

}
