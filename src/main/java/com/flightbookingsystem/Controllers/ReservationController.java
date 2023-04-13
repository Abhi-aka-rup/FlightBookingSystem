package com.flightbookingsystem.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookingsystem.Entities.Reservation;
import com.flightbookingsystem.Services.Interfaces.IReservationService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("api/reservations")
@Validated
public class ReservationController {
    @Autowired
    private IReservationService _reservationService;

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        List<Reservation> allBookings = _reservationService.getAllBookings().join();
        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("/{reservationNumber}")
    public ResponseEntity<?> getBookingByReservationNumber(@PathVariable @NotBlank Long reservationNumber) {
        Reservation bookingDetails = _reservationService.getBookingByReservationNumber(reservationNumber).join();
        return ResponseEntity.ok(bookingDetails);
    }

    @PostMapping
    public ResponseEntity<?> bookFlight(@RequestBody Reservation reservation) {
        Reservation reservationDetails = _reservationService.bookFlight(reservation.getFlight().getId(),
                reservation.getPassengerName(), reservation.getNumPassengers()).join();
        return ResponseEntity.ok(reservationDetails);
    }

    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<?> deleteBooking(@PathVariable @NotBlank Long reservationNumber) {
        _reservationService.cancelReservation(reservationNumber).join();
        return ResponseEntity.ok(String.format("Reservation ID=%d has been successfully cancelled", reservationNumber));
    }

    @PutMapping("/{reservationNumber}")
    public ResponseEntity<?> updateBooking(@PathVariable @NotBlank Long reservationNumber,
            @RequestBody Reservation reservation) {
        Reservation updateReservationDetail = _reservationService.updateBooking(reservationNumber, reservation).join();
        return ResponseEntity.ok(updateReservationDetail);
    }
}
