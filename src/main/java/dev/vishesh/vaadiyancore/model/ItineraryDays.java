package dev.vishesh.vaadiyancore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * ItineraryDays
 * This class is used to store the itinerary days details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItineraryDays {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
}
