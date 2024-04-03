package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchProfitDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing branches.
 */

public interface BranchService {

    /**
     * Create a new branch.
     *
     * @param branchDto The DTO representing the branch to be created.
     * @return The created branch DTO.
     */
    BranchDto createBranch(BranchDto branchDto);

    /**
     * Get a branch by its unique identifier.
     *
     * @param branchId The unique identifier of the branch.
     * @return The branch DTO, or null if not found.
     */
    BranchDto getBranchById(Long branchId);

    /**
     * Get a list of all branches.
     *
     * @return A list of branch DTOs.
     */
    @Query("SELECT b from Branch b")
    List<BranchDto> getAllBranches();

    /**
     * Update an existing branch.
     *
     * @param branchId  The unique identifier of the branch to be updated.
     * @param branchDto The DTO representing the updated branch data.
     * @return The updated branch DTO, or null if the branch doesn't exist.
     */
    BranchDto updateBranch(Long branchId, BranchDto branchDto);

    /**
     * Delete a branch by its unique identifier.
     *
     * @param branchId The unique identifier of the branch to be deleted.
     */
    void deleteBranch(Long branchId);

    /**
     * Get a list of profitable branches by month and year.
     *
     * @return A list of BranchProfitDto representing profitable branches.
     */
    List<BranchProfitDto> getProfitableBranchesByMonthAndYear();
}
