package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {

    String ctURL = "https://api.training.cydeo.com";

    @Test
    public void test1(){
        Response response = RestAssured.get(ctURL+"/student/all");
        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print json body
        System.out.println(response.prettyPrint());

    }

    /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        And body is json format

     */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(ctURL+"/student/all");
        //verify status code is 200
        Assert.assertEquals(response.statusCode(), 200,"Verify status code is 200");

        //verify content type
        String expectedContentType = "application/json;charset=UTF-8";
        Assert.assertEquals(response.contentType(), expectedContentType, "Verify content type is as expected");
    }

    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(ctURL+"/student/all").then()
                .assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8");
    }

    @Test
    public void test4(){
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .when().get(ctURL + "/student/15");
        //verify status code
        Assert.assertEquals(response.statusCode(), 200);
        //verify content type
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        // verify body contains Leopold
        Assert.assertTrue(response.body().asString().contains("Leopold"));

    }
}
