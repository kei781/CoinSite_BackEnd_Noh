package com.mysite.sitebackend;

import com.mysite.sitebackend.configurable.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(Config.class)
@SpringBootApplication
public class SitebackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SitebackendApplication.class, args);
    }
}
