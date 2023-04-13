package com.flightbookingsystem.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbookingsystem.Entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTime(String departureAirport, String arrivalAirport, LocalDateTime departureTime);
}
