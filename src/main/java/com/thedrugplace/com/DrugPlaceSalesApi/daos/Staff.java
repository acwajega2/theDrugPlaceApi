package com.thedrugplace.com.DrugPlaceSalesApi.daos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    /** The branch to which the staff member belongs. */
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    /** The name of the staff member. */
    private String staffName;

    /** The role or position of the staff member. */
    private String staffRole;

    /** The email address of the staff member. */
    private String staffEmail;

    /** The phone number of the staff member. */
    private String staffPhone;

    /** The date when the staff member was hired. */
    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")

    private Date hireDate;

    /** The timestamp when the staff record was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(); // Set the current date and time
    }
}
