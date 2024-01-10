package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

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

    /*
    Given no headers provided
    When users send get request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body shoud be "Hello from Sparta"
     */

    @Test
    public void test3(){
        Response response = when().get(spartanURL + "/api/hello");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assert.assertEquals(response.header("Content-Length"),"17");

        //System.out.println(response.headers().toString());
        Assert.assertEquals(response.body().asString(), "Hello from Sparta");


    }
}
