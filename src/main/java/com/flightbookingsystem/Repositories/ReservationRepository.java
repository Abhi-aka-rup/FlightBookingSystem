package com.flightbookingsystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbookingsystem.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
