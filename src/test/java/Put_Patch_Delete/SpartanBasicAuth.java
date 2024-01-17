package Put_Patch_Delete;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanBasicAuth {

    @Test
    public void test1(){
         given()
                 .accept(ContentType.JSON)
                 .and()
                 .auth().basic("admin", "admin")
                 .when()
                 .get(ConfigurationReader.get("spartan_api_url")+"/api/spartans")
                 .then().log().all()
                 .statusCode(200);
    }
}
