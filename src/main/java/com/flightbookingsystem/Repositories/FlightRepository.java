package com.flightbookingsystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbookingsystem.Entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
}
