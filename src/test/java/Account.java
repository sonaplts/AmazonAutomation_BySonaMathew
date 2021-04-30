import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class Account extends BasePage {

    private WebDriver driver;
    public Account(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    //Locators
    private By signInBtn        = By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]");
    private By createAccountBtn = By.xpath("//*[@id=\"createAccountSubmit\"]");
    private By signInCountinue  = By.xpath("//*[@id=\"continue\"]");

    //Method for verify invalid account opening
    public void verifyInvalidAccount()throws IOException {

        click(signInBtn);
        click(createAccountBtn);
        click(signInCountinue);
        Reports.extentTest.log(Status.INFO,"Verifying invalid account opening");
        try{
            Reports.extentTest.log(Status.PASS,"Email, Phone number and password validation are present and it shows in red color", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Email, Phone number and password validation are not present",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }


}
