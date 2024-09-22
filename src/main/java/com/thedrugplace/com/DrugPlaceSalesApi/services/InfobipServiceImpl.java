package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.InfobipService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfobipServiceImpl implements InfobipService {

    private final OkHttpClient client;
    private final String infobipApiUrl;
    private final String infobipAuthorization;

    @Autowired
    public InfobipServiceImpl(OkHttpClient client, String infobipApiUrl, String infobipAuthorization) {
        this.client = client;
        this.infobipApiUrl = infobipApiUrl;
        this.infobipAuthorization = infobipAuthorization;
    }

    @Override
    public String sendWhatsAppMessage(String from, String to, String textMessage) {
        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = String.format(
                "{\"from\":\"%s\",\"to\":\"%s\",\"messageId\":\"%s\",\"content\":{\"text\":\"%s\"},\"callbackData\":\"Callback data\",\"notifyUrl\":\"https://www.example.com/whatsapp\",\"urlOptions\":{\"shortenUrl\":true,\"trackClicks\":true,\"trackingUrl\":\"https://example.com/click-report\",\"removeProtocol\":true,\"customDomain\":\"example.com\"}}",
                from, to, java.util.UUID.randomUUID(), textMessage
        );

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(infobipApiUrl)
                .post(body)
                .addHeader("Authorization", infobipAuthorization)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return "Message sent successfully: " + response.body().string();
            } else {
                return "Failed to send message: " + response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while sending message: " + e.getMessage();
        }
    }
}