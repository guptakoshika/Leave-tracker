package com.example.leavetracker.repository;

import com.example.leavetracker.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {
}
