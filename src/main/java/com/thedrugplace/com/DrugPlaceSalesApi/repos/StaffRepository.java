package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for interacting with the Staff entity in the database.
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {
    /**
     * Custom query to retrieve a list of staff members by the branch code they are associated with.
     *
     * @param branchCode The branch code used to filter the results.
     * @return A list of Staff objects associated with the specified branch code.
     */
    @Query("SELECT a FROM Staff a JOIN a.branch b WHERE b.branch_code = :branchCode")
    List<Staff> findByBranch_BranchCode(@Param("branchCode") String branchCode);

    /**
     * Custom query to retrieve a list of staff members by their phone number.
     *
     * @param staffPhone The phone number used to filter the results.
     * @return A list of Staff objects with the specified phone number.
     */
    @Query("SELECT a FROM Staff a WHERE a.staff_phone = :staffPhone")
    List<Staff> findAllByStaffPhone(@Param("staffPhone") String staffPhone);

    @Query("SELECT a FROM Staff a WHERE a.staff_phone = :staffPhone")
    Staff findByStaffPhone(@Param("staffPhone") String staffPhone);

}
