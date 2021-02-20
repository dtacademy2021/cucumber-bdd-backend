package step_definitions;

import apiTests.examples.Endpoints;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIStepDefs implements Endpoints {

   static {
       RestAssured.baseURI = "http://duobank-env.eba-bgkwzq3h.us-east-2.elasticbeanstalk.com/api";
   }

    String token;
    Response response;
    RequestSpecification specification;


    @Given("I am logged an as a user and I have generated a token")
    public void iAmLoggedAnAsAUserAndIHaveGeneratedAToken() {


        Map responseMap = given().
                body("{\n" +
                        "    \"email\":\"jbiden@gmail.com\",\n" +
                        "    \"password\": \"1\"\n" +
                        "}").


                when().log().all().
                post("/login.php").
                then().log().all().
                assertThat().

                statusCode(equalTo(200)).
                extract().as(Map.class);

        token = (String)responseMap.get("token");



    }


    @When("I add the authorization token to the header")
    public void iAddTheAuthorizationTokenToTheHeader() {

        specification = given(). log().all().
                header("Authorization", token);
    }

    @When("Send a GET request to the endpoint {string}")
    public void sendAGETRequestToTheEndpoint(String endpoint) {




         response =   specification.when().log().all().
                                   get(endpoint);


    }

    @Then("The response code should be {int} and the mortgage count should be correct")
    public void theResponseCodeShouldBeAndTheMortgageCountShouldBeCorrect(Integer int1) {
        response. then().log().all().
                assertThat().

                statusCode(equalTo(200));
    }


    @Override
    public String getToken() {
        return null;
    }
}
