package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @GetMapping("/check-balance")
    public String checkBalance() {
        return smsService.checkBalance();
    }

    @GetMapping("/send-sms")
    public String sendSms(@RequestParam String to, @RequestParam String from,
                          @RequestParam String message, @RequestParam String dlrUrl) {
        return smsService.sendMessage(to, from, message, dlrUrl);
    }
}
