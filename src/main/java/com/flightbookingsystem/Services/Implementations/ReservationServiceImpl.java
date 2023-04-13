package com.flightbookingsystem.Services.Implementations;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.flightbookingsystem.Entities.Flight;
import com.flightbookingsystem.Entities.Reservation;
import com.flightbookingsystem.Repositories.FlightRepository;
import com.flightbookingsystem.Repositories.ReservationRepository;
import com.flightbookingsystem.Services.Interfaces.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    FlightRepository _flightRepository;

    @Autowired
    ReservationRepository _reservationRepository;

    @Override
    @Async
    public CompletableFuture<List<Reservation>> getAllBookings() {
        List<Reservation> reservations = _reservationRepository.findAll();

        return CompletableFuture.completedFuture(reservations);
    }

    @Override
    @Async
    public CompletableFuture<Reservation> getBookingByReservationNumber(Long reservationNumber) {
        Reservation reservation = _reservationRepository.findById(reservationNumber).get();

        return CompletableFuture.completedFuture(reservation);
    }

    @Override
    @Async
    public CompletableFuture<Reservation> bookFlight(Long flightId, String passengerName, int numPassengers) {
        // TODO Auto-generated method stub

        Flight flight = _flightRepository.findById(flightId).get();
        if (flight.getAvailableSeats() < numPassengers) {
            throw new RuntimeException("Not enough available seats on this flight");
        }
        Reservation reservation = new Reservation(flightId, flight, passengerName, numPassengers);
        _reservationRepository.saveAndFlush(reservation);
        flight.setAvailableSeats(flight.getAvailableSeats() - numPassengers);
        _flightRepository.saveAndFlush(flight);

        return CompletableFuture.completedFuture(reservation);
    }

    @Override
    @Async
    public CompletableFuture<Reservation> cancelReservation(Long reservationNumber) {
        // TODO Auto-generated method stub
        Reservation existingReservation = _reservationRepository.findById(reservationNumber).get();
        _reservationRepository.delete(existingReservation);

        return CompletableFuture.completedFuture(existingReservation);
    }

    @Override
    public CompletableFuture<Reservation> updateBooking(Long reservationNumber, Reservation reservation) {
        // TODO Auto-generated method stub

        Reservation existingReservation = _reservationRepository.getReferenceById(reservationNumber);
        existingReservation.setNumPassengers(reservation.getNumPassengers());
        existingReservation.setPassengerName(reservation.getPassengerName());
        existingReservation.setFlight(reservation.getFlight());
        _reservationRepository.saveAndFlush(existingReservation);

        return CompletableFuture.completedFuture(existingReservation);
    }

}
