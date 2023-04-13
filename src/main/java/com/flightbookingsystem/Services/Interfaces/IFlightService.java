package com.flightbookingsystem.Services.Interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.flightbookingsystem.Entities.Flight;

public interface IFlightService {
    CompletableFuture<Void> createFlight(Flight flight);
    CompletableFuture<Void> updateFlight(Long id, Flight flight);
    CompletableFuture<Void> deleteFlight(Long id);

    CompletableFuture<List<Flight>> getAllFlights();
    CompletableFuture<Flight> getFlightById(Long id);
    CompletableFuture<List<Flight>> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime);
}
