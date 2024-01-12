package api_tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HRTestWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1(){
        Response response = get("/countries");

        //assign to jsonpath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println(countryIDs.toString());

        //get all country names where their region id is equal to 2
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNamesWithRegionId2);
    }
}
