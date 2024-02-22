package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchProfitDto;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
    private static final Logger logger = Logger.getLogger(BranchController.class.getName());

    @Autowired
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    /**
     * Create a new branch.
     *
     * @param branchDto The DTO containing branch information.
     * @return ResponseEntity with the created branch and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        logger.info("Creating a new branch.");
        BranchDto createdBranch = branchService.createBranch(branchDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }

    /**
     * Get a branch by its ID.
     *
     * @param branchId The ID of the branch to retrieve.
     * @return ResponseEntity with the branch information and HTTP status 200 (OK) if found,
     * or 404 (Not Found) if not found.
     */
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long branchId) {
        logger.info("Getting branch by ID: " + branchId);
        BranchDto branchDto = branchService.getBranchById(branchId);
        if (branchDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(branchDto);
        } else {
            logger.warning("Branch not found with ID: " + branchId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get a list of all branches.
     *
     * @return ResponseEntity with a list of branches and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        logger.info("Getting all branches.");
        List<BranchDto> branches = branchService.getAllBranches();
        return ResponseEntity.status(HttpStatus.OK).body(branches);
    }

    /**
     * Update a branch by its ID.
     *
     * @param branchId  The ID of the branch to update.ddd
     * @param branchDto The updated branch information.
     * @return ResponseEntity with the updated branch and HTTP status 200 (OK) if updated,
     * or 404 (Not Found) if the branch was not found.
     */
    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long branchId, @RequestBody BranchDto branchDto) {
        logger.info("Updating branch with ID: " + branchId);
        BranchDto updatedBranch = branchService.updateBranch(branchId, branchDto);
        if (updatedBranch != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedBranch);
        } else {
            logger.warning("Branch not found for updating with ID: " + branchId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Delete a branch by its ID.
     *
     * @param branchId The ID of the branch to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the branch was deleted.
     */
    @DeleteMapping("/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long branchId) {
        logger.info("Deleting branch with ID: " + branchId);
        branchService.deleteBranch(branchId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * Get a list of profitable branches by month and year.
     *
     * @return ResponseEntity with a list of profitable branches and HTTP status 200 (OK).
     */
    @GetMapping("/profits")
    public ResponseEntity<List<BranchProfitDto>> getProfitableBranchesByMonthAndYear() {
        logger.info("Getting profitable branches by month and year.");
        List<BranchProfitDto> profitableBranches = branchService.getProfitableBranchesByMonthAndYear();
        return ResponseEntity.status(HttpStatus.OK).body(profitableBranches);
    }
}
