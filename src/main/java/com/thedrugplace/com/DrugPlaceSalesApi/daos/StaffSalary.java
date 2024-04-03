package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "staff_salaries") // Specify the table name
public class StaffSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_salary_id")
    private Long staff_salary_id;

    /**
     * The staff member associated with this salary record.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id") // Adjust the column name as per your schema
    private Staff staff;

    /** The branch to which the staff belongs and received the salary. */


    /**
     * The amount of the staff member's salary.
     */
    @Column(name = "amount")
    private double amount;

    /**
     * The date when the salary payment was made.
     */
    @Column(name = "payment_date")
    private LocalDate payment_date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_id") // Adjust the column name as per your schema
    private Branch branch;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "transaction_reference")
    private String transaction_reference;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }
}
