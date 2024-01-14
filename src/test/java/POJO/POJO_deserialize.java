package POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class POJO_deserialize {

    @Test
    public void oneSpartanPOJO(){
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get(ConfigurationReader.get("spartan_api_url") + "/api/spartan/{id}");

        assertEquals(response.statusCode(), 200);

        //JSON to POJO de-serialize to java custom class
        //JSON to our Spartan class object
        Spartan spartan15 = response.body().as(Spartan.class);
        System.out.println("spartan15 = " + spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getId());
        System.out.println(spartan15.getGender());
        System.out.println(spartan15.getPhone());

        assertEquals(spartan15.getId(), 15);
        assertEquals(spartan15.getName(), "Meta");

    }
}
