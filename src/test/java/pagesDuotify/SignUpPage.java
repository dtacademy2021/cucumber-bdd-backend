package pagesDuotify;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SignUpPage {


    public SignUpPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "hideLogin")
    public WebElement signUpLink;

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "firstName")
    public WebElement first;

    @FindBy(id = "lastName")
    public WebElement last;

    @FindBy(id = "email")
    public WebElement email;




    @FindBy(id = "email2")
    public WebElement confirmEmail;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "password2")
    public WebElement confirmPassword;


    @FindBy(name = "registerButton")
    public WebElement signUpButton;







}
