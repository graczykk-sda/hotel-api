package com.example.api.accommodations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccommodationControllerTest {

    @Autowired
    private AccommodationController accommodationController;

    @Test
    void shouldCreateAccommodation() {
        RoomUtilization utilization = accommodationController.createAccommodation(new Accommodation(3, 3));
        assertAll(
                () -> assertEquals(3, utilization.premiumUsed()),
                () -> assertEquals(3, utilization.economyUsed()),
                () -> assertEquals(738, utilization.economyGain()),
                () -> assertEquals(167, utilization.premiumGain())
        );
    }
}
