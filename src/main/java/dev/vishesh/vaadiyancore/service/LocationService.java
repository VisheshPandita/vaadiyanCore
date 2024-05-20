package dev.vishesh.vaadiyancore.service;

import dev.vishesh.vaadiyancore.dao.LocationRequest;
import dev.vishesh.vaadiyancore.model.Location;
import dev.vishesh.vaadiyancore.repository.ItineraryRepository;
import dev.vishesh.vaadiyancore.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * LocationService
 * This class is used to handle the location related requests.
 */
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final ItineraryRepository itineraryRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location createLocation(LocationRequest location) {
        return locationRepository.save(Location.builder()
                .name(location.getName())
                .country(location.getCountry())
                .subHeading(location.getSubHeading())
                .description(location.getDescription())
                .imageUrl(location.getImageUrl())
                .build());
    }

    public Location updateLocation(LocationRequest location, UUID id) {
        Location locationEntity = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
        locationEntity.setName(location.getName());
        locationEntity.setCountry(location.getCountry());
        locationEntity.setSubHeading(location.getSubHeading());
        locationEntity.setDescription(location.getDescription());
        locationEntity.setImageUrl(location.getImageUrl());
        return locationRepository.save(locationEntity);
    }

    @Transactional
    public void deleteLocation(UUID id) {
        itineraryRepository.deleteAll(itineraryRepository.findAllByLocation(locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"))));
        locationRepository.deleteById(id);
    }

    public Location getLocation(String name) {
        return locationRepository.findByName(name);
    }
}
