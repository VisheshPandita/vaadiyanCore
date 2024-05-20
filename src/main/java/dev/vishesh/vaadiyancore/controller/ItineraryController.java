package dev.vishesh.vaadiyancore.controller;

import dev.vishesh.vaadiyancore.dao.BookRequest;
import dev.vishesh.vaadiyancore.dao.ItineraryRequest;
import dev.vishesh.vaadiyancore.model.Itinerary;
import dev.vishesh.vaadiyancore.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * ItineraryController
 * This class is used to handle the itinerary requests.
 */
@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    /**
     * This method is used to get all the itineraries.
     */
    @GetMapping
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        return ResponseEntity.ok(itineraryService.getAllItineraries());
    }

    /**
     * This method is used to create a new itinerary.
     * @param itinerary
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody ItineraryRequest itinerary) {
        return ResponseEntity.ok(itineraryService.createItinerary(itinerary));
    }

    /**
     * This method is used to update an existing itinerary.
     * @param itinerary
     * @param id
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Itinerary> updateItinerary(@RequestBody ItineraryRequest itinerary, @PathVariable UUID id) {
        return ResponseEntity.ok(itineraryService.updateItinerary(itinerary, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteItinerary(@PathVariable UUID id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> getItinerary(@PathVariable UUID id) {
        return ResponseEntity.ok(itineraryService.getItinerary(id));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Itinerary>> getItinerariesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(itineraryService.getItinerariesByLocation(location));
    }

    @PostMapping("/book")
    public ResponseEntity<Itinerary> bookItinerary(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(itineraryService.bookItinerary(bookRequest));
    }
}
