package com.flightbooking.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbooking.Entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
    
}
