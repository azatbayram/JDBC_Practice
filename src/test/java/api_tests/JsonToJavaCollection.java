package api_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name, "Meta");

        BigDecimal phone=new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);

    }

    @Test
    public void allSpartansToListOfMap(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        //we need to de-serialize Json response to List of Maps
        List<Map<String,Object>> allSpartanList=response.body().as(List.class);
        System.out.println("allSpartanList = " + allSpartanList);

        //print second spartan first name
        System.out.println(allSpartanList.get(1).get("name"));
        //save spartan 3 in a map
        Map<String,Object> spartan3=allSpartanList.get(2);
        System.out.println(spartan3);

    }

}
