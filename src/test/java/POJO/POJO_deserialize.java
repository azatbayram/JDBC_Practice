package POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

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

    @Test
    public void regionToPojo(){

        Response response = when().get("http://3.85.214.90:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

        //JSON to POJO
        Regions regions=response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionName());

    }

    @Test
    public void gson_example(){

        Gson gson=new Gson();

        //JSON to Java collections ot POJO -->De-serialization

        String myJsonData="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15=gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);

        //Java Collection or POJO to JSON Serialization

        Spartan spartanEU=new Spartan(200,"Mike","Male",123123123);
        String jsonSpartanEU=gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);

    }

}
