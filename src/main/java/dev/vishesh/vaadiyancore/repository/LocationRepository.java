package dev.vishesh.vaadiyancore.repository;

import dev.vishesh.vaadiyancore.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * LocationRepository
 * This class is used to handle the database operations related to the location.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    Location findByName(String name);
}
