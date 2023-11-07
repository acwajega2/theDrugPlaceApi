package com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffSalaryDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto {

    /** The name of the branch. */
    @JsonProperty("branchName")
    private String branchName;

    /** The unique code associated with the branch. */
    @JsonProperty("branchCode")
    private String branchCode;

    /** The location of the branch. */
    @JsonProperty("branchLocation")
    private String branchLocation;

    /** The ID of the branch manager. */
    @JsonProperty("branchManagerId")
    private int branchManagerId;

    /** The opening date of the branch. */
    @JsonProperty("branchOpeningDate")
    private String branchOpeningDate;

    /** The list of staff salaries associated with the branch. */
    @JsonProperty("staffSalaries")
    private List<StaffSalaryDto> staffSalaries;
}
