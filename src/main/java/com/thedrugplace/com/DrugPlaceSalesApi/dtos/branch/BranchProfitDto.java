package com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import lombok.Data;



@Data
public class BranchProfitDto {
    private Branch branch;

    /** The year for which net profit is calculated. */
    private int year;

    /** The month for which net profit is calculated. */
    private int month;

    /** The net profit of the branch in the specified year and month. */
    private double netProfit;

    /**
     * Constructs a `BranchProfitDto` object.
     *
     * @param branch    The branch associated with the net profit data.
     * @param year      The year for which net profit is calculated.
     * @param month     The month for which net profit is calculated.
     * @param netProfit The net profit of the branch in the specified year and month.
     */
    public BranchProfitDto(Branch branch, int year, int month, double netProfit) {
        this.branch = branch;
        this.year = year;
        this.month = month;
        this.netProfit = netProfit;
    }
}
