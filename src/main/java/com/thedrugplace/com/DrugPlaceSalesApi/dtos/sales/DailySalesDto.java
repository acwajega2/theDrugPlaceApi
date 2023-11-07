package com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailySalesDto {


    @JsonProperty("staffPhone")
    private String staffPhone;

    /** The date of the sale. */
    @JsonProperty("saleDate")
    private String saleDate;

    @JsonProperty("branchCode")
    private String branchCode;

    /** The amount of the sale. */
    @JsonProperty("saleAmount")
    private double saleAmount;

    /** The payment method used for the sale. */
    @JsonProperty("paymentMethod")
    private String paymentMethod;

    /** The transaction reference for the sale. */
    @JsonProperty("transactionReference")
    private String transactionReference;
}
