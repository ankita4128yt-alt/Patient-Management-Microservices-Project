package com.ankita.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity  // jpa - at start
//@Table(name = "patients")
public class Patient {
    @Id //jpa - at start
    @GeneratedValue(strategy = GenerationType.UUID) // jpa at start + savetime
    //@Column(name = "private_key") by default variable name
    private UUID id;

    // validations - Validation says
    //“Your input is wrong, and here’s exactly why.” clear communication
    //Validation = security checkpoint before entering airport.
    //“Reject bad data early, clearly, and safely.”
    //Database says
    //“Something broke.”
    @NotNull // validations
    @Column(nullable = false) // db [jpa]
    private String name;

    @Email // validation [correct email structure]
    @NotNull // can use not blank also
    @Column(unique = true, nullable = false) //db
    private String email;

    @NotBlank // validation
    @Column(nullable = false) //db [jpa]
    private String address;

    @NotBlank
    @Column(nullable = false )
    private LocalDate dateOfBirth;

    @NotBlank
    @Column(nullable = false )
    private LocalDate registeredDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}

// as i have jpa therefore database is neccessary