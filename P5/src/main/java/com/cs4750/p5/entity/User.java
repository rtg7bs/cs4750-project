package com.cs4750.p5.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="plan_id")
    private Integer planId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="date_joined")
    private Date dateJoined;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Artist artist;

    // add fkey mapping for planid if implemented (hard-coded default plan_id = 1 for now)

    User() {}

    User(Integer id, Integer planId, String username, String password, String email, Date dateJoined) {
        this.id = id;
        this.planId = planId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }
}
