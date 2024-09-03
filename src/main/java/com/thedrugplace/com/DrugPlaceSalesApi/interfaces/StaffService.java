package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing staff data.
 */
@Service
public interface StaffService {

    /**
     * Create a new staff entry.
     *
     * @param staffDto The DTO representing the staff entry to be created.
     * @return The created staff entry DTO.
     */
    StaffDto createStaff(StaffDto staffDto);

    /**
     * Get a staff entry by its unique identifier.
     *
     * @param staffId The unique identifier of the staff entry.
     * @return The staff entry DTO.
     */
    StaffDto getStaffById(Long staffId);

    /**
     * Get a list of all staff entries.
     *
     * @return A list of staff entry DTOs.
     */
    List<StaffDto> getAllStaff();

    /**
     * Update an existing staff entry by its unique identifier.
     *
     * @param staffId  The unique identifier of the staff entry to be updated.
     * @param staffDto The DTO representing the updated staff entry.
     * @return The updated staff entry DTO.
     */
    StaffDto updateStaff(Long staffId, StaffDto staffDto);

    /**
     * Delete a staff entry by its unique identifier.
     *
     * @param staffId The unique identifier of the staff entry to be deleted.
     */
    void deleteStaff(Long staffId);

    /**
     * Search for staff entries by phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return A list of staff entry DTOs matching the provided phone number.
     */
    List<StaffDto> searchStaffByPhoneNumber(String phoneNumber);

    /**
     * Get a list of staff entries by branch code.
     *
     * @param branchCode The branch code to filter by.
     * @return A list of staff entry DTOs belonging to the specified branch.
     */
    @Query("SELECT a,b.branch_code Staff a JOIN a.branch b WHERE b.branch_code = :branchCode")
    List<StaffDto> getStaffByBranch_code(@Param("branchCode") String branchCode);

    @Query("SELECT * from Staff where username = :username")
    Staff getStaffByUsername(@Param("username") String username);
}
