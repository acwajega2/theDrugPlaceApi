package com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import lombok.Data;



@Data
public class BranchPerformanceDto {
    private Branch branch;

    /** The year for which performance is calculated. */
    private int year;

    /** The month for which performance is calculated. */
    private int month;

    /** The total sales for the branch in the specified year and month. */
    private double totalSales;

    /** The average sales for the branch in the specified year and month. */
    private double averageSales;

    /** The performance score calculated based on your criteria. */
    private double performanceScore;

    /**
     * Constructs a `BranchPerformanceDto` object.
     *
     * @param branch       The branch associated with the performance data.
     * @param year         The year for which performance is calculated.
     * @param month        The month for which performance is calculated.
     * @param totalSales   The total sales for the branch in the specified year and month.
     * @param averageSales The average sales for the branch in the specified year and month.
     */
    public BranchPerformanceDto(Branch branch, int year, int month, double totalSales, double averageSales) {
        this.branch = branch;
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
        this.averageSales = averageSales;
        this.performanceScore = calculatePerformanceScore(totalSales, averageSales);
    }

    /**
     * Calculate the performance score based on your criteria.
     *
     * @param totalSales   The total sales for the branch in the specified year and month.
     * @param averageSales The average sales for the branch in the specified year and month.
     * @return The performance score calculated based on your criteria.
     */
    private double calculatePerformanceScore(double totalSales, double averageSales) {
        // Define your performance scoring logic here
        // For example, you can use totalSales, averageSales, and other factors to calculate the score
        // The calculation can vary based on your specific criteria
        return totalSales / averageSales;
    }
}
