package groovy

import com.example.bestmatchedrestarant.entity.Restaurant
import com.example.bestmatchedrestarant.enums.Cuisine
import com.example.bestmatchedrestarant.helper.FileParser
import spock.lang.Specification

class FileParserSpecification extends Specification {

    def "when calling parseCSV should parse file into List<Restaurant>" (){
        given:
            def restaurant1 = new Restaurant("Deliciousgenix", 4, 1, 10, Cuisine.SPANISH)
            def restaurant2 = new Restaurant("Herbed Delicious", 4, 7, 20, Cuisine.VIETNAMESE)
            def restaurant3 = new Restaurant("Deliciousscape", 3, 7, 50, Cuisine.AMERICAN)
            def restaurant4 = new Restaurant("Hideaway Delicious", 2, 5, 40, Cuisine.GREEK)
            def restaurant5 = new Restaurant("Cuts Delicious", 3, 9, 25, Cuisine.KOREAN)
            def expectedResult = List.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5)
        when:
            def result = FileParser.parseCSV("data/testFile.csv")

        then:
            assert result!= null
            result.size() == 5
            result == expectedResult
    }
}
