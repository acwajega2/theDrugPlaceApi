package com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import lombok.Data;


import java.util.Date;

@Data
public class BranchExpensesDto {
    private Branch branch;



    /** The staff associated with the branch expenses data. */
    private Staff staff;

    /** The date for which expenses are recorded. */
    private Date date;

    /** The total expenses incurred on the specified date. */
    private double totalExpenses;

    /**
     * Constructs a `BranchExpensesDto` object.
     *
     * @param branch        The branch associated with the expenses data.
     * @param staff         The staff associated with the branch expenses data.
     * @param date          The date for which expenses are recorded.
     * @param totalExpenses The total expenses incurred on the specified date.
     */
    public BranchExpensesDto(Branch branch, Staff staff, Date date, double totalExpenses) {
        this.branch = branch;
        this.staff = staff;
        this.date = date;
        this.totalExpenses = totalExpenses;
    }
}
