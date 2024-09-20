package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.whatsapp.WhatsAppResponse;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.WhatsAppService;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {

    @Value("${twilio.whatsapp-from}")
    private String fromWhatsApp;

    @Override
    public WhatsAppResponse sendWhatsAppMessage(String to, String body) {
        // Validate and format the phone number
        String formattedNumber = formatPhoneNumber(to);
        if (formattedNumber == null) {
            // If the number is invalid, return validation error immediately
            return new WhatsAppResponse(false, "Invalid phone number format.", "PHONE_FORMAT_ERROR");
        }

        // Try sending the message and handle any Twilio-specific errors
        try {
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + formattedNumber),  // Receiver's formatted WhatsApp number
                    new PhoneNumber(fromWhatsApp),                   // Twilio WhatsApp number
                    body                                             // Message content
            ).create();

            // Return success status with message SID
            return new WhatsAppResponse(true, "Message Sent. SID: " + message.getSid(), null);

        } catch (ApiException e) {
            // Handle Twilio API errors by using Twilio error codes
            return new WhatsAppResponse(false, "Failed to send message: " + e.getMessage(), e.getCode().toString());
        } catch (Exception e) {
            // Catch any other unknown errors
            return new WhatsAppResponse(false, "An unexpected error occurred: " + e.getMessage(), "UNKNOWN_ERROR");
        }
    }

    /**
     * Validates and formats a phone number into the international format: +256XXXXXXXXX
     *
     * @param phoneNumber The phone number input by the user.
     * @return A formatted phone number with the country code (+256) or null if invalid.
     */
    private String formatPhoneNumber(String phoneNumber) {
        // Remove any spaces or special characters
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");  // Keep only digits

        // If number starts with 0, replace it with +256
        if (phoneNumber.startsWith("0")) {
            phoneNumber = "+256" + phoneNumber.substring(1);
        }
        // If number starts with 256, add +
        else if (phoneNumber.startsWith("256")) {
            phoneNumber = "+256" + phoneNumber.substring(3);
        }
        // If the number already starts with +256, do nothing
        else if (phoneNumber.startsWith("+256")) {
            // Already in the correct format
        }
        // If the number does not start with 0 or 256, it's considered invalid
        else {
            return null;
        }

        // Check the length, it must be 13 characters including +256
        if (phoneNumber.length() != 13) {
            return null;
        }

        return phoneNumber;
    }
}