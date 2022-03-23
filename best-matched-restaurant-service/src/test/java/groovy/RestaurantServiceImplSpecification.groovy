package groovy

import com.example.bestmatchedrestarant.entity.Restaurant
import com.example.bestmatchedrestarant.enums.Cuisine
import com.example.bestmatchedrestarant.service.serviceImpl.RestaurantServiceImpl
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class RestaurantServiceImplSpecification extends Specification{
    @Subject RestaurantServiceImpl restaurantService

    def setup() {
        restaurantService = new RestaurantServiceImpl()
    }

    def "calling search should return matched restaurants in sorted order" () {
        given:
            def restaurant1 = new Restaurant("Deliciouszilla",4,1,15,Cuisine.CHINESE)
            def restaurant2 = new Restaurant("Deliciouszilla",5,3,50,Cuisine.CHINESE)
            def restaurant3 = new Restaurant("Deliciousoryx",1,5,25,Cuisine.CHINESE)
            def expectedResult = List.of(restaurant1, restaurant2, restaurant3)

        when:
           def result =  restaurantService.search("deli", 1, 10, 50, "Chinese")
        then:
            assert result.size() == 3
            assert result == expectedResult
    }
    def "calling buildFilterPredicate should build predicate" () {
        given:
            def name = "Deli"
            def rating = 5
            def distance = 3
            def price = 15
            def cuisine = "chinese"
            Predicate<Restaurant> expectedPredicate = Objects::nonNull;
            expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)))
            expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getCustomer_rating() >= rating)
            expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getDistance() <= distance)
            expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getPrice() <= price)
            expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getCuisine().name().contains(cuisine.toUpperCase(Locale.ROOT)));

            def restaurant = new Restaurant(name,rating,distance, price, Cuisine.CHINESE)

        when:
            def resultPredicate = restaurantService.buildFilterPredicate(name, rating, distance, price, cuisine)

        then:
            assert resultPredicate.test(restaurant)
            assert expectedPredicate.test(restaurant)
    }

    def "calling buildFilterPredicate with un-matched Cuisine should build predicate with Cuisine.OTHER" () {
        given:
        def name = "Deli"
        def rating = 5
        def distance = 3
        def price = 15
        def cuisine = "abc"
        Predicate<Restaurant> expectedPredicate = Objects::nonNull;
        expectedPredicate = expectedPredicate.and(restaurant -> restaurant.getCuisine().name().equals(Cuisine.OTHER.name()));

        def restaurant = new Restaurant(name,rating,distance, price, Cuisine.OTHER)

        when:
        def resultPredicate = restaurantService.buildFilterPredicate(null, null, null, null, cuisine)

        then:
        assert resultPredicate.test(restaurant)
        assert expectedPredicate.test(restaurant)
    }
}
