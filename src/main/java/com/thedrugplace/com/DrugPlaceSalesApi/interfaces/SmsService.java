package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

public interface SmsService {
    /**
     * Check the balance of the SMS account.
     *
     * @return A string representing the balance.
     */
    String checkBalance();

    /**
     * Send an SMS message.
     *
     * @param to      The recipient's phone number(s), URL-encoded.
     * @param from    The sender's phone number or identifier, URL-encoded.
     * @param message The message content, URL-encoded.
     * @param dlrUrl  The delivery report callback URL, URL-encoded.
     * @return A string response from the SMS API.
     */
    String sendMessage(String to, String from, String message, String dlrUrl);
}
