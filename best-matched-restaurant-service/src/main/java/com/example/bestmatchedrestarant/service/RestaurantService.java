package com.example.bestmatchedrestarant.service;

import com.example.bestmatchedrestarant.entity.Restaurant;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

public interface RestaurantService {
    List<Restaurant> search(String name, Integer rating, Integer distance,Integer price, String cuisine) throws IOException, CsvException;
}
