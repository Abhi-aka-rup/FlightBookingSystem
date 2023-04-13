package com.flightbookingsystem.Services.Interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.flightbookingsystem.Entities.Reservation;

public interface IReservationService {
    CompletableFuture<List<Reservation>> getAllBookings();

    CompletableFuture<Reservation> getBookingByReservationNumber(Long reservationNumber);

    CompletableFuture<Reservation> bookFlight(Long flightId, String passengerName, int numPassengers);

    CompletableFuture<Reservation> cancelReservation(Long reservationNumber);

    CompletableFuture<Reservation> updateBooking(Long reservationNumber, Reservation reservation);
}
