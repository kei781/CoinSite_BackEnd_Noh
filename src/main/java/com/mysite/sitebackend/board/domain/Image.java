package com.mysite.sitebackend.board.domain;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Embeddable;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Image {
    @NotNull
    private String fileName;
    @NotNull
    private String fileOriName;
    @NotNull
    private String fileUrl;
}
