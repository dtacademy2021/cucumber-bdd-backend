package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pagesDuotify.HomePAge;
import pagesDuotify.LoginPage;
import utilities.DBUtils;

public class DBUpdateStepDefs {

    String first;
    String last;
    String email;
    String username;

    @When("I update firstName with {string}, lastName with {string} and email with {string} for a user with username {string}")
    public void iUpdateFirstNameWithLastNameWithAndEmailWithForAUserWithUsername(String first, String last, String email, String username) {
        String query =  "update users set firstName='"+first+"', lastName='"+last+"', email='"+email+"' where username='"+username+"'";

        this.first = first;
        this.last  =last;
        this.email =email;
        this.username = username;


        DBUtils.updateQuery(query);
    }




    @Then("The same info on the UI should be correct")
    public void theSameInfoOnTheUIShouldBeCorrect() {
        new LoginPage().username.sendKeys(this.username);
        new LoginPage().password.sendKeys("duotech2020" + Keys.ENTER);

        String[] s = new HomePAge().welcomePageName.getText().split(" ");

        Assert.assertEquals(this.first,  s[0]);
        Assert.assertEquals(this.last,  s[1]);
       HomePAge hp =  new HomePAge();
       hp.welcomePageName.click();
       hp.userDetailsButton.click();
        String actualEmail = hp.emailInputbox.getAttribute("value");
        Assert.assertEquals(this.email,  actualEmail);

    }
}
