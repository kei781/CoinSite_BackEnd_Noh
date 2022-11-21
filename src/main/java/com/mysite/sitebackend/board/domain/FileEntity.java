package com.mysite.sitebackend.board.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FileEntity {
    @Id // primary key임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false, unique = true, length = 1000)
    private String filename;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_dt;

    private Integer boardIndex;
}
