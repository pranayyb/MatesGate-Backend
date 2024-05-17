package com.matesgate.project.matesgate.repository;

import com.matesgate.project.matesgate.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Bookings, Integer> {
}
