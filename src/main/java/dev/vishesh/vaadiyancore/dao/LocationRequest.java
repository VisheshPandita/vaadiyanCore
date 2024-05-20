package dev.vishesh.vaadiyancore.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LocationRequest
 * This class is used to store the location request details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private String name;
    private String country;
    private String subHeading;
    private String description;
    private String imageUrl;
}
