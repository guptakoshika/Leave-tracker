package com.example.leavetracker.entities;

import com.example.leavetracker.enums.Gender;
import lombok.Data;

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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "employee")
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_name")
    @NotNull
    private String employeeName;

    @Column(name = "employee_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "leave_balance", nullable = false)
    private int leaveBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Leave> leaves = new HashSet<>();

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
