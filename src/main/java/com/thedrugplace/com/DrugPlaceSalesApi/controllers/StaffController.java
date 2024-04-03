package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    private static final Logger logger = Logger.getLogger(StaffController.class.getName());

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * Create a new staff member.
     *
     * @param staffDto The DTO containing staff information.
     * @return ResponseEntity with the created staff and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<StaffDto> createStaff(@RequestBody StaffDto staffDto) {
        logger.info("Creating a new staff member.");
        StaffDto createdStaff = staffService.createStaff(staffDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
    }

    /**
     * Get a staff member by their ID.
     *
     * @param staffId The ID of the staff member to retrieve.
     * @return ResponseEntity with the staff member's information and HTTP status 200 (OK) if found,
     * or 404 (Not Found) if not found.
     */
    @GetMapping("/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long staffId) {
        logger.info("Getting staff member by ID: " + staffId);
        StaffDto staffDto = staffService.getStaffById(staffId);
        if (staffDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(staffDto);
        } else {
            logger.warning("Staff member not found with ID: " + staffId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get a list of all staff members.
     *
     * @return ResponseEntity with a list of staff members and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        logger.info("Getting all staff members.");
        List<StaffDto> staffList = staffService.getAllStaff();
        return ResponseEntity.status(HttpStatus.OK).body(staffList);
    }

    /**
     * Update a staff member by their ID.
     *
     * @param staffId   The ID of the staff member to update.
     * @param staffDto  The updated staff member information.
     * @return ResponseEntity with the updated staff member and HTTP status 200 (OK) if updated,
     * or 404 (Not Found) if the staff member was not found.
     */
    @PutMapping("/{staffId}")
    public ResponseEntity<StaffDto> updateStaff(@PathVariable Long staffId, @RequestBody StaffDto staffDto) {
        logger.info("Updating staff member with ID: " + staffId);
        StaffDto updatedStaff = staffService.updateStaff(staffId, staffDto);
        if (updatedStaff != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedStaff);
        } else {
            logger.warning("Staff member not found for updating with ID: " + staffId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Delete a staff member by their ID.
     *
     * @param staffId The ID of the staff member to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the staff member was deleted.
     */
    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long staffId) {
        logger.info("Deleting staff member with ID: " + staffId);
        staffService.deleteStaff(staffId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * Get a list of staff members by branch code.
     *
     * @param branchCode The branch code to filter staff members.
     * @return ResponseEntity with a list of staff members and HTTP status 200 (OK).
     */
    @GetMapping("/by-branch-code/{branchCode}")
    public ResponseEntity<List<StaffDto>> getStaffByBranchCode(@PathVariable("branchCode") String branchCode) {
        logger.info("Getting staff members by branch code: " + branchCode);
        List<StaffDto> staffList = staffService.getStaffByBranch_code(branchCode);
        return ResponseEntity.status(HttpStatus.OK).body(staffList);
    }

    /**
     * Search for staff members by their phone number.
     *
     * @param phoneNumber The phone number to search for staff members.
     * @return ResponseEntity with a list of staff members and HTTP status 200 (OK).
     */
    @GetMapping("/by-phone/{phoneNumber}")
    public ResponseEntity<List<StaffDto>> searchStaffByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        logger.info("Searching for staff members by phone number: " + phoneNumber);
        List<StaffDto> staffList = staffService.searchStaffByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(staffList);
    }
}
