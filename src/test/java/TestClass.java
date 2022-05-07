import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import response.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class TestClass {

    @Test
    public static void verifyNoOfResultsForAPrice() {
        int actualSize = getPriceValueEquals(17.99);
        assertThat(actualSize).as("Expected result size is not matching with actual size").isEqualTo(146);
    }

    @Test
    public static void verifyPriceGreaterThan20() {

        assertThat(getNameAndPriceGreaterThan(20.0).values())
                .as("Expected to return all the item price greater than \"%s\"", 20).allMatch(price -> price > 20);

    }

    //Negative cases
    //To test returned price list size is
    @Test
    public static void verifyNoOfResultsForAPriceNotMatching() {
        int actualSize = getPriceValueEquals(17.99);
        assertThat(actualSize == 100)
                .as("Expected result size is matching with unexpected result size").isFalse();
    }

    // To test results with price value list with less than 20 is empty
    @Test
    public static void verifyPriceGreaterThan20NegativeCase() {

        assertThat(getNameAndPriceGreaterThan(20.0).values().stream().filter(price -> price < 20))
                .as("Expected not to return any item price less than \"%s\"", 20).isEmpty();

    }

    // To test results with price value list with less than 20 is empty
    @Test
    public static void verifyPriceGreaterThan20NegativeCase2() {

        assertThat(getNameAndPriceGreaterThan(20.0).values().stream().filter(price -> price < 20).count() > 1)
                .as("Expected not to return any item price less than \"%s\"", 20).isFalse();

    }

    public static List<Result> sendRequestAndGetResponse() {

        RestAssured.baseURI = "https://raw.githubusercontent.com/stockholmux/ecommerce-sample-set/master";
        RequestSpecification request = RestAssured.given().filter(new OverrideContentTypeFilter());
        Response response = request.get("/items.json");
        //The easy and short way to get list of price is by creating JsonPath and get
        //the price list from response as below. But this is not recommendable in framework
        //level. Deserialization using POJO is more preferable.
        /*JsonPath jsonPath= response.jsonPath();
        jsonPath.get("price");*/
        Result[] root = response.getBody().as(Result[].class);
        return Arrays.asList(root);

    }

    public static int getPriceValueEquals(Double priceValue) {
        List<Result> result = sendRequestAndGetResponse();
        List<Double> getAllPrice = result.stream().map(Result::getPrice).filter(price -> price.equals(priceValue)).collect(Collectors.toList());
        return getAllPrice.size();
    }

    public static Map<String, Double> getNameAndPriceGreaterThan(Double price) {
        List<Result> result = sendRequestAndGetResponse();
        Map<String, Double> nameAndPrice = new HashMap<>();
        List<Result> getFilteredResult = result.stream().filter(result1 -> result1.getPrice() > price).collect(Collectors.toList());
        for (Result results : getFilteredResult) {
            nameAndPrice.put(results.getName(), results.getPrice());
        }
        return nameAndPrice;

    }
}
