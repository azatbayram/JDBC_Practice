package api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class simpleGetRequest {

    String ctURL = "https://api.training.cydeo.com/student/all";

    @Test
    public void test1(){
        Response response = RestAssured.get(ctURL);
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
}
