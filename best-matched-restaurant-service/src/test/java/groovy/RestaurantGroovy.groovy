package groovy

import com.example.bestmatchedrestarant.entity.Restaurant
import com.example.bestmatchedrestarant.enums.Cuisine
import spock.lang.Specification
import spock.lang.Subject

class RestaurantGroovy extends Specification{
    @Subject Restaurant restaurant

    def setup() {
        restaurant = new Restaurant("Deli", 4, 3, 20, Cuisine.RUSSIAN)
    }

    def "calling compareTo should return correct sort number" () {
        when:
            def sortNum = restaurant.compareTo(comparingRestaurant)
        then:
            sortNum == expectedNum

        where:
            comparingRestaurant                                 |  expectedNum
            //distance
            new Restaurant("Deli", 4, 5, 20, Cuisine.RUSSIAN)   |   -1
            new Restaurant("Deli", 4, 3, 20, Cuisine.RUSSIAN)   |    0
            new Restaurant("Deli", 4, 1, 20, Cuisine.RUSSIAN)   |    1
            //rating
            new Restaurant("Deli", 3, 3, 20, Cuisine.RUSSIAN)   |   -1
            new Restaurant("Deli", 4, 3, 20, Cuisine.RUSSIAN)   |    0
            new Restaurant("Deli", 5, 3, 20, Cuisine.RUSSIAN)   |    1
            //price
            new Restaurant("Deli", 4, 3, 30, Cuisine.RUSSIAN)   |   -1
            new Restaurant("Deli", 4, 3, 20, Cuisine.RUSSIAN)   |    0
            new Restaurant("Deli", 4, 3, 10, Cuisine.RUSSIAN)   |    1
    }



}
