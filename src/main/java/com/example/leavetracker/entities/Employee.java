package com.example.leavetracker.entities;

import com.example.leavetracker.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeId")
    private int employeeId;

    @Column(name = "EmpName")
    private String employeeName;

    @Column(name = "EmployeeGender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "JoiningDate", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "leaveBalance", nullable = false)
    private int leaveBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmployeeId")
    private Set<Leave> leaves = new HashSet<>();

    /*** constructors */
    public Employee() {}

    /*** getters */
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    /*** setters */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setGender(String gender){
            if(isValidGender(gender))
              this.gender = Gender.valueOf(gender);
            else
                this.gender = Gender.NOT_DEFINED;
    }

    public void setJoiningDate(String joiningDate) throws Exception {
        if(isValidDate(joiningDate)) {
            this.joiningDate = LocalDate.parse(joiningDate);

            if (this.joiningDate.getDayOfMonth() == 1)
                this.leaveBalance = 2;
            else
                this.leaveBalance = 1;
        }
        else
            throw new Exception("invalid date!");
    }

    public void setLeaves(Set<Leave> leaves) {
        this.leaves = leaves;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public String toString() {
        return this.getEmployeeName() + " " + this.getEmployeeId()
                + " " + this.getJoiningDate() + " " + this.getGender();
    }
    public boolean isValidGender(String gender){
        if(gender.equals(Gender.FEMALE) || gender.equals(Gender.MALE))
            return true;
        return false;
    }
    public boolean isValidDate(String joiningDate){
        if(LocalDate.parse(joiningDate).isBefore(LocalDate.parse("2019-08-01")))
        {
            return false;
        }
        else if (LocalDate.parse(joiningDate).isBefore(LocalDate.parse("2019-08-31")))
        {
            return false;
        }
        return true;
    }
}
