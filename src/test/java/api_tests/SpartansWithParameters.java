package api_tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartansWithParameters {

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://3.85.214.90:8000";
    }

    @Test
    public void getSpartanId_Positive_PathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json; charset=UFT-8");

        assertTrue(response.body().asString().contains("Blythe"));

    }

    @Test
    public void getSpartanId_Negative_PathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 404);

        assertEquals(response.contentType(), "application/json; charset=UFT-8");

        assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

    @Test
    public void positiveTestWithQueryParam(){

        Response response = given().contentType(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Female"));

        assertTrue(response.body().asString().contains("Janette"));

    }
}
