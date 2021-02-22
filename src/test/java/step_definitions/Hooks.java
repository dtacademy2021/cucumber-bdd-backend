package step_definitions;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class Hooks {

/**
  This test demonstrates the difference between implicit wait and explicit wait, and what happens when you mix them both in one script
 */
@Test
	public void test() {
//	Driver.getDriver().manage().timeouts().
//			implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitTimeout")), TimeUnit.SECONDS);


	Driver.getDriver().get("https://www.google.com/");

	long start = System.currentTimeMillis();

	try{

//		By locationMachanism = By.name("r");
//
////		BrowserUtils.fluentWait(locationMachanism, 10, 1 );  // Fluent wait test

		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("r")));         //Explicit wait test

		Driver.getDriver().findElement(By.name("r")).sendKeys("Hello" + Keys.ENTER);  // Implicit wait test
	}
	finally {
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);  // To count the seconds for waits
	}







}
	
	@Before ()
	public void setupScenario() {
		
		Driver.getDriver().manage().timeouts().
		implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitTimeout")), TimeUnit.SECONDS);
		Driver.getDriver().manage().window().maximize();

		
		
	}
	

	
	
	
	
	@After ()
	public void tearDownScenario(Scenario scenario) {
		
		if(scenario.isFailed()) {
			byte [] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			
			String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			scenario.attach(screenshot, "image/png", "Screenshot"+date);
		}
		
		
		
		Driver.quit();
	}
	
	

	

}
