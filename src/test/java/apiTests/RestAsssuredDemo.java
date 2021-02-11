package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
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
        given().  log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
//                contentType(ContentType.JSON).
                header("Accept", "*/*").
                body("{\n" +
                        " \"location\": {\n" +
                        "   \"lat\": -77.2273128,\n" +
                        "   \"lng\": 38.9151944\n" +
                        " },\n" +
                        " \"accuracy\": 50,\n" +
                        " \"name\": \"Duotech Academy\",\n" +
                        " \"phone_number\": \"(571) 295-4555\",\n" +
                        " \"address\": \"8133 Leesburg Pike STE 300, Vienna, VA 22182\",\n" +
                        " \"types\": [\n" +
                        "   \"academy\",\n" +
                        "   \"bootcamp\"\n" +
                        " ],\n" +
                        " \"website\": \"duotech.io\",\n" +
                        " \"language\": \"English\"\n" +
                        "}\n").
                when(). log().all().
                post("/add/json").
        then().  log().all().
                assertThat().
                statusCode(equalTo(200)).
                body("status", equalTo("OK")).
                body("scope", equalTo("APP"));







        // Retrieve the place
        //Delete the place





    }



}
