package com.example.bestmatchedrestarant.entity;

import com.example.bestmatchedrestarant.enums.Cuisine;
import java.util.Comparator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurant implements Comparable<Restaurant> {
    private String name;
    private int customer_rating;
    private int distance;
    private int price;
    private Cuisine cuisine;

    @Override
    public int compareTo(Restaurant otherRestaurant) {
        return Comparator.comparing(Restaurant::getDistance)
                .thenComparing(Restaurant::getCustomer_rating, Comparator.reverseOrder())
                .thenComparing(Restaurant::getPrice)
                .compare(this, otherRestaurant);
    }
}
