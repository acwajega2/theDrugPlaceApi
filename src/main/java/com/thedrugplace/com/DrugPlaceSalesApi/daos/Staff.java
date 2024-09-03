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

    /**
     * The branch to which the staff member belongs.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    /**
     * The name of the staff member.
     */
    @Column(name = "staff_name", nullable = false)
    private String staff_name;

    /**
     * The username of the staff member used for authentication.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * The hashed password of the staff member used for authentication.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The role or position of the staff member.
     */
    @Column(name = "staff_role", nullable = false)
    private String staff_role;

    /**
     * The email address of the staff member.
     */
    @Column(name = "staff_email", nullable = false, unique = true)
    private String staff_email;

    /**
     * The phone number of the staff member.
     */
    @Column(name = "staff_phone", nullable = false)
    private String staff_phone;

    /**
     * The date when the staff member was hired.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hire_date;

    /**
     * The timestamp when the staff record was created.
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now(); // Set the current date and time
    }
}
