package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.BranchSalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.DailySalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.BestPerformingStaffDto;

import java.util.List;

/**
 * Service interface for managing daily sales data.
 */

public interface DailySalesService {

    /**
     * Create a new daily sales entry.
     *
     * @param dailySalesDto The DTO representing the daily sales entry to be created.
     * @return The created daily sales entry DTO.
     */
    DailySalesDto createDailySales(DailySalesDto dailySalesDto);

    /**
     * Get a daily sales entry by its unique identifier.
     *
     * @param salesId The unique identifier of the daily sales entry.
     * @return The daily sales entry DTO, or null if not found.
     */
    DailySalesDto getDailySalesById(Long salesId);

    /**
     * Get a list of all daily sales entries.
     *
     * @return A list of daily sales entry DTOs.
     */
    List<DailySalesDto> getAllDailySales();

    /**
     * Update an existing daily sales entry.
     *
     * @param salesId       The unique identifier of the daily sales entry to be updated.
     * @param dailySalesDto The DTO representing the updated daily sales entry data.
     * @return The updated daily sales entry DTO, or null if the entry doesn't exist.
     */
    DailySalesDto updateDailySales(Long salesId, DailySalesDto dailySalesDto);

    /**
     * Delete a daily sales entry by its unique identifier.
     *
     * @param salesId The unique identifier of the daily sales entry to be deleted.
     */
    void deleteDailySales(Long salesId);

    /**
     * Get a list of daily sales entries by a staff member's phone number with branch details.
     *
     * @param phoneNumber The phone number of the staff member.
     * @return A list of DailySalesDto representing daily sales entries with branch details.
     */
    List<DailySalesDto> getSalesByStaffPhoneNumberWithBranchDetails(String phoneNumber);

    List<DailySalesDto> getSalesByBranchCode(String branchCode);

    /**
     * Get a list of branch sales with staff details, ordered by date in descending order.
     *
     * @return A list of BranchSalesDto representing branch sales with staff details.
     */
    List<BranchSalesDto> getBranchSalesWithStaffDetailsOrderByDateDesc();

    /**
     * Get a list of branch performance metrics by month and year.
     *
     * @return A list of BranchPerformanceDto representing branch performance metrics.
     */
    List<BranchPerformanceDto> getBranchPerformanceByMonthAndYear();

    /**
     * Get a list of best performing staff members by month and year.
     *
     * @return A list of BestPerformingStaffDto representing best performing staff members.
     */
    List<BestPerformingStaffDto> getBestPerformingStaffByMonthAndYear();
}
