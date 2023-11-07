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
    private int salesId;

    /** The staff member associated with the daily sales. */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    /** The date of the sale. */
    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date")
    private Date saleDate;

    /** The amount of the sale. */
    private double saleAmount;

    /** The payment method used for the sale. */
    @Column(name = "payment_method")
    private String paymentMethod;

    /** The transaction reference for the sale. */
    @Column(name = "transaction_reference")
    private String transactionReference;

    /** The timestamp when the sale record was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(); // Set the current date and time
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id") // Adjust the column name as per your schema
    private Branch branch;
}
