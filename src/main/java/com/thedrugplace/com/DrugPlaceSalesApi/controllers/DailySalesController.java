package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.BranchSalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.DailySalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.BestPerformingStaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/dailySales")
public class DailySalesController {
    private static final Logger logger = Logger.getLogger(DailySalesController.class.getName());

    private final DailySalesService dailySalesService;

    @Autowired
    public DailySalesController(DailySalesService dailySalesService) {
        this.dailySalesService = dailySalesService;
    }

    /**
     * Create a new daily sales entry.
     *
     * @param dailySalesDto The DTO containing daily sales information.
     * @return ResponseEntity with the created daily sales and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<DailySalesDto> createDailySales(@RequestBody DailySalesDto dailySalesDto) {
        logger.info("Creating a new daily sales entry.");
        DailySalesDto createdSales = dailySalesService.createDailySales(dailySalesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSales);
    }

    /**
     * Get daily sales by its ID.
     *
     * @param salesId The ID of the daily sales to retrieve.
     * @return ResponseEntity with the daily sales information and HTTP status 200 (OK) if found,
     * or 404 (Not Found) if not found.
     */
    @GetMapping("/{salesId}")
    public ResponseEntity<DailySalesDto> getDailySalesById(@PathVariable Long salesId) {
        logger.info("Getting daily sales by ID: " + salesId);
        DailySalesDto salesDto = dailySalesService.getDailySalesById(salesId);
        if (salesDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(salesDto);
        } else {
            logger.warning("Daily sales not found with ID: " + salesId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get a list of all daily sales entries.
     *
     * @return ResponseEntity with a list of daily sales entries and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<DailySalesDto>> getAllDailySales() {
        logger.info("Getting all daily sales entries.");
        List<DailySalesDto> salesList = dailySalesService.getAllDailySales();
        return ResponseEntity.status(HttpStatus.OK).body(salesList);
    }

    /**
     * Update a daily sales entry by its ID.
     *
     * @param salesId       The ID of the daily sales entry to update.
     * @param dailySalesDto The updated daily sales information.
     * @return ResponseEntity with the updated daily sales entry and HTTP status 200 (OK) if updated,
     * or 404 (Not Found) if the daily sales entry was not found.
     */
    @PutMapping("/{salesId}")
    public ResponseEntity<DailySalesDto> updateDailySales(@PathVariable Long salesId, @RequestBody DailySalesDto dailySalesDto) {
        logger.info("Updating daily sales with ID: " + salesId);
        DailySalesDto updatedSales = dailySalesService.updateDailySales(salesId, dailySalesDto);
        if (updatedSales != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedSales);
        } else {
            logger.warning("Daily sales not found for updating with ID: " + salesId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Delete a daily sales entry by its ID.
     *
     * @param salesId The ID of the daily sales entry to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the daily sales entry was deleted.
     */
    @DeleteMapping("/{salesId}")
    public ResponseEntity<Void> deleteDailySales(@PathVariable Long salesId) {
        logger.info("Deleting daily sales with ID: " + salesId);
        dailySalesService.deleteDailySales(salesId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * Get a list of branch sales with staff details, ordered by date in descending order.
     *
     * @return ResponseEntity with a list of branch sales and HTTP status 200 (OK).
     */
    @GetMapping("/branch-sales")
    public ResponseEntity<List<BranchSalesDto>> getBranchSalesWithStaffDetailsOrderByDateDesc() {
        logger.info("Getting branch sales with staff details ordered by date.");
        List<BranchSalesDto> branchSales = dailySalesService.getBranchSalesWithStaffDetailsOrderByDateDesc();
        return ResponseEntity.status(HttpStatus.OK).body(branchSales);
    }

    /**
     * Get a list of daily sales entries by staff phone number with branch details.
     *
     * @param phoneNumber The phone number of the staff.
     * @return ResponseEntity with a list of daily sales entries and HTTP status 200 (OK).
     */
    @GetMapping("/sales-by-phone")
    public ResponseEntity<List<DailySalesDto>> getSalesByStaffPhoneNumberWithBranchDetails(@RequestParam String phoneNumber) {
        logger.info("Getting daily sales entries by staff phone number: " + phoneNumber);
        List<DailySalesDto> salesList = dailySalesService.getSalesByStaffPhoneNumberWithBranchDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(salesList);
    }

    /**
     * Get a list of branch sales performance by month and year.
     *
     * @return ResponseEntity with a list of branch sales performance and HTTP status 200 (OK).
     */
    @GetMapping("/branch-performance")
    public ResponseEntity<List<BranchPerformanceDto>> getBranchPerformanceByMonthAndYear() {
        logger.info("Getting branch sales performance by month and year.");
        List<BranchPerformanceDto> branchPerformance = dailySalesService.getBranchPerformanceByMonthAndYear();
        return ResponseEntity.status(HttpStatus.OK).body(branchPerformance);
    }

    /**
     * Get a list of best performing staff by month and year.
     *
     * @return ResponseEntity with a list of best performing staff and HTTP status 200 (OK).
     */
    @GetMapping("/best-performing-staff")
    public ResponseEntity<List<BestPerformingStaffDto>> getBestPerformingStaffByMonthAndYear() {
        logger.info("Getting best performing staff by month and year.");
        List<BestPerformingStaffDto> bestPerformingStaff = dailySalesService.getBestPerformingStaffByMonthAndYear();
        return ResponseEntity.status(HttpStatus.OK).body(bestPerformingStaff);
    }
}
