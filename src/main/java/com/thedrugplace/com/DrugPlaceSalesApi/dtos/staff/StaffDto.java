package com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffDto {
    @JsonProperty("branchCode")
    private String branchCode;

    /**
     * The name of the staff member.
     */
    @JsonProperty("staffName")
    private String staffName;

    /**
     * The role or position of the staff member.
     */
    @JsonProperty("staffRole")
    private String staffRole;

    /**
     * The email address of the staff member.
     */
    @JsonProperty("staffEmail")
    private String staffEmail;

    /**
     * The phone number of the staff member.
     */
    @JsonProperty("staffPhone")
    private String staffPhone;

    /**
     * The date of hire for the staff member.
     */
    @JsonProperty("hireDate")
    private String hireDate;

    /**
     * The date of hire for the staff member.
     */
    @JsonProperty("username")
    private String username;
}
