package com.knu.cdp1.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="users")
public class UserVO {
    @Id
    private String email;

    private String password;
    @Column(columnDefinition = "nvarchar(10)")
    private String nickname;
    @Column(columnDefinition = "nvarchar(10)")
    private String name;
    private String birth;

    public void update(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

}
