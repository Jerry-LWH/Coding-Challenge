package groovy

import com.example.bestmatchedrestarant.controller.RestaurantController
import com.example.bestmatchedrestarant.entity.Restaurant
import com.example.bestmatchedrestarant.enums.Cuisine
import com.example.bestmatchedrestarant.service.RestaurantService
import spock.lang.Specification
import spock.lang.Subject

class RestaurantControllerSpecification extends Specification{
    @Subject RestaurantController restaurantController

    RestaurantService restaurantService

    def setup() {
        restaurantService = Mock()
    }

    def "calling searchRestaurant should invoke service call to search" () {
        given:
            def expectedResult = Restaurant.builder()
                    .name("Deli")
                    .customer_rating(3)
                    .distance(3)
                    .price(15)
                    .cuisine(Cuisine.RUSSIAN)
                    .build()
        when:
            def result = restaurantService.search("Deli",3,3,15,"Russian")

        then:
            1 * restaurantService.search(_,_,_,_,_) >> List.of(expectedResult)
            result.get(0).equals(expectedResult)
    }


}
