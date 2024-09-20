package com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppResponse {
    private boolean success;
    private String message;
    private String errorCode;  // Optional, to handle Twilio error codes or validation errors
}
