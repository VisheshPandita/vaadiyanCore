package dev.vishesh.vaadiyancore.controller;

import dev.vishesh.vaadiyancore.dao.LocationRequest;
import dev.vishesh.vaadiyancore.model.Location;
import dev.vishesh.vaadiyancore.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * LocationController
 * This class is used to handle the location related requests.
 */
@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    /**
     * This method is used to get all the locations.
     * @return List of locations
     */
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    /**
     * This method is used to get the location by name.
     * @param name Name of the location
     * @return Location
     */
    @GetMapping("/{name}")
    public ResponseEntity<Location> getLocation(@PathVariable String name) {
        return ResponseEntity.ok(locationService.getLocation(name));
    }

    /**
     * This method is used to create a new location.
     * @param location Location details
     * @return Location
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Location> createLocation(@RequestBody LocationRequest location) {
        return ResponseEntity.ok(locationService.createLocation(location));
    }

    /**
     * This method is used to update the location.
     * @param location Location details
     * @param id Id of the location
     * @return Location
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Location> updateLocation(@RequestBody LocationRequest location, @PathVariable UUID id) {
        return ResponseEntity.ok(locationService.updateLocation(location, id));
    }

    /**
     * This method is used to delete the location.
     * @param id Id of the location
     * @return Void
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok().build();
    }
}
