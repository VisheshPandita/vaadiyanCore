package dev.vishesh.vaadiyancore.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItineraryDaysRequest
 * This class is used to store the itinerary days request details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDaysRequest {
    private String name;
    private String description;
    private String imageUrl;
}
