package com.example.api.hotel;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accommodation")
class AccommodationApi {

    @PostMapping
    RoomUtilization createAccommodation(@RequestBody Accommodation accommodation) {
        return new RoomUtilization(1, 1, 1, 1);
    }
}
