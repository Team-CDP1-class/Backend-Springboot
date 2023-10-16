package com.knu.cdp1.vo;

import jakarta.persistence.Column;
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
    private String id;

    private String password;
    @Column(columnDefinition = "nvarchar(10)")
    private String nickname;
    @Column(columnDefinition = "nvarchar(10)")
    private String name;
    private String birth;

}
