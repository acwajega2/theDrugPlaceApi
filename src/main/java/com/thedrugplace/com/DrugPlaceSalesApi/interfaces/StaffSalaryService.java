package com.thedrugplace.com.DrugPlaceSalesApi.interfaces;

import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffSalaryDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing staff salary data.
 */

public interface StaffSalaryService {

    /**
     * Create a new staff salary entry.
     *
     * @param staffSalaryDto The DTO representing the staff salary entry to be created.
     * @return The created staff salary entry DTO.
     */
    StaffSalaryDto createStaffSalary(StaffSalaryDto staffSalaryDto);

    /**
     * Get a list of staff salary entries by staff member's unique identifier.
     *
     * @param staffId The unique identifier of the staff member.
     * @return A list of staff salary entry DTOs.
     */
    List<StaffSalaryDto> getStaffSalariesByStaffId(Long staffId);

    /**
     * Get a list of staff salary entries by branch's unique identifier.
     *
     * @param branchId The unique identifier of the branch.
     * @return A list of staff salary entry DTOs.
     */
    List<StaffSalaryDto> getStaffSalariesByBranchId(Long branchId);

    /**
     * Get a list of staff salary entries by staff member's phone number.
     *
     * @param phoneNumber The phone number of the staff member.
     * @return A list of staff salary entry DTOs.
     */
    List<StaffSalaryDto> getStaffSalariesByStaffPhoneNumber(String phoneNumber);
}
