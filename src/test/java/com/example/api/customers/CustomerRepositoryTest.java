package com.example.api.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@WireMockTest
class CustomerRepositoryTest {

    @Test
    void shouldGetAllCustomers(WireMockRuntimeInfo wireMockRuntimeInfo) throws IOException {
        WireMock.stubFor(
                WireMock.get("/")
                        .willReturn(
                                aResponse().withBodyFile("customers.json")
                        )
        );
        CustomerRepository customerRepository =
                new CustomerRepository("http://localhost:" + wireMockRuntimeInfo.getHttpPort(), new ObjectMapper());

        List<Customer> allCustomers = customerRepository.getAllCustomers();
        List<Customer> expectedCustomers = List.of(
                new Customer(23), new Customer(45), new Customer(155), new Customer(374), new Customer(22),
                new Customer(99), new Customer(100), new Customer(101), new Customer(115), new Customer(209)
        );
        Assertions.assertIterableEquals(expectedCustomers, allCustomers);
    }
}