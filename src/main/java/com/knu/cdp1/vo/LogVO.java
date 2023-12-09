package com.knu.cdp1.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="SystemLog")
public class LogVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 요청 바이트 수, 응답 바이트 수, 응답 시간, 타입 (스토리카드 or 트리트먼트)
    private double reqByte;
    private double resByte;
    private long resTime;
    private String type;
    private boolean isSuccess;
}