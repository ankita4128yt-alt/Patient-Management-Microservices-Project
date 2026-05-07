package com.ankita.patientservice.dto;

import com.ankita.patientservice.dto.validators.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "name is required")
    @Size(max =100 , message = "name cannot exceed 100 characters")
    private String name;

    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class},message = "address is required")
    private String address;

    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class},message = "dateOfBirth is required")
    private String dateOfBirth;

    @NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class},message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(groups = {ValidationGroups.Create.class},message = " registered date is required")
    private String registeredDate;

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
