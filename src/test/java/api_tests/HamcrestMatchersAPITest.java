package api_tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersAPITest {

    /*
      given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
          "id": 15,
          "name": "Meta",
          "gender": "Female",
          "phone": 1938695106
       */

    @Test
    public void OneSpartanWithHamcrest(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://3.85.214.90:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id",equalTo(15),
                        "name",equalTo("Meta"),
                        "gender",equalTo("Female"),
                        "phone",equalTo(1938695106));


    }

    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id","6766")
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Content-Length",equalTo("242"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("ZX"),
                        "teachers.lastName[0]",equalTo("AS"),
                        "teachers.gender[0]",equalTo("Male"));
        // .log().all();
    }

    @Test
    public void teachersWithDepartments(){

        given().accept(ContentType.JSON)
                .and().pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName",hasItems("Alexander","Marteen"));

    }


}
