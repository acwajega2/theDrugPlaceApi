package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailyExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the DailyExpenses entity in the database.
 */
@Repository
public interface DailyExpensesRepository extends JpaRepository<DailyExpenses, Long> {
    /**
     * Custom query to retrieve branch expenses with staff details, ordered by date in descending order.
     *
     * @return A list of objects containing branch, staff, date, and total expenses data.
     */
    @Query("SELECT b, s, de.created_at, SUM(de.expense_amount) as totalExpenses " +
            "FROM DailyExpenses de " +
            "JOIN de.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, s, de.created_at " +
            "ORDER BY de.created_at DESC")
    List<Object[]> findBranchExpensesWithStaffDetailsOrderByDateDesc();

    /**
     * Custom query to retrieve branch expenses performance by month and year, ordered by year, month, and total expenses in descending order.
     *
     * @return A list of objects containing branch, year, month, total expenses, and average expenses data.
     */
    @Query("SELECT b, YEAR(de.created_at) as year, MONTH(de.created_at) as month, SUM(de.expense_amount) as totalExpenses, AVG(de.expense_amount) as averageExpenses " +
            "FROM DailyExpenses de " +
            "JOIN de.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, year, month " +
            "ORDER BY year DESC, month DESC, totalExpenses DESC")
    List<Object[]> findBranchExpensesPerformanceByMonthAndYear();


    @Query("SELECT de FROM DailyExpenses de JOIN FETCH de.staff s JOIN FETCH de.branch b WHERE b.branch_code = :branchCode")
    List<DailyExpenses> findExpensesByBranchCode(@Param("branchCode") String branchCode);


    @Query("SELECT b FROM DailyExpenses b WHERE b.transaction_reference = :transactionReference")
    DailyExpenses findByTransactionReference(@Param("transactionReference") String transactionReference);
}
