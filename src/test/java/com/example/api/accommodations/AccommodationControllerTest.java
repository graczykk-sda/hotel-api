package com.example.api.accommodations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccommodationController.class)
@AutoConfigureMockMvc
public class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccommodationService accommodationService;

    @Test
    void shouldInvokeCreateAccommodation() throws Exception {
        Accommodation givenAccommodation = new Accommodation(3, 3);
        when(accommodationService.placeCustomers(givenAccommodation)).thenReturn(new RoomUtilization(1, 1, 100, 100));

        String json = objectMapper.writeValueAsString(givenAccommodation);
        this.mockMvc.perform(
                        post("/v1/accommodation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andDo(print())
                .andExpect(status().isCreated());
    }
}
