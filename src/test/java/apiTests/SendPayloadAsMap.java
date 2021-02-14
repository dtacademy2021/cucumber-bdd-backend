package apiTests;

import apiTests.pojos.placePojos.Location;
import apiTests.pojos.placePojos.Place;
import io.restassured.RestAssured;
import org.junit.Test;

import java.io.File;
import java.util.*;

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


    @Test
    public void sendPayloadAsJsonFile(){

        RestAssured.baseURI = "http://3.6.24.244/maps/api/place";

        File jsonFile = new File("src/test/java/apiTests/payloads/place.json");







        //Create a place

         given().log().all().
                queryParam("key", "qaclick123").
//                header("Content-Type", "application/json").
//                contentType(ContentType.JSON).
        header("Accept", "*/*").
                        body(jsonFile).
                        when().log().all().
                        post("/add/json").
                        then().log().all().
                        assertThat().
                        statusCode(equalTo(200)).
                        body("status", equalTo("OK")).
                        body("scope", equalTo("APP"));


    }


    @Test
    public void sendPayloadAsPojo(){

        RestAssured.baseURI = "http://3.6.24.244/maps/api/place";


        Place placePojo = new Place(
                new Location(67.89, 79.0),
                45,"Duotech", "1276572", "1321 hashjxc xshvah",
                Arrays.asList("academy", "bootcamp"), "duotech", "english"
                );







        //Create a place

        given().log().all().
                queryParam("key", "qaclick123").
//                header("Content-Type", "application/json").
//                contentType(ContentType.JSON).
        header("Accept", "*/*").
                body(placePojo).
                when().log().all().
                post("/add/json").
                then().log().all().
                assertThat().
                statusCode(equalTo(200)).
                body("status", equalTo("OK")).
                body("scope", equalTo("APP"));


    }



}
