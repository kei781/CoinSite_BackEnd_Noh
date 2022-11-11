package com.mysite.sitebackend.configurable;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:secret.yml")
@Getter
public class ApiKey {
    @Value("${stock-api-key}")
    private String stockApiKey;
    @Value("${market-api-key}")
    private String marketApiKey;
}