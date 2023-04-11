package com.flightbooking.Controllers;

import com.flightbooking.Entities.Flight;
import com.flightbooking.Services.Implementations.FlightServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl _flightService;

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody Flight flight) {
        try {
            _flightService.createFlight(flight).join();
            return ResponseEntity.created(null).body("Successfully created flight");
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to create flight: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(Long id, @RequestBody Flight flight) {
        try {
            _flightService.updateFlight(id, flight).join();
            return ResponseEntity.ok("Updated flight whose ID is " + id);
        } catch (RuntimeException runtimeEx) {
            return ResponseEntity
                    .notFound()
                    .header("message", runtimeEx.getMessage())
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to update flight: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlightById(Long id) {
        try {
            _flightService.deleteFlight(id).join();
            return ResponseEntity.ok("Flight deleted whose ID is " + id);
        } catch (RuntimeException runtimeEx) {
            return ResponseEntity
                    .notFound()
                    .header("message", runtimeEx.getMessage())
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to delete flight: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        try {
            List<Flight> flightList = _flightService.getAllFlights().join();
            return ResponseEntity.ok(flightList);
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to get all flights: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(Long id) {
        try {
            Flight flight = _flightService.getFlightById(id).join();
            return ResponseEntity.ok(flight);
        } catch (RuntimeException runtimeEx) {
            return ResponseEntity
                    .notFound()
                    .header("message", runtimeEx.getMessage())
                    .build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
