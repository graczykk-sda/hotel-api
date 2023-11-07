package com.example.api.accommodations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accommodation")
class AccommodationController {

    private final AccommodationService accommodationService;

    AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @PostMapping
    RoomUtilization createAccommodation(@RequestBody Accommodation accommodation) {
        return accommodationService.placeCustomers(accommodation);
    }
}
