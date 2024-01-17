package Put_Patch_Delete;

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

public class PutRequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){

        Map<String, Object> putRequestBody = new HashMap<>();
        putRequestBody.put("name", "Azat");
        putRequestBody.put("gender", "Male");
        putRequestBody.put("phone", 123456789l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                .body(putRequestBody)
                .when()
                .put("/api/spartan/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

        System.out.println("========================================================");

        //send get request to verify body
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",90)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void patchTest(){

        Map<String, Object> patchRequestBody = new HashMap<>();
        patchRequestBody.put("name", "AB");

        given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                .body(patchRequestBody)
                .when()
                .patch("/api/spartan/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",90)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

    }

}
