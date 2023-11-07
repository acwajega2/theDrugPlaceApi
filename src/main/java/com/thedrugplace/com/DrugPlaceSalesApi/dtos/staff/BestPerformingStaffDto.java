package com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import lombok.Data;



@Data
public class BestPerformingStaffDto {
    private Staff staff;
    private int year;
    private int month;
    private double totalSales;

    public BestPerformingStaffDto(Staff staff, int year, int month, double totalSales) {
        this.staff = staff;
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
    }
}
