package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailySales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the DailySales entity in the database.
 */
@Repository
public interface DailySalesRepository extends JpaRepository<DailySales, Long> {
    /**
     * Custom query to retrieve daily sales with staff and branch details by staff phone number.
     *
     * @param staffPhone The phone number of the staff to filter the results.
     * @return A list of DailySales objects with associated staff and branch details.
     */
    @Query("SELECT ds FROM DailySales ds " +
            "JOIN FETCH ds.staff s " +
            "JOIN FETCH s.branch b " +
            "WHERE s.staffPhone = :staffPhone")
    List<DailySales> findSalesByStaffPhoneNumberWithBranchDetails(@Param("staffPhone") String staffPhone);

    /**
     * Custom query to retrieve branch sales with staff details, ordered by date in descending order.
     *
     * @return A list of objects containing branch, staff, date, and total expenses data.
     */
    @Query("SELECT b, s, de.expenseDate, SUM(de.expenseAmount) as totalExpenses " +
            "FROM DailyExpenses de " +
            "JOIN de.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, s, de.expenseDate " +
            "ORDER BY de.expenseDate DESC")
    List<Object[]> findBranchSalesWithStaffDetailsOrderByDateDesc();

    /**
     * Custom query to retrieve branch performance by month and year, ordered by year, month, and total sales in descending order.
     *
     * @return A list of objects containing branch, year, month, total sales, and average sales data.
     */
    @Query("SELECT b, YEAR(ds.saleDate) as year, MONTH(ds.saleDate) as month, SUM(ds.saleAmount) as totalSales, AVG(ds.saleAmount) as averageSales " +
            "FROM DailySales ds " +
            "JOIN ds.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, year, month " +
            "ORDER BY year DESC, month DESC, totalSales DESC")
    List<Object[]> findBranchPerformanceByMonthAndYear();

    /**
     * Custom query to retrieve the best performing staff by month and year, ordered by year, month, and total sales in descending order.
     *
     * @return A list of objects containing staff, year, month, and total sales data.
     */
    @Query("SELECT s, YEAR(ds.saleDate) as year, MONTH(ds.saleDate) as month, SUM(ds.saleAmount) as totalSales " +
            "FROM DailySales ds " +
            "JOIN ds.staff s " +
            "GROUP BY s, year, month " +
            "ORDER BY year DESC, month DESC, totalSales DESC")
    List<Object[]> findBestPerformingStaffByMonthAndYear();

    DailySales findByTransactionReference(String transactionReference);
}
