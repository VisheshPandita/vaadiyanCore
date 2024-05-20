package dev.vishesh.vaadiyancore.repository;

import dev.vishesh.vaadiyancore.model.ItineraryDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ItineraryDaysRepository
 * This class is used to handle the database operations related to the itinerary days.
 */
@Repository
public interface ItineraryDaysRepository extends JpaRepository<ItineraryDays, UUID>{
}
