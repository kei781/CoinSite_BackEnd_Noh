package com.mysite.sitebackend.configurable;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiKey {
    @Value("${stock-api-key}")
    private String stockApiKey;
    @Value("${market-api-key}")
    private String marketApiKey;

    private static ApiKey instance = new ApiKey();

    private ApiKey(){
    }
    public static ApiKey getInstance(){
        if(instance == null){
            instance = new ApiKey();
        }
        return instance;
    }
}
