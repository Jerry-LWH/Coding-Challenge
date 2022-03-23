package com.example.bestmatchedrestarant.service.serviceImpl;

import com.example.bestmatchedrestarant.entity.Restaurant;
import com.example.bestmatchedrestarant.enums.Cuisine;
import com.example.bestmatchedrestarant.helper.FileParser;
import com.example.bestmatchedrestarant.service.RestaurantService;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public List<Restaurant> search(String name, Integer rating, Integer distance, Integer price, String cuisine) throws IOException, CsvException {
        List<Restaurant> restaurants = FileParser.parseCSV("data/restaurants.csv");

        Predicate<Restaurant> predicate = buildFilterPredicate(name, rating, distance, price, cuisine);

        return restaurants.stream().filter(predicate).sorted(Restaurant::compareTo).limit(5).collect(Collectors.toList());
    }

    Predicate<Restaurant> buildFilterPredicate(String name, Integer rating, Integer distance, Integer price, String cuisine) {
        Predicate<Restaurant> p = Objects::nonNull;

        if (Objects.nonNull(name)) {
            p = p.and(restaurant -> restaurant.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
        }

        if (Objects.nonNull(rating)) {
            p = p.and(restaurant -> restaurant.getCustomer_rating() >= rating);
        }

        if (Objects.nonNull(distance)) {
            p = p.and(restaurant -> restaurant.getDistance() <= distance);
        }

        if (Objects.nonNull(price)) {
            p = p.and(restaurant -> restaurant.getPrice() <= price);
        }

        if(Objects.nonNull(cuisine)) {
            boolean hasSet = false;
            //** Assuming unmatched cuisine parameter defaults to Cuisine.Other **//
            for (Cuisine c :Cuisine.values()) {
                if (c.name().contains(cuisine.toUpperCase(Locale.ROOT))) {
                    p = p.and(restaurant -> restaurant.getCuisine().name().contains(cuisine.toUpperCase(Locale.ROOT)));
                    hasSet = true;
                    break;
                }
            }

            //** if cuisine predicate is not set (due to non-match), then set predicate to equals to Cuisine.OTHER **//
            if (!hasSet) {
                p = p.and(restaurant -> restaurant.getCuisine().name().equals(Cuisine.OTHER.name()));
            }
        }
        return p;
    }
}
