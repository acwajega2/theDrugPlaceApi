package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "branches")
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branch_id;

    /** The name of the branch. */
    @Column(name = "branch_name")
    @NotNull
    private String branch_name;

    /** The location of the branch. */
    @Column(name = "branch_location")
    private String branch_location;

    /** A unique code for the branch. */
    @Column(name = "branch_code")
    private String branch_code;

    /** The ID of the branch manager. */
    @Column(name = "branch_manager_id")
    private int branch_manager_id;

    /** The date when the branch was opened. */
    @Temporal(TemporalType.DATE)
    @Column(name = "branch_opening_date")
    private Date branch_opening_date;

    /** The timestamp when the branch was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }

    @OneToMany(cascade = CascadeType.MERGE)
    private List<DailySales> dailySales;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<DailyExpenses> dailyExpenses;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<StaffSalary> staffSalaries;

    public Branch(String branch_name, String branch_location, String branch_code, int branch_manager_id, Date branch_opening_date) {
        this.branch_name = branch_name;
        this.branch_location = branch_location;
        this.branch_code = branch_code;
        this.branch_manager_id = branch_manager_id;
        this.branch_opening_date = branch_opening_date;
        // Set the current date and time for createdAt
        this.created_at = LocalDateTime.now();
    }
}
