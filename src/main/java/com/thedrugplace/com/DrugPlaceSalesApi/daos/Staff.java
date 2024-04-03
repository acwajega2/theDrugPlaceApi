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
    @Column(name = "staff_id")
    private Long staff_id;

    /** The branch to which the staff member belongs. */
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    /** The name of the staff member. */
    @Column(name = "staff_name")
    private String staff_name;

    /** The role or position of the staff member. */
    @Column(name = "staff_role")
    private String staff_role;

    /** The email address of the staff member. */
    @Column(name = "staff_email")
    private String staff_email;

    /** The phone number of the staff member. */
    @Column(name = "staff_phone")
    private String staff_phone;

    /** The date when the staff member was hired. */
    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hire_date;

    /** The timestamp when the staff record was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }
}
