package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp.WhatsAppResponse;

public interface WhatsAppService {

    /**
     * Sends a WhatsApp message to the specified recipient.
     *
     * @param to   The recipient's WhatsApp number.
     * @param body The message content to send.
     * @return A Pair<Boolean, String> where the Boolean indicates success or failure,
     * and the String contains the status message or error message.
     */
    WhatsAppResponse sendWhatsAppMessage(String to, String body);
}
