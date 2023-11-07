package com.example.api.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Repository
class CustomerRepository implements CustomersApi {

    private final String jsonPath;
    private final ObjectMapper objectMapper;

    CustomerRepository(@Value("${hotel.customer.jsonPath}") String jsonPath, ObjectMapper objectMapper) {
        this.jsonPath = jsonPath;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            URL url = URI.create(jsonPath).toURL();
            int[] customerPrizes = objectMapper.readValue(url, int[].class);
            return Arrays.stream(customerPrizes).mapToObj(Customer::new).toList();
        } catch (IOException e) {
            throw new CustomerAccessException(e);
        }
    }
}
