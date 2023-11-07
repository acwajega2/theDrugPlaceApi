package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.StaffSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for interacting with the StaffSalary entity in the database.
 */
public interface StaffSalaryRepository extends JpaRepository<StaffSalary, Long> {
    /**
     * Retrieves a list of staff salaries by the ID of the associated staff member.
     *
     * @param staffId The ID of the staff member to filter the results.
     * @return A list of StaffSalary objects associated with the specified staff member.
     */
    List<StaffSalary> findAllByStaff_StaffId(Long staffId);

    /**
     * Retrieves a list of staff salaries by the ID of the associated branch.
     *
     * @param branchId The ID of the branch to filter the results.
     * @return A list of StaffSalary objects associated with the specified branch.
     */
    List<StaffSalary> findAllByBranch_BranchId(Long branchId);

    /**
     * Retrieves a list of staff salaries by the phone number of the associated staff member.
     *
     * @param staffPhone The phone number used to filter the results.
     * @return A list of StaffSalary objects associated with the specified phone number.
     */
    List<StaffSalary> findAllByStaff_staffPhone(String staffPhone);

    StaffSalary findByTransactionReference(String transactionReference);
}
