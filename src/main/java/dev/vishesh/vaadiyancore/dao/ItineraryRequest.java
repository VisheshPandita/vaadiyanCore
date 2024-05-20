package dev.vishesh.vaadiyancore.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItineraryRequest
 * This class is used to store the itinerary request details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryRequest {
    private String name;
    private String description;
    private String locationName;
    private String imageUrl;
    private ItineraryDaysRequest[] itineraryDays;
}
