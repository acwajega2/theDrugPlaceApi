package com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendWhatsAppMessageRequest {
    private String to;
    private String message;
}
