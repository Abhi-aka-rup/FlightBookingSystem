package com.flightbookingsystem.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "num_passengers")
    private int numPassengers;

    public Reservation() {
    }

    public Reservation(Long reservationNumber, Flight flight, String passengerName, int numPassengers) {
        this.reservationNumber = reservationNumber;
        this.flight = flight;
        this.passengerName = passengerName;
        this.numPassengers = numPassengers;
    }

    public Long getReservationNumber() {
        return this.reservationNumber;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getNumPassengers() {
        return this.numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }
}
