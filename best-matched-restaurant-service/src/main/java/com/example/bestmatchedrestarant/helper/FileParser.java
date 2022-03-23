package com.example.bestmatchedrestarant.helper;

import com.example.bestmatchedrestarant.entity.Restaurant;
import com.example.bestmatchedrestarant.enums.Cuisine;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileParser {
   public static List<Restaurant> parseCSV(String filePath) throws IOException, CsvException {
        //read csv file
        InputStream inputStream = FileParser.class.getClassLoader().getResourceAsStream(filePath);
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> dataRow = csvReader.readAll();

        List<Restaurant> retaurants = new ArrayList<>();

        //parse data rows
        for(int i = 1; i< dataRow.size();i++){
            Restaurant restaurant = Restaurant.builder()
                    .name(dataRow.get(i)[0])
                    .customer_rating(Integer.parseInt(dataRow.get(i)[1]))
                    .distance(Integer.parseInt(dataRow.get(i)[2]))
                    .price(Integer.parseInt(dataRow.get(i)[3]))
                    .cuisine(Cuisine.values()[Integer.parseInt(dataRow.get(i)[4])-1])
                    .build();
            retaurants.add(restaurant);
        }
        return retaurants;
    }
}
