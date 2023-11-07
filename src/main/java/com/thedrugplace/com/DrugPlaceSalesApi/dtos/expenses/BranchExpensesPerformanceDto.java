package com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import lombok.Data;



@Data
public class BranchExpensesPerformanceDto {
    private Branch branch;

    /** The year for which the expenses performance is measured. */
    private int year;

    /** The month for which the expenses performance is measured. */
    private int month;

    /** The total expenses incurred by the branch in the specified year and month. */
    private double totalExpenses;

    /** The average expenses incurred by the branch in the specified year and month. */
    private double averageExpenses;

    /** The performance score indicating how expenses compare to the average. */
    private double performanceScore;

    /**
     * Constructs a `BranchExpensesPerformanceDto` object.
     *
     * @param branch          The branch for which expenses performance is measured.
     * @param year            The year for which the expenses performance is measured.
     * @param month           The month for which the expenses performance is measured.
     * @param totalExpenses   The total expenses incurred by the branch in the specified year and month.
     * @param averageExpenses The average expenses incurred by the branch in the specified year and month.
     */
    public BranchExpensesPerformanceDto(Branch branch, int year, int month, double totalExpenses, double averageExpenses) {
        this.branch = branch;
        this.year = year;
        this.month = month;
        this.totalExpenses = totalExpenses;
        this.averageExpenses = averageExpenses;
        this.performanceScore = calculatePerformanceScore(totalExpenses, averageExpenses);
    }

    /**
     * Calculates the performance score based on specified criteria.
     *
     * @param totalExpenses   The total expenses incurred by the branch.
     * @param averageExpenses The average expenses incurred by the branch.
     * @return The performance score indicating how expenses compare to the average.
     */
    private double calculatePerformanceScore(double totalExpenses, double averageExpenses) {
        // Define your performance scoring logic here
        // For example, you can use totalExpenses, averageExpenses, and other factors to calculate the score
        // The calculation can vary based on your specific criteria
        return totalExpenses/ averageExpenses;
    }
}
