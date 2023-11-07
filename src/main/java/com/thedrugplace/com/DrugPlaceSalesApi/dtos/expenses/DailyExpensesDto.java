package com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyExpensesDto {

    private String transactionReference;
    @JsonProperty("branchCode")
    private String branchCode;

    @JsonProperty("staffPhone")
    private String staffPhone;

    /** The date on which the expense occurred. */
    @JsonProperty("expenseDate")
    private String expenseDate;

    /** The amount of the expense. */
    @JsonProperty("expenseAmount")
    private double expenseAmount;

    /** The category or type of the expense. */
    @JsonProperty("expenseCategory")
    private String expenseCategory;

    /** The URL of the receipt image related to the expense. */
    @JsonProperty("receiptImageUrl")
    private String receiptImageUrl;
}
