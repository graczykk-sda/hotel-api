package com.example.api.accommodations;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accommodation")
class AccommodationController {

    @PostMapping
    RoomUtilization createAccommodation(@RequestBody Accommodation accommodation) {
        return new RoomUtilization(1, 1, 1, 1);
    }
}
