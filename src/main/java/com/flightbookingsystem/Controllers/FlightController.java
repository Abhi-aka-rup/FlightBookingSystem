package com.flightbookingsystem.Controllers;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookingsystem.Entities.Flight;
import com.flightbookingsystem.Services.Interfaces.IFlightService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("api/flights")
@Validated
public class FlightController {

    @Autowired
    private IFlightService _flightService;

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody Flight flight) {
        _flightService.createFlight(flight).join();
        return ResponseEntity.created(null).body("Successfully created new flight");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable @NotBlank Long id, @RequestBody Flight flight) {
        _flightService.updateFlight(id, flight).join();
        return ResponseEntity.ok(String.format("Flight with ID-%d has been successfully updated", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlightById(@PathVariable @NotBlank Long id) {
        _flightService.deleteFlight(id).join();
        return ResponseEntity.ok(String.format("Flight with ID-%d has been successfully deleted", id));
    }

    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        List<Flight> flightList = _flightService.getAllFlights().join();
        return ResponseEntity.ok(flightList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable @NotBlank Long id) {
        Flight flight = _flightService.getFlightById(id).join();
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/")
    public ResponseEntity<?> searchFlights(@RequestParam String departureAirport, @RequestParam String arrivalAirport,
            @RequestParam LocalDateTime departureTime) {
        List<Flight> availableFlights = _flightService
                .searchFlights(departureAirport, arrivalAirport, departureTime).join();
        return ResponseEntity.ok(availableFlights);
    }

}
