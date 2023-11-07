package com.example.api.accommodations;

import com.example.api.customers.Customer;
import com.example.api.customers.CustomersApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
class AccommodationService {

    private final CustomersApi customersApi;

    AccommodationService(CustomersApi customersApi) {
        this.customersApi = customersApi;
    }

    RoomUtilization placeCustomers(Accommodation accommodation) {
        List<Customer> allCustomers = new ArrayList<>(customersApi.getAllCustomers());
        allCustomers.sort(Comparator.comparingInt(Customer::prize).reversed());

        List<Customer> premium = premiumCustomers(allCustomers, accommodation.premium());
        allCustomers.removeIf(premium::contains);

        List<Customer> economy = economyCustomers(allCustomers, accommodation.economy());
        allCustomers.removeIf(economy::contains);

        while (isPlaceInPremium(premium, accommodation.premium()) && stillAvailableCustomers(allCustomers)) {
            premium.add(economy.removeFirst());
            economy.add(allCustomers.removeFirst());
        }

        return calculateUtilization(premium, economy);
    }

    private RoomUtilization calculateUtilization(List<Customer> premium, List<Customer> economy) {
        int premiumGain = premium.stream()
                .mapToInt(Customer::prize)
                .sum();
        int economyGain = economy.stream()
                .mapToInt((Customer::prize))
                .sum();
        return new RoomUtilization(premium.size(), economy.size(), premiumGain, economyGain);
    }

    private List<Customer> premiumCustomers(List<Customer> allCustomers, int maxPlaces) {
        return new LinkedList<>(allCustomers.stream()
                .filter(c -> c.prize() >= 100)
                .limit(maxPlaces).toList());
    }

    private List<Customer> economyCustomers(List<Customer> allCustomers, int maxPlaces) {
        return new LinkedList<>(allCustomers.stream()
                .filter(c -> c.prize() < 100)
                .limit(maxPlaces).toList());
    }

    private boolean stillAvailableCustomers(List<Customer> allCustomers) {
        return !allCustomers.isEmpty();
    }

    private boolean isPlaceInPremium(List<Customer> premium, int size) {
        return size - premium.size() > 0;
    }

}
