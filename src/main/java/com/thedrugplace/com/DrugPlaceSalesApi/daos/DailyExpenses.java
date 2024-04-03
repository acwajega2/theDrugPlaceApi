package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "daily_expenses")
@NoArgsConstructor
@AllArgsConstructor
public class DailyExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="expense_id")
    private int expense_id;

    /** The staff associated with the expense. */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    /** The date when the expense was incurred. */
    @Temporal(TemporalType.DATE)
    @Column(name = "expense_date")
    private Date expense_date;

    /** The amount of the expense. */
    private double expense_amount;

    /** The category of the expense. */
    @Column(name = "expense_category")
    private String expense_category;

    /** The URL of the receipt image. */
    @Column(name = "receipt_image_url")
    private String receipt_image_url;

    /** The timestamp when the expense was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "transaction_reference")
    private String transaction_reference;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id") // Adjust the column name as per your schema
    private Branch branch;
}
