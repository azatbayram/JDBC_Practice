package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanGetRequest {

    String spartanURL = "http://3.85.214.90:8000";

    @Test
    public void test1(){
        Response response = RestAssured.get(spartanURL + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println(response.prettyPrint());
    }

    /*
    Task
    When users sends a get request to /api/spartans/3
    Then status code should be 200
    And content should be application/json; charset=UFT-8
    And json body should contain Fidole
     */
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/3");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/json; charset=UFT-8");

        Assert.assertTrue(response.body().asString().contains("Fidole"));
    }
}
