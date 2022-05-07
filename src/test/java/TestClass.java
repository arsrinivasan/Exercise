import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import response.Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestClass {

    @Test
    public static void getPriceValue() {
        List<Result> result = sendRequestAndGetResponse();
        List<Double> getAllPrice = result.stream().map(Result::getPrice).filter(price -> price == 17.99).collect(Collectors.toList());
        System.out.println("No of items in the list with price 17.99 is: " + getAllPrice.size());
    }

    @Test
    public static void getPriceGreaterThan20(){
        List<Result> result = sendRequestAndGetResponse();
        List<Result> getFilteredResult = result.stream().filter(result1 -> result1.getPrice()> 20).collect(Collectors.toList());
        for(Result results: getFilteredResult){
            System.out.println("Name of the item: "+results.getName());
            System.out.println("Price of the item: "+results.getPrice()+"\n");
        }
        System.out.println("===============================================================");

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
}
