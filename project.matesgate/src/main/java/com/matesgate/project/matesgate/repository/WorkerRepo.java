package com.matesgate.project.matesgate.repository;

import com.matesgate.project.matesgate.model.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends JpaRepository<WorkerProfile, Integer> {
}
