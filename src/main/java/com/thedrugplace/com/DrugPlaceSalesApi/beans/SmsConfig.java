package com.thedrugplace.com.DrugPlaceSalesApi.beans;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Configuration
public class SmsConfig {

    @Value("${sms.api.balance.url}")
    private String balanceUrl;

    @Value("${sms.api.sendmessage.url}")
    private String sendMessageUrl;

    @Value("${sms.api.username}")
    private String username;

    @Value("${sms.api.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
