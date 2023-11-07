package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffSalaryDto;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/staff/salary")
public class StaffSalaryController {
    private static final Logger logger = Logger.getLogger(StaffSalaryController.class.getName());

    private final StaffSalaryService staffSalaryService;

    @Autowired
    public StaffSalaryController(StaffSalaryService staffSalaryService) {
        this.staffSalaryService = staffSalaryService;
    }

    /**
     * Create a new staff salary entry.
     *
     * @param staffSalaryDto The DTO containing staff salary information.
     * @return ResponseEntity with the created staff salary and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<StaffSalaryDto> createStaffSalary(@RequestBody StaffSalaryDto staffSalaryDto) {
        logger.info("Creating a new staff salary entry.");
        StaffSalaryDto createdStaffSalary = staffSalaryService.createStaffSalary(staffSalaryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaffSalary);
    }

    /**
     * Get a list of staff salaries by staff ID.
     *
     * @param staffId The ID of the staff to retrieve salaries for.
     * @return ResponseEntity with a list of staff salaries and HTTP status 200 (OK).
     */
    @GetMapping("/by-staff/{staffId}")
    public ResponseEntity<List<StaffSalaryDto>> getStaffSalariesByStaffId(@PathVariable Long staffId) {
        logger.info("Getting staff salaries for staff with ID: " + staffId);
        List<StaffSalaryDto> staffSalaries = staffSalaryService.getStaffSalariesByStaffId(staffId);
        return ResponseEntity.status(HttpStatus.OK).body(staffSalaries);
    }

    /**
     * Get a list of staff salaries by branch ID.
     *
     * @param branchId The ID of the branch to retrieve staff salaries for.
     * @return ResponseEntity with a list of staff salaries and HTTP status 200 (OK).
     */
    @GetMapping("/by-branch/{branchId}")
    public ResponseEntity<List<StaffSalaryDto>> getStaffSalariesByBranchId(@PathVariable Long branchId) {
        logger.info("Getting staff salaries for branch with ID: " + branchId);
        List<StaffSalaryDto> staffSalaries = staffSalaryService.getStaffSalariesByBranchId(branchId);
        return ResponseEntity.status(HttpStatus.OK).body(staffSalaries);
    }

    /**
     * Get a list of staff salaries by staff phone number.
     *
     * @param phoneNumber The phone number to search for staff salaries.
     * @return ResponseEntity with a list of staff salaries and HTTP status 200 (OK).
     */
    @GetMapping("/by-staff-phone")
    public ResponseEntity<List<StaffSalaryDto>> getStaffSalariesByStaffPhoneNumber(@RequestParam String phoneNumber) {
        logger.info("Getting staff salaries for staff with phone number: " + phoneNumber);
        List<StaffSalaryDto> staffSalaries = staffSalaryService.getStaffSalariesByStaffPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(staffSalaries);
    }
}
