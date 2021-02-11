package apiTests;

import apiTests.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SendPayloadAsMap {










    @Test
    public void sendPayloadAsMap(){
          RestAssured.baseURI = "http://3.6.24.244/maps/api/place";

          Map<String, Object> placePayload = new LinkedHashMap<>();

                Map<String, Object> location =  new HashMap<String,Object>();
                location.put("lat",  -77.2273128);
                location.put("lng",  -38.9151944);


        placePayload.put("location", location );
        placePayload.put("accuracy", 90 );
        placePayload.put("name", "Duotech Academy" );
        placePayload.put("phone_number", "(571) 295-4555" );
        placePayload.put("address", "8133 Leesburg Pike STE 300, Vienna, VA 22182" );
                 List<String> list = new ArrayList<>();
                 list.add("academy");
                list.add("bootcamp");
        placePayload.put("types", list);
        placePayload.put("website", "duotech.io" );
        placePayload.put("language", "English" );






        //Create a place

        Map map = given().log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
//                contentType(ContentType.JSON).
        header("Accept", "*/*").
                        body(placePayload).
                        when().log().all().
                        post("/add/json").
                        then().log().all().
                        assertThat().
                        statusCode(equalTo(200)).
                        body("status", equalTo("OK")).
                        body("scope", equalTo("APP")).extract().as(Map.class);

        System.out.println(map);

//

    }



}
