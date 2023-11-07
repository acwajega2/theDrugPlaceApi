package com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffSalaryDto {

    @JsonProperty("branchCode")
    private String branchCode;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("staffPhone")
    private String staffPhone;
    @JsonProperty("transactionReference")
    private String transactionReference;
}
