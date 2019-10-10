package com.example.leavetracker.Repo;

import com.example.leavetracker.Models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {
}
