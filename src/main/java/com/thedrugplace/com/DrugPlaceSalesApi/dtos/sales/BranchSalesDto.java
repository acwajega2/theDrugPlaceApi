package com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import lombok.Data;


import java.util.Date;

@Data
public class BranchSalesDto {
    private Branch branch;
    private Staff staff;

    /** The total sales amount for the specified date. */
    private double totalSales;

    /** The date on which the sales occurred. */
    private Date date;

    public BranchSalesDto(Branch branch, Staff staff, Date date, double totalSales) {
        this.branch = branch;
        this.staff = staff;
        this.date = date;
        this.totalSales = totalSales;
    }
}
