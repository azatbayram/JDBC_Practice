package api_tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("ct_api_url");
    }

    @Test
    public void test1(){
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 14)
                .when().get("/student/{id}");

        //verify status code
        assertEquals(response.statusCode(), 200);

        //assign gto JsonPath
        JsonPath jsonPath = response.jsonPath();

        //get value from jsonpath
        String firstname = jsonPath.getString("students.firstName[0]");
        System.out.println("firstname = " + firstname);

        String lastname = jsonPath.getString("students.lastName[0]");
        System.out.println("lastname = " + lastname);

        String phone = jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        int zipCode = jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);

        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);

    }
}
