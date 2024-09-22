package com.thedrugplace.com.DrugPlaceSalesApi.beans;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfobipConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Bean
    public String infobipApiUrl() {
        return "https://d9mjj1.api.infobip.com/whatsapp/1/message/text"; // Update with the appropriate URL
    }

    @Bean
    public String infobipAuthorization() {
        return "Bearer 34f33dd97b0591950109826ce64df145-0234a392-77ac-4911-bd5d-2d62d9801418"; // Replace with your actual authorization token
    }
}