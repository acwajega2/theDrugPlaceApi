package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<Staff> findByBranch_BranchCode(String branchCode);

    /**
     * Custom query to retrieve a list of staff members by their phone number.
     *
     * @param staffPhone The phone number used to filter the results.
     * @return A list of Staff objects with the specified phone number.
     */
    List<Staff> findAllByStaffPhone(String staffPhone);

    Staff findByStaffPhone(String staffPhone);

}
