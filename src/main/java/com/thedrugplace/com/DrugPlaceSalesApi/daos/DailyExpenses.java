package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "daily_expenses")
public class DailyExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;

    /** The staff associated with the expense. */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    /** The date when the expense was incurred. */
    @Temporal(TemporalType.DATE)
    @Column(name = "expense_date")
    private Date expenseDate;

    /** The amount of the expense. */
    private double expenseAmount;

    /** The category of the expense. */
    @Column(name = "expense_category")
    private String expenseCategory;

    /** The URL of the receipt image. */
    @Column(name = "receipt_image_url")
    private String receiptImageUrl;

    /** The timestamp when the expense was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(); // Set the current date and time
    }
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id") // Adjust the column name as per your schema
    private Branch branch;
}
