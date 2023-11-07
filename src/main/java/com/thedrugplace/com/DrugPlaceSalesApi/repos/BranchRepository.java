package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT b.branchId, YEAR(ds.saleDate) as year, MONTH(ds.saleDate) as month, SUM(ds.saleAmount) - COALESCE(SUM(de.expenseAmount), 0) - COALESCE(SUM(ss.amount), 0) as netProfit " +
            "FROM Branch b " +
            "LEFT JOIN b.dailySales ds " +
            "LEFT JOIN b.dailyExpenses de " +
            "LEFT JOIN b.staffSalaries ss " +
            "GROUP BY b.branchId, year, month " +
            "ORDER BY year DESC, month DESC, netProfit DESC")
    List<Object[]> findBranchProfitsByMonthAndYear();

    Branch findByBranchName(String branchName);
    Branch findByBranchCode(String branchCode);

}
