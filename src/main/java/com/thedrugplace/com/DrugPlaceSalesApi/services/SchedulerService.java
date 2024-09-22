package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.beans.SmsConfig;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailySalesService;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SchedulerService {
    @Autowired
    private DailySalesService dailySalesService;
    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsConfig smsConfig;

    @Scheduled(cron = "0 0 12 * * ?")
    public void generatePreviousDaySalesReport() {
        // Generate daily sales report and store it in a database or file
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // Subtract 1 day
        Date yesterday = calendar.getTime();
        Double totalSalesYesterday = dailySalesService.getPreviousDaySales(yesterday);
        smsService.sendMessage("256772123017", "", String.format(smsConfig.getDailySalesTemplate(), "Chris", totalSalesYesterday), "");
    }

}
