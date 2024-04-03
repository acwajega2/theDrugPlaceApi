package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the Branch entity in the database.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    /**
     * Custom query to retrieve branch profits by month and year.
     *
     * @return A list of objects containing branch, year, month, and net profit data.
     */
    @Query("SELECT b.branch_id, YEAR(ds.sale_date) as year, MONTH(ds.sale_date) as month, SUM(ds.sale_amount) - COALESCE(SUM(de.expense_amount), 0) - COALESCE(SUM(ss.amount), 0) as netProfit " +
            "FROM Branch b " +
            "LEFT JOIN b.dailySales ds " +
            "LEFT JOIN b.dailyExpenses de " +
            "LEFT JOIN b.staffSalaries ss " +
            "GROUP BY b.branch_id, year, month " +
            "ORDER BY year DESC, month DESC, netProfit DESC")
    List<Object[]> findBranchProfitsByMonthAndYear();

    @Query("SELECT b FROM Branch b WHERE b.branch_name = :branchName")
    Branch findBranchByBranchName(@Param("branchName") String branchName);
    @Query("SELECT b FROM Branch b WHERE b.branch_code = :branchCode")
    Branch findBranchByBranch_code(@Param("branchCode") String branchCode);

}
