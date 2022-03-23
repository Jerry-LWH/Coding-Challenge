package com.example.bestmatchedrestarant.controller;

import com.example.bestmatchedrestarant.entity.Restaurant;
import com.example.bestmatchedrestarant.service.RestaurantService;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class RestaurantController {
    final RestaurantService restaurantService;

    @GetMapping("/best-matched-restaurants")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam(value = "name", required = false)  String name,
                                                             @RequestParam(value = "rating", required = false) @Min(1) @Max(5) Integer rating,
                                                             @RequestParam(value = "distance", required = false) @Min(1) @Max(10) Integer distance,
                                                             @RequestParam(value = "price", required = false) @Min(10) @Max(50) Integer price,
                                                             @RequestParam(value = "cuisine", required = false)  String cuisine) throws IOException, CsvException {

        List<Restaurant> restaurants = restaurantService.search(name, rating, distance, price, cuisine);

        return ResponseEntity.ok(restaurants);
    }

}
