package com.mastery.java.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "employees")
@ApiModel(description = "Details about employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique id of the employee", required = true)
    private Long employeeId;

    @Column(nullable = false, name = "first_name")
    @NotBlank(message = "Employee's first name cannot be null or empty")
    @Size(min = 2, max = 100, message = "Employee's first name should have from 3 to 100 signs")
    @ApiModelProperty(notes = "Employee's name (2-100 characters)", required = true)
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @NotBlank(message = "Employee's last name cannot be null or empty")
    @Size(min = 2, max = 100, message = "Employee's last name should have from 3 to 100 signs")
    @ApiModelProperty(notes = "Employee's surname (2-100 characters)", required = true)
    private String lastName;

    @Column(nullable = false, name = "department_id")
    @NotNull(message = "Employee's department id cannot be null")
    @PositiveOrZero(message = "Employee's department id cannot be negative")
    @ApiModelProperty(notes = "Id of employee's department (not negative)", required = true)
    private Long departmentId;

    @Column(nullable = false, name = "job_title")
    @NotBlank(message = "Employee's job title cannot be null")
    @ApiModelProperty(notes = "Employee's job title", required = true)
    private String jobTitle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee's gender cannot be null")
    @ApiModelProperty(notes = "Employee's gender (MALE or FEMALE)", required = true)
    private Gender gender;

    @Column(nullable = false, name = "date_of_birth")
    @NotNull(message = "Employee's birth date cannot be null")
    @Past(message = "Employee cannot be born today or in future")
    @ApiModelProperty(notes = "Employee's birth date", required = true)
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Long departmentId, String jobTitle, Gender gender,
                    LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long employeeId, String firstName, String lastName, Long departmentId, String jobTitle,
                    Gender gender, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
