package com.example.api.accommodations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccommodationControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("provideAccommodations")
    void shouldCreateAccommodation(Accommodation accommodation, RoomUtilization expectedRoomUtilization) throws Exception {

        String json = objectMapper.writeValueAsString(accommodation);
        MvcResult mvcResult = this.mockMvc.perform(
                        post("/v1/accommodation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andDo(print())
                .andExpect(status().isCreated()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        RoomUtilization roomUtilization = objectMapper.readValue(responseJson, RoomUtilization.class);
        Assertions.assertEquals(expectedRoomUtilization, roomUtilization);
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
