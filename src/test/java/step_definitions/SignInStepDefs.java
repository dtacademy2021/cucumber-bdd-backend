package step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SignInPage;
import utilities.ConfigReader;
import utilities.Driver;

public class SignInStepDefs {
	
	
	@Given("The User is on the homepage")
	public void the_User_is_on_the_homepage() {
	   
		Driver.getDriver().get(ConfigReader.getProperty("UAT_URL"));
			
	}

	@When("The user clicks on sign in button")
	public void the_user_clicks_on_sign_in_button() {

		  HomePage hp = new HomePage();
		  hp.signInLink.click();

		
	}

	@Then("The user should land on sign in page and the title should be Login - My Store")
	public void the_user_should_land_on_sign_in_page_and_the_title_should_be_Login_My_Store() {

		String title = Driver.getDriver().getTitle();
		
		assertEquals("Login - My Store", title);
		
		
	
		
	  
	}
	
	
	
	@When("The user enters incorrect credentials")
	public void the_user_enters_incorrect_credentials() {
		
		SignInPage sp = new SignInPage();
		
		sp.username.sendKeys("wrong@wrong.com");
		sp.password.sendKeys("wrongpass");
		sp.signInButton.click();
		
	  
	}

	@Then("The user should not login and the error message should appear with the text Invalid Email Address")
	public void the_user_should_not_login_and_the_error_message_should_appear() {
	  
		SignInPage sp = new SignInPage();
		
		assertTrue(sp.errorMessage.isDisplayed());
		assertEquals("Authentication failed.", sp.errorMessage.getText());
		Driver.quit();
	}
	
	
	@When("The user enters no credentials")
	public void the_user_enters_no_credentials() {
		SignInPage sp = new SignInPage();
		
		sp.username.sendKeys("");
		sp.password.sendKeys("");
		sp.signInButton.click();
	}

	@Then("The user should not login and the error message should appear with the text An email address required")
	public void the_user_should_not_login_and_the_error_message_should_appear_with_the_text_an_email_address_required() {
		SignInPage sp = new SignInPage();
		
		assertTrue(sp.errorMessage.isDisplayed());
		assertEquals("An email address needed.", sp.errorMessage.getText());
		
	}
	
	
	

}
