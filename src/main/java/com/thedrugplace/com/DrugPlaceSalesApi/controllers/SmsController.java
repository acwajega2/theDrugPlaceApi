package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.beans.SmsConfig;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailySalesService;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private DailySalesService dailySalesService;
    @Autowired
    private SmsConfig smsConfig;

    @GetMapping("/check-balance")
    public String checkBalance() {
        return smsService.checkBalance();
    }

    @GetMapping("/send-sms")
    public String sendSms(@RequestParam String to, @RequestParam String from,
                          @RequestParam String message, @RequestParam String dlrUrl) {
        return smsService.sendMessage(to, from, message, dlrUrl);
    }

    @GetMapping("/sendDen")
    public String sendSms() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // Subtract 1 day
        Date yesterday = calendar.getTime();
        Double totalSalesYesterday = dailySalesService.getPreviousDaySales(yesterday);
        return smsService.sendMessage("256772123017", "", String.format(smsConfig.getDailySalesTemplate(), "Chris", totalSalesYesterday), "");
    }
}
