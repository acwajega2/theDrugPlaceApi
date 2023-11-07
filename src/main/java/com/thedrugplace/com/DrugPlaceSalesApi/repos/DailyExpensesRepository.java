package com.thedrugplace.com.DrugPlaceSalesApi.repos;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailyExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT b, s, de.createdAt, SUM(de.expenseAmount) as totalExpenses " +
            "FROM DailyExpenses de " +
            "JOIN de.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, s, de.createdAt " +
            "ORDER BY de.createdAt DESC")
    List<Object[]> findBranchExpensesWithStaffDetailsOrderByDateDesc();

    /**
     * Custom query to retrieve branch expenses performance by month and year, ordered by year, month, and total expenses in descending order.
     *
     * @return A list of objects containing branch, year, month, total expenses, and average expenses data.
     */
    @Query("SELECT b, YEAR(de.createdAt) as year, MONTH(de.createdAt) as month, SUM(de.expenseAmount) as totalExpenses, AVG(de.expenseAmount) as averageExpenses " +
            "FROM DailyExpenses de " +
            "JOIN de.staff s " +
            "JOIN s.branch b " +
            "GROUP BY b, year, month " +
            "ORDER BY year DESC, month DESC, totalExpenses DESC")
    List<Object[]> findBranchExpensesPerformanceByMonthAndYear();

    DailyExpenses findByTransactionReference(String transactionReference);
}
