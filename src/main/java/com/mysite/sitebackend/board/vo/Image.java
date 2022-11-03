package com.mysite.sitebackend.board.vo;

import lombok.Data;

import javax.persistence.Column;
@Data
public class Image {
    private String fileName;
    private String fileUrl;
}
