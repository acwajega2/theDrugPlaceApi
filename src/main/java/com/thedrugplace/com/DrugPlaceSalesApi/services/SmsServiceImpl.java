package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.beans.SmsConfig;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.SmsService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String checkBalance() {
        String url = smsConfig.getBalanceUrl();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", smsConfig.getUsername());
        body.add("password", smsConfig.getPassword());

        return sendRequest(url, body);
    }

    @Override
    public String sendMessage(String to, String from, String message, String dlrUrl) {
        String url = smsConfig.getSendMessageUrl();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", smsConfig.getUsername());
        body.add("password", smsConfig.getPassword());
        body.add("to", to);
        body.add("from", from);
        body.add("message", message);
        body.add("dlr_url", dlrUrl);

        return sendRequest(url, body);
    }

    @Nullable
    private String sendRequest(String url, MultiValueMap<String, String> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }
}
