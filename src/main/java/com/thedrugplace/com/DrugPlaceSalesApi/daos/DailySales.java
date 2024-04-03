package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "daily_sales")
public class DailySales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private int sales_id;

    /** The staff member associated with the daily sales. */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    /** The date of the sale. */
    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date")
    private Date sale_date;

    /** The amount of the sale. */
    @Column(name = "sale_amount")
    private double sale_amount;

    /** The payment method used for the sale. */
    @Column(name = "payment_method")
    private String payment_method;

    /** The transaction reference for the sale. */
    @Column(name = "transaction_reference")
    private String transaction_reference;

    /** The timestamp when the sale record was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id") // Adjust the column name as per your schema
    private Branch branch;
}
