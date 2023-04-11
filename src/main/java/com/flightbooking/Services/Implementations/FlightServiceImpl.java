package com.flightbooking.Services.Implementations;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.flightbooking.Entities.Flight;
import com.flightbooking.Repositories.FlightRepository;
import com.flightbooking.Services.Interfaces.IFlightService;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private FlightRepository _flightRepository;

    @Override
    @Async
    public CompletableFuture<Void> createFlight(Flight flight) {
        // TODO: can add validation to verify flight property passed by user

        _flightRepository.saveAndFlush(flight);

        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Void> updateFlight(Long id, Flight flight) {
        // TODO: can add validation to verify id and flight property passed by user

        return _flightRepository.findById(id)
                .map(existingFlight -> {
                    existingFlight.setFlightNumber(flight.getFlightNumber());
                    existingFlight.setDepartureTime(flight.getDepartureTime());
                    existingFlight.setDepartureAirport(flight.getDepartureAirport());
                    existingFlight.setArrivalTime(flight.getArrivalTime());
                    existingFlight.setArrivalAirport(flight.getArrivalAirport());
                    existingFlight.setAvailableSeats(flight.getAvailableSeats());
                    _flightRepository.saveAndFlush(existingFlight);
                    return CompletableFuture.completedFuture(null);
                })
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id))
                .thenApply(result -> null);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteFlight(Long id) {
        // TODO: can add validation to verify id property passed by user

        return _flightRepository.findById(id)
                .map(existingFlight -> {
                    _flightRepository.delete(existingFlight);
                    return CompletableFuture.completedFuture(null);
                })
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id))
                .thenApply(result -> null);
    }

    @Override
    @Async
    public CompletableFuture<List<Flight>> getAllFlights() {
        List<Flight> flightList = _flightRepository.findAll();

        return CompletableFuture.completedFuture(flightList);
    }

    @Override
    @Async
    public CompletableFuture<Flight> getFlightById(Long id) {
        // TODO: can add validation to verify id property passed by user

        return _flightRepository.findById(id)
                .map(existingFlight -> {
                    return CompletableFuture.completedFuture(existingFlight);
                })
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id))
                .thenApply(result -> result);
    }

}
