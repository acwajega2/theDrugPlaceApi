package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    /** The name of the branch. */
    @Column(name = "branchName")
    private String branchName;

    /** The location of the branch. */
    @Column(name = "branchLocation")
    private String branchLocation;

    /** A unique code for the branch. */
    @Column(name = "branchCode")
    private String branchCode;

    /** The ID of the branch manager. */
    @Column(name = "branch_manager_id")
    private int branchManagerId;

    /** The date when the branch was opened. */
    @Temporal(TemporalType.DATE)
    @Column(name = "branch_opening_date")
    private Date branchOpeningDate;

    /** The timestamp when the branch was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(); // Set the current date and time
    }


    @OneToMany(cascade = CascadeType.MERGE)
    private List<DailySales> dailySales;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<DailyExpenses> dailyExpenses;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<StaffSalary> staffSalaries;
}
