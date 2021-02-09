package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.DBUtils;
import utilities.Driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hooks2 {
	
	
	
	@Before ("@duotify")
	public void setupScenario() {


		Driver.getDriver().manage().timeouts().
		implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitTimeout")), TimeUnit.SECONDS);
		Driver.getDriver().manage().window().maximize();
		Driver.getDriver().get(ConfigReader.getProperty("url_test"));
		DBUtils.createConnection();
		
	}

	@Before ("@duotify_db_only")
	public void setupScenarioDB() {

		DBUtils.createConnection();

	}







	@After ("@duotify")
	public void tearDownScenario(Scenario scenario) {
		
		if(scenario.isFailed()) {
			byte [] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			
			String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			scenario.attach(screenshot, "image/png", "Screenshot"+date);
		}
		
		
		
		Driver.quit();

		DBUtils.close();
	}


	@After ("@duotify_db_only")
	public void tearDownScenarioDB(Scenario scenario) {

		DBUtils.close();
	}
	

}
