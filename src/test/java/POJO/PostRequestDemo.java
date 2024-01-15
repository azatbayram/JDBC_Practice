package POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"JohnTKM",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void postNewSpartan(){

        String jsonBody="{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"JohnTKM\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        //verify status code 201 for post
        assertEquals(postResponse.statusCode(),201);
        //verify Content-Type is application/json
        assertEquals(postResponse.contentType(),"application/json");

        //verify successful message
        String actualMessage=postResponse.path("success");
        String expectedMessage="A Spartan is Born!";

        assertEquals(actualMessage,expectedMessage);

        //assertion for spartan data
        String name=postResponse.path("data.name");
        String gender=postResponse.path("data.gender");
        long phone=postResponse.path("data.phone");

        assertEquals(name,"JohnTKM");
        assertEquals(gender,"Male");
        assertEquals(phone,8877445596l);

    }

    @Test
    public void postNewSpartan2(){

        //create a map to keep request json body information
        Map<String, Object> requestMap=new HashMap<>();
        //adding value that we want to post
        requestMap.put("name","Steven");
        requestMap.put("gender","Male");
        requestMap.put("phone",8877445596l);

        given().log().all().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans")
                .then().log().all()
                .statusCode(201).and()
                .contentType("application/json").and()
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name",equalTo("Steven"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(8877445596l));

    }

    @Test
    public void postNewSpartan3(){

        Spartan spartanEU=new Spartan();
        spartanEU.setName("Michael");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartanEU)
                .when().post("/api/spartans")
                .then().log().all()
                .statusCode(201).and()
                .contentType("application/json").and()
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name",equalTo("Michael"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(8877445596l));

    }



}
