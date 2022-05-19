package com.example.report.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "labtechnicians")
public class LabTechnicians {

    @Id
    @Column(name = "identification_number")
    private String IdentificationNumber;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "roles")
    private String roles;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    public LabTechnicians() {
    }

    public LabTechnicians(String identificationNumber, String firstName, String lastName, String roles,
                          String email, String userName, String password) {
        IdentificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        IdentificationNumber = identificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}