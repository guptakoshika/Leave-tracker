package com.example.leavetracker.repository;

import com.example.leavetracker.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    @Query("select * from Leave l where l.employee.id = ?1")
    List<Leave> getLeaveByEmpId(Long empId);
}
