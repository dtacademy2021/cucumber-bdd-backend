package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class RestAsssuredDemo {

    static{
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api/place";
    }


    @Test
    public void testRA(){
       // Send a request to findPlace resource to serch for Georgia Aquarium
        // Verify the response code is 200
        // Verify that the response contains the search term


        // 1. Set the base URI;
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api/place";

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

        given().
                queryParam("input", "Georgia Aquarium").
                queryParam("inputtype", "textquery").
                queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry").
                queryParam("key", "AIzaSyCW1pMbqWsBrmZaZAgUQPwPuv7EOBcQ-Qc").
        when(). log().all().
                get("/findplacefromtext/json").
        then(). log().all().
                statusCode(equalTo(200)).
                body(containsString("Georgia Aquarium"));





    }



}
