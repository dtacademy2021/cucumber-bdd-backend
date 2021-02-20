package apiTests;

import apiTests.payloads.Payloads;
import apiTests.examples.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Driver;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

public class RestAsssuredDemo {


    @Before
    public void setUpBaseURI(){
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api/place";
    }




    @Test
    public void testRA(){
       // Send a request to findPlace resource to serch for Georgia Aquarium
        // Verify the response code is 200
        // Verify that the response contains the search term


        // 1. Set the base URI;


//        RestAssured.basePath = ""  // base bath is usually varies from test to test or even within the test so it is
        // better to put it into the code;

        RequestSpecification requestSpecification = RestAssured.given().
                queryParam("input", "Georgia Aquarium").
                queryParam("inputtype", "textquery").
                queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry").
                queryParam("key", "AIzaSyCW1pMbqWsBrmZaZAgUQPwPuv7EOBcQ-Qc");

        Response response = requestSpecification.get("/findplacefromtext/json");

        String s = response.asString();

        System.out.println(s);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(200, statusCode);

        Assert.assertTrue(s.contains("Georgia Aquarium"));




    }


    @Test
    public void testRAEasySyntax(){


        //Given I have the query params
        //When I send a get request to this endppoint
        //Then the status code should be 200
        //And the body should contain search term


        //Given I have the query params
        RequestSpecification requestSpecification = given().

                queryParam("input", "Georgia Aquarium").
                queryParam("inputtype", "textquery").
                queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry").
                queryParam("key", "AIzaSyCW1pMbqWsBrmZaZAgUQPwPuv7EOBcQ-Qc");

        //When I send a get request to this endppoint
        Response response = requestSpecification.when().log().all().
                get("/findplacefromtext/json");

        //Then the status code should be 200
        //And the body should contain search term

       response. then(). log().all().
                statusCode(equalTo(200)).
                body(containsString("Georgia Aquarium"));





    }


    @Test
    public void end2endTestPlacesAPI(){
          baseURI = "http://3.6.24.244/maps/api/place";
         //Create a place
        String placeName = "Duotech";
        String phoneNo = "571-222-3456";
       JsonPath jsonPath= given().log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
        header("Accept", "*/*").
                        body(Payloads.addPlaceRequestBody(placeName, phoneNo )).
                        when().log().all().
                        post(Endpoints.POST_PLACE).
                        then().log().all().
                        assertThat().
                        statusCode(equalTo(200)).
                        body("status", equalTo("OK")).
                        body("scope", equalTo("APP")).extract().jsonPath();

//        System.out.println(map);

        Driver.getDriver().findElement(By.xpath("sbhab")).sendKeys(Keys.END);
//
//        String place_id = map.get("place_id");
//
//        System.out.println((String)place_id);


        String place_id = jsonPath.getString("place_id");





        // We can extract the response body as:
        // 1. String -> extract().asString()
        // 2. JsonPath object -> extract().jsonPath();
        // 3. Map object -> extract().as(Map.class);


//        System.out.println(responseBody);
        // Retrieve the place

        given().
                queryParam("key", "qaclick123").
                queryParam("place_id", place_id).
                header("Accept", "*/*").
        when().
                get("/get/json").
        then().
                assertThat().
                statusCode(is(200)).
                body("name", equalTo(placeName)).
                body("phone_number", equalTo(phoneNo));







        //Delete the place

        given().
                queryParam("key", "qaclick123").
                contentType(ContentType.JSON).
                header("Accept", "*/*").
                body("{\n" +
                        "    \"place_id\": \""+place_id+"\"\n" +
                        "}\n").


                when().

                delete("/delete/json").
                then().

                assertThat().
                statusCode(is(200)).
                body("status", equalTo("OK"));



          // Retrieve the plave with GET and verify that it returns error status code and error message

        given().
                queryParam("key", "qaclick123").
                queryParam("place_id", place_id).
                header("Accept", "*/*").
                when().
                get("/get/json").
                then(). log().all().
                assertThat().
                statusCode(is(404)).
                body("msg", containsString("doesn't exist"));





    }



}
