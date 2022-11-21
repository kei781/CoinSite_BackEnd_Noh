package com.mysite.sitebackend.board.controller;

import com.mysite.sitebackend.board.dao.FileRepostiroy;
import com.mysite.sitebackend.board.domain.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {
    private final FileRepostiroy fileRepostiroy;
    String filepath = "C:/SCsite/image";

    // 이미지 업로드
    @PostMapping("/post/image")
    public FileEntity uploadImage(HttpServletRequest request,
                                  @RequestParam(value = "file", required = false) MultipartFile[] files) {
        String filepath = "/image";

        String originFileName = files[0].getOriginalFilename();
        String safeFile = System.currentTimeMillis() + originFileName;

        File f1 = new File(filepath + safeFile);
        try {
            files[0].transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final FileEntity file = FileEntity.builder().filename(safeFile).build();
        return fileRepostiroy.save(file);
    }

    // 이미지 다운로드
    @GetMapping("/download/image")
    public List<FileEntity> downloadImage(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(value = "filename") String filename) {
        File file = new File(filepath + filename);

        FileInputStream fis;
        BufferedInputStream bis;
        ServletOutputStream sos;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            sos = response.getOutputStream();

            String reFilename;
            // IE로 실행한 경우인지 -> IE는 따로 인코딩 작업을 거쳐야 한다.
            // request헤어에 MSIE 또는 Trident가 포함되어 있는지 확인
            boolean isMSIE = request.getHeader("user-agent").contains("Trident");

            if (isMSIE) {
                reFilename = URLEncoder.encode("이미지파일.jpg", StandardCharsets.UTF_8);
                reFilename = reFilename.replaceAll("\\+", "%20");
            } else {
                reFilename = new String("이미지 파일.jpg".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + reFilename + "\"");
            response.setContentLength((int) file.length());

            int read;
            while ((read = bis.read()) != -1) {
                sos.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 이미지 불러오기
    @GetMapping("/image")
    public List<FileEntity> findAllImages() {
        return fileRepostiroy.findAll();
    }
}
