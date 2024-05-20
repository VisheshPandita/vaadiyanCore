package dev.vishesh.vaadiyancore.model;

import dev.vishesh.rankList.ItineraryInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Itinerary
 * This class is used to store the itinerary details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Itinerary implements ItineraryInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Integer clickCount;
    private Integer buyCount;
    @OneToMany
    private List<ItineraryDays> itineraryDays;
    private String imageUrl;
    @ManyToOne
    private Location location;
}