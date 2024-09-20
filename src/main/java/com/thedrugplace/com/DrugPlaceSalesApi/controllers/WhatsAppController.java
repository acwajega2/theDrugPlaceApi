package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp.SendWhatsAppMessageRequest;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp.WhatsAppResponse;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
public class WhatsAppController {
    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send")
    public ResponseEntity<WhatsAppResponse> sendWhatsAppMessage(@RequestBody SendWhatsAppMessageRequest dto) {
        // Call the service to send WhatsApp message
        WhatsAppResponse response = whatsAppService.sendWhatsAppMessage(dto.getTo(), dto.getMessage());

        // Return appropriate HTTP response based on the outcome
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
