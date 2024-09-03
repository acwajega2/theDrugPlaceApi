package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.DailyExpensesDto;

import java.util.List;

/**
 * Service interface for managing daily expenses.
 */

public interface DailyExpensesService {

    /**
     * Create a new daily expenses entry.
     *
     * @param dailyExpensesDto The DTO representing the daily expenses entry to be created.
     * @return The created daily expenses entry DTO.
     */
    DailyExpensesDto createDailyExpenses(DailyExpensesDto dailyExpensesDto);

    /**
     * Get a daily expenses entry by its unique identifier.
     *
     * @param expenseId The unique identifier of the daily expenses entry.
     * @return The daily expenses entry DTO, or null if not found.
     */
    DailyExpensesDto getDailyExpensesById(Long expenseId);

    /**
     * Get a list of all daily expenses entries.
     *
     * @return A list of daily expenses entry DTOs.
     */
    List<DailyExpensesDto> getAllDailyExpenses();

    /**
     * Update an existing daily expenses entry.
     *
     * @param expenseId        The unique identifier of the daily expenses entry to be updated.
     * @param dailyExpensesDto The DTO representing the updated daily expenses entry data.
     * @return The updated daily expenses entry DTO, or null if the entry doesn't exist.
     */
    DailyExpensesDto updateDailyExpenses(Long expenseId, DailyExpensesDto dailyExpensesDto);

    /**
     * Delete a daily expenses entry by its unique identifier.
     *
     * @param expenseId The unique identifier of the daily expenses entry to be deleted.
     */
    void deleteDailyExpenses(Long expenseId);


    // Get Expenses by Branch Code
    List<DailyExpensesDto> getBranchExepnsesByBranchCode(String branchCode);


    /**
     * Get a list of branch expenses with staff details, ordered by date in descending order.
     *
     * @return A list of BranchExpensesDto representing branch expenses with staff details.
     */
    List<BranchExpensesDto> getBranchExpensesWithStaffDetailsOrderByDateDesc();

    /**
     * Get a list of branch expenses performance metrics by month and year.
     *
     * @return A list of BranchExpensesPerformanceDto representing branch expenses performance metrics.
     */
    List<BranchExpensesPerformanceDto> getBranchExpensesPerformanceByMonthAndYear();
}
