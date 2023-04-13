package com.flightbookingsystem.Services.Implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.flightbookingsystem.Entities.Flight;
import com.flightbookingsystem.Repositories.FlightRepository;
import com.flightbookingsystem.Services.Interfaces.IFlightService;

import jakarta.persistence.EntityNotFoundException;

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
        Flight existingFlight = _flightRepository.getReferenceById(id);

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setDepartureAirport(flight.getDepartureAirport());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setArrivalAirport(flight.getArrivalAirport());
        existingFlight.setAvailableSeats(flight.getAvailableSeats());
        _flightRepository.saveAndFlush(existingFlight);

        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteFlight(Long id) {
        // TODO: can add validation to verify id property passed by user
        Flight existingFlight = _flightRepository.findById(id).get();
        _flightRepository.delete(existingFlight);

        return CompletableFuture.completedFuture(null);
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
        Flight flight = _flightRepository.findById(id).get();

        return CompletableFuture.completedFuture(flight);
    }

    @Override
    @Async
    public CompletableFuture<List<Flight>> searchFlights(String departureAirport, String arrivalAirport,
            LocalDateTime departureTime) {
        // TODO Auto-generated method stub
        List<Flight> availableFlights = _flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                departureAirport, arrivalAirport, departureTime);
        if (availableFlights.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return CompletableFuture.completedFuture(availableFlights);
    }

}
