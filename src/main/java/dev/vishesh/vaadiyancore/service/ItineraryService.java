package dev.vishesh.vaadiyancore.service;

import dev.vishesh.vaadiyancore.dao.BookRequest;
import dev.vishesh.vaadiyancore.dao.ItineraryRequest;
import dev.vishesh.vaadiyancore.model.Itinerary;
import dev.vishesh.vaadiyancore.model.ItineraryDays;
import dev.vishesh.vaadiyancore.model.Location;
import dev.vishesh.vaadiyancore.repository.ItineraryDaysRepository;
import dev.vishesh.vaadiyancore.repository.ItineraryRepository;
import dev.vishesh.vaadiyancore.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static dev.vishesh.rankList.RankList.getRankList;

/**
 * ItineraryService
 * This class is used to handle the itinerary related requests.
 */
@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final ItineraryDaysRepository itineraryDaysRepository;
    private final LocationRepository locationRepository;

    public List<Itinerary> getAllItineraries() {
        List<Itinerary> itineraryList = itineraryRepository.findAll();
        // Rank the itineraries based on the buy count and click count
        return (List<Itinerary>) getRankList(itineraryList);
    }

    public Itinerary createItinerary(ItineraryRequest itinerary) {
        Location location = locationRepository.findByName(itinerary.getLocationName());
        ItineraryDays[] itineraryDays = Arrays.stream(itinerary.getItineraryDays()).map(itineraryDay -> itineraryDaysRepository.save(ItineraryDays.builder()
                .name(itineraryDay.getName())
                .description(itineraryDay.getDescription())
                .imageUrl(itineraryDay.getImageUrl())
                .build())).toArray(ItineraryDays[]::new);
        return itineraryRepository.save(Itinerary.builder()
                .name(itinerary.getName())
                .description(itinerary.getDescription())
                .location(location)
                .itineraryDays(Arrays.asList(itineraryDays))
                .imageUrl(itinerary.getImageUrl())
                .clickCount(0)
                .buyCount(0)
                .build());
    }

    public Itinerary getItinerary(UUID id) {
        itineraryRepository.findById(id).ifPresent(itinerary -> {
            itinerary.setClickCount(itinerary.getClickCount() + 1);
            itineraryRepository.save(itinerary);
        });
        return itineraryRepository.findById(id).orElse(null);
    }

    public List<Itinerary> getItinerariesByLocation(String location) {
        Location locationEntity = locationRepository.findByName(location);
        List<Itinerary> itineraryList =  itineraryRepository.findAllByLocation(locationEntity);
        // Rank the itineraries based on the buy count and click count
        return (List<Itinerary>) getRankList(itineraryList);
    }

    public Itinerary bookItinerary(BookRequest bookRequest) {
        Itinerary itinerary = itineraryRepository.findById(bookRequest.getItineraryId()).orElse(null);
        if (itinerary != null) {
            itinerary.setBuyCount(itinerary.getBuyCount() + 1);
            itineraryRepository.save(itinerary);
        }
        return itinerary;
    }

    @Transactional
    public Itinerary updateItinerary(ItineraryRequest itinerary, UUID id) {
        Location location = locationRepository.findByName(itinerary.getLocationName());
        Itinerary itineraryEntity = itineraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Itinerary not found"));
        itineraryEntity.setName(itinerary.getName());
        itineraryEntity.setDescription(itinerary.getDescription());
        itineraryEntity.setLocation(location);
        itineraryEntity.setImageUrl(itinerary.getImageUrl());
        itineraryEntity.setItineraryDays(Arrays.stream(itinerary.getItineraryDays()).map(itineraryDay -> itineraryDaysRepository.save(ItineraryDays.builder()
                .name(itineraryDay.getName())
                .description(itineraryDay.getDescription())
                .imageUrl(itineraryDay.getImageUrl())
                .build())).toList());
        return itineraryRepository.save(itineraryEntity);
    }

    @Transactional
    public void deleteItinerary(UUID id) {
        itineraryDaysRepository.deleteAll(itineraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Itinerary not found")).getItineraryDays());
        itineraryRepository.deleteById(id);
    }
}
