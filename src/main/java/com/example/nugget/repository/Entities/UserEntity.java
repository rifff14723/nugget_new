package com.example.nugget.repository.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = { "user_name" }))
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(columnDefinition = "bytea", name = "password")
    //   @Column( name = "password")
    @ToString.Exclude
    private String password;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "failed_login_count")
    private Integer failedLoginCount;

    @Column(name = "locked_state_start_time")
    private Timestamp lockedStateStartTime;

    @Column(name = "first_failed_login_time")
    private Timestamp firstFailedLoginTime;

    @Column(name = "last_failed_login_time")
    private Timestamp lastFailedLoginTime;

    @Column(name = "force_password_change")
    private Boolean forcePasswordChange;

    public UserEntity(String firstName, String lastName, String password, String contactNumber,
                      String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.contactNumber = contactNumber;
        this.userName = userName;
    }

}