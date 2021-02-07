package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import pagesDuotify.HomePAge;
import pagesDuotify.LoginPage;
import pagesDuotify.SignUpPage;
import utilities.DBUtils;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class DBMappingStepDefs {

    String username;
    String firstName;
    String lastName;
    String email;

    @Given("I am in Sign Up page")
    public void iAmInSignUpPage() {
        new SignUpPage().signUpLink.click();

    }

    @When("I enter the following User details and sign up")
    public void iEnterTheFollowingUserDetailsAndSignUp(List<Map<String,String>> dataTable) {

        SignUpPage sp = new SignUpPage();

        Map<String, String> map = dataTable.get(0);
        username = map.get("username");
        firstName = map.get("first");
        lastName = map.get("last");
        email = map.get("email");

        sp.username.sendKeys(username);
        sp.first.sendKeys(firstName);
        sp.last.sendKeys(lastName);
        sp.email.sendKeys(email);
        sp.confirmEmail.sendKeys(map.get("email"));
        sp.password.sendKeys(map.get("password"));
        sp.confirmPassword.sendKeys(map.get("password"));

        sp.signUpButton.click();


    }

    @Then("I should land on the welcome page")
    public void iShouldLandOnTheWelcomePage() {
        String actual = new HomePAge().welcomePageName.getText();
        String expected = "Joe Biden";
        assertEquals(expected, actual);

    }

    @Then("The user details should be correctly mapped in the users table of database")
    public void theUserDetailsShouldBeCorrectlyMappedInTheUsersTableOfDatabase() {
        // Verify data mapping

        String request = "select username, firstName, lastName, email from users where username='"+username+"'";

        final List<Map<String, Object>> list = DBUtils.getQueryResultMap(request);

        Map<String, Object> map = list.get(0);

        String actualUserName = (String) map.get("username");
        String actualfirstName = (String) map.get("firstName");
        String actuallastName = (String) map.get("lastName");
        String actualemail = (String) map.get("email");


        assertEquals(username, actualUserName);
        assertEquals(firstName, actualfirstName);
        assertEquals(lastName, actuallastName);
        assertEquals(email, actualemail);




        //delete the entry to be able to repeat the test again

        String query = "delete from users where username='"+username+"'";
        DBUtils.updateQuery(query);

    }











}
