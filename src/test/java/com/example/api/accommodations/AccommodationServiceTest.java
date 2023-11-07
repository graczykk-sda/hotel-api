package com.example.api.accommodations;

import com.example.api.TestUtils;
import com.example.api.customers.CustomersApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

class AccommodationServiceTest {

    @ParameterizedTest
    @MethodSource("provideAccommodations")
    void shouldPlaceCustomers(Accommodation accommodation, RoomUtilization expectedRoomUtilization) {
        CustomersApi customersApiMock = Mockito.mock(CustomersApi.class);
        when(customersApiMock.getAllCustomers()).thenReturn(TestUtils.defaultCustomerList());

        AccommodationService accommodationService = new AccommodationService(customersApiMock);
        RoomUtilization actualRoomUtilization = accommodationService.placeCustomers(accommodation);

        Assertions.assertEquals(expectedRoomUtilization, actualRoomUtilization);
    }

    private static Stream<Arguments> provideAccommodations() {
        return Stream.of(
                Arguments.of(new Accommodation(3,3), new RoomUtilization(3,3,738,167)),
                Arguments.of(new Accommodation(7,5), new RoomUtilization(6,4,1054,189)),
                Arguments.of(new Accommodation(2,7), new RoomUtilization(2,4,583,189)),
                Arguments.of(new Accommodation(10,1), new RoomUtilization(9,1,1221,22))
        );
    }
}