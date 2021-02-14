package apiTests;

import apiTests.pojos.videogame.VG;
import apiTests.pojos.videogame.VideoGame;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestVideoGameApi {


    @Test
    public void getVideoGames(){

        RestAssured.baseURI = "http://localhost:8080/app";
        given().
                header("Accept", "application/json").

                when().log().all().
                   get("/videogames").
                then().log().all().
                    assertThat().

                    statusCode(equalTo(200)).
                    header("content-length" , "1183").
                    header("content-type" , "application/json");





    }


    @Test
    public void verifyVideoGameCreation() {

        // Create  a video game in the database by using post request
        RestAssured.baseURI = "http://localhost:8080/app";
        int id  = 10 + new Random().nextInt(1000);
        VG requestGameObject = new VG( id,
                                        "Valhalla",
                                        "2021-02-13",
                                        9.0,
                                        "Adventure",
                                        "5.0");




        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").


                body(requestGameObject, ObjectMapperType.JACKSON_2).   // sending the payload as POJO -> serialization


                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().

                statusCode(equalTo(200)).
                body("status", equalTo("Record Added Successfully")).
                header("content-type" , "application/xml");









        //Verify the video game creation by getting the video game
        // and verifying that the information that I sent initially is correct
        VideoGame responseGameObject = given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                // required for path parameters
                        when().log().all().
                        get("/videogames/{videoGameId}").   // path parameter {videoGameId} value will come from the header
                then().log().all().
                        assertThat().
                        statusCode(equalTo(200)).
                        header("content-type", "application/json").extract().as(VideoGame.class);
                        // deserialize the JSON into VideoGame object

        Assert.assertEquals((Object)requestGameObject.getId(), (Object)responseGameObject.getId());
        Assert.assertEquals(requestGameObject.getCategory(), responseGameObject.getCategory());
        Assert.assertEquals(requestGameObject.getName(), responseGameObject.getName());
        Assert.assertEquals(requestGameObject.getRating(), responseGameObject.getRating());
//        Assert.assertEquals(requestGameObject.getReleaseDate(), responseGameObject.getReleaseDate());
        Assert.assertEquals(requestGameObject.getReviewScore(), responseGameObject.getReviewScore());




    }

    }
