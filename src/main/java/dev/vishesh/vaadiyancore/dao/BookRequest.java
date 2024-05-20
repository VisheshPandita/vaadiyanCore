package dev.vishesh.vaadiyancore.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * BookRequest
 * This class is used to store the book request details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private UUID itineraryId;
}
