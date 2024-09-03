package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.DailyExpensesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailyExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/dailyExpenses")
public class DailyExpensesController {
    private static final Logger logger = Logger.getLogger(DailyExpensesController.class.getName());

    private final DailyExpensesService dailyExpensesService;

    @Autowired
    public DailyExpensesController(DailyExpensesService dailyExpensesService) {
        this.dailyExpensesService = dailyExpensesService;
    }

    /**
     * Create a new daily expense entry.
     *
     * @param dailyExpensesDto The DTO containing daily expenses information.
     * @return ResponseEntity with the created daily expenses and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<DailyExpensesDto> createDailyExpenses(@RequestBody DailyExpensesDto dailyExpensesDto) {
        logger.info("Creating a new daily expense entry.");
        System.out.println(dailyExpensesDto.toString());
        DailyExpensesDto createdExpenses = dailyExpensesService.createDailyExpenses(dailyExpensesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpenses);
    }

    /**
     * Get daily expenses by its ID.
     *
     * @param expenseId The ID of the daily expenses to retrieve.
     * @return ResponseEntity with the daily expenses information and HTTP status 200 (OK) if found,
     * or 404 (Not Found) if not found.
     */
    @GetMapping("/{expenseId}")
    public ResponseEntity<DailyExpensesDto> getDailyExpensesById(@PathVariable Long expenseId) {
        logger.info("Getting daily expenses by ID: " + expenseId);
        DailyExpensesDto expensesDto = dailyExpensesService.getDailyExpensesById(expenseId);
        if (expensesDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(expensesDto);
        } else {
            logger.warning("Daily expenses not found with ID: " + expenseId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get a list of all daily expenses.
     *
     * @return ResponseEntity with a list of daily expenses and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<DailyExpensesDto>> getAllDailyExpenses() {
        logger.info("Getting all daily expenses.");
        List<DailyExpensesDto> expensesList = dailyExpensesService.getAllDailyExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(expensesList);
    }

    /**
     * Update a daily expense entry by its ID.
     *
     * @param expenseId        The ID of the daily expenses entry to update.
     * @param dailyExpensesDto The updated daily expenses information.
     * @return ResponseEntity with the updated daily expenses and HTTP status 200 (OK) if updated,
     * or 404 (Not Found) if the daily expenses entry was not found.
     */
    @PutMapping("/{expenseId}")
    public ResponseEntity<DailyExpensesDto> updateDailyExpenses(@PathVariable Long expenseId, @RequestBody DailyExpensesDto dailyExpensesDto) {
        logger.info("Updating daily expenses with ID: " + expenseId);
        DailyExpensesDto updatedExpenses = dailyExpensesService.updateDailyExpenses(expenseId, dailyExpensesDto);
        if (updatedExpenses != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedExpenses);
        } else {
            logger.warning("Daily expenses not found for updating with ID: " + expenseId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Delete a daily expense entry by its ID.
     *
     * @param expenseId The ID of the daily expenses entry to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the daily expenses entry was deleted.
     */
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteDailyExpenses(@PathVariable Long expenseId) {
        logger.info("Deleting daily expenses with ID: " + expenseId);
        dailyExpensesService.deleteDailyExpenses(expenseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * Get a list of branch expenses with staff details, ordered by date in descending order.
     *
     * @return ResponseEntity with a list of branch expenses and HTTP status 200 (OK).
     */
    @GetMapping("/branch-expenses")
    public ResponseEntity<List<BranchExpensesDto>> getBranchExpensesWithStaffDetailsOrderByDateDesc() {
        logger.info("Getting branch expenses with staff details ordered by date.");
        List<BranchExpensesDto> branchExpenses = dailyExpensesService.getBranchExpensesWithStaffDetailsOrderByDateDesc();
        return ResponseEntity.status(HttpStatus.OK).body(branchExpenses);
    }

    /**
     * Get a list of branch expenses performance by month and year.
     *
     * @return ResponseEntity with a list of branch expenses performance and HTTP status 200 (OK).
     */
    @GetMapping("/branch-expenses-performance")
    public ResponseEntity<List<BranchExpensesPerformanceDto>> getBranchExpensesPerformanceByMonthAndYear() {
        logger.info("Getting branch expenses performance by month and year.");
        List<BranchExpensesPerformanceDto> branchExpensesPerformance = dailyExpensesService.getBranchExpensesPerformanceByMonthAndYear();
        return ResponseEntity.status(HttpStatus.OK).body(branchExpensesPerformance);
    }

    @GetMapping("/branch-expenses/{branchCode}")
    public ResponseEntity<List<DailyExpensesDto>> getAllDailySalesByBranchCode(@PathVariable String branchCode) {
        logger.info("Getting branch Expenses by branch code");
        List<DailyExpensesDto> branchExpenses = dailyExpensesService.getBranchExepnsesByBranchCode(branchCode);
        return ResponseEntity.status(HttpStatus.OK).body(branchExpenses);
    }
}
