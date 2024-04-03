package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.StaffSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query("SELECT a FROM StaffSalary a  JOIN a.staff b WHERE b.staff_id= :staffId")
    List<StaffSalary> findAllByStaff_StaffId(@Param("staffId") Long staffId);

    /**
     * Retrieves a list of staff salaries by the ID of the associated branch.
     *
     * @param branchId The ID of the branch to filter the results.
     * @return A list of StaffSalary objects associated with the specified branch.
     */
    @Query("SELECT a FROM StaffSalary a JOIN a.branch b WHERE b.branch_id = :branchId")
    List<StaffSalary> findAllByBranch_BranchId(@Param("branchId") Long branchId);

    /**
     * Retrieves a list of staff salaries by the phone number of the associated staff member.
     *
     * @param staffPhone The phone number used to filter the results.
     * @return A list of StaffSalary objects associated with the specified phone number.
     */
    @Query("SELECT a FROM StaffSalary a JOIN a.staff b  WHERE b.staff_phone = :staffPhone")
    List<StaffSalary> findAllByStaff_staffPhone(@Param("staffPhone") String staffPhone);

    @Query("SELECT a FROM StaffSalary a JOIN a.staff b  WHERE b.staff_phone = :staffPhone")
    StaffSalary findAllByStaff_staffPhone2(@Param("staffPhone") String staffPhone);
    @Query("SELECT a FROM StaffSalary a WHERE a.transaction_reference = :transactionReference")
    StaffSalary findByTransactionReference(@Param("transactionReference") String transactionReference);
}
