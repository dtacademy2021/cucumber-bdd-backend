package pagesDuotify;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePAge {

    public HomePAge() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[@onclick=\"openPage('settings.php')\"]")
    public WebElement welcomePageName;

    @FindBy(xpath = "//button[@onclick=\"openPage('updateDetails.php')\"]")
    public WebElement userDetailsButton;

    @FindBy(name = "email")
    public WebElement emailInputbox;






}


