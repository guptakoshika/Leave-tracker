package com.example.leavetracker.Repo;

import com.example.hashedin.leavetracker.hu16javaleavetracker.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {
}
