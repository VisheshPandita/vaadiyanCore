package dev.vishesh.vaadiyancore.repository;

import dev.vishesh.vaadiyancore.model.Itinerary;
import dev.vishesh.vaadiyancore.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * ItineraryRepository
 * This class is used to handle the database operations related to the itinerary.
 */
@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, UUID> {
    List<Itinerary> findAllByLocation(Location location);
}
