package com.example.api;

import com.example.api.customers.Customer;

import java.util.List;

public final class TestUtils {
    public static List<Customer> defaultCustomerList() {
        return List.of(
                new Customer(23), new Customer(45), new Customer(155), new Customer(374), new Customer(22),
                new Customer(99), new Customer(100), new Customer(101), new Customer(115), new Customer(209)
        );
    }
}
