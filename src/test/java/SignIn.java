import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;

public class SignIn extends BasePage {

    private WebDriver driver;
    public SignIn(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    private By primeBtn         = By.xpath("//*[@id=\"nav-link-prime\"]");
    private By loginPrimeBtn    = By.xpath("//*[@id=\"a-autoid-0-announce\"]");
    private By signInTitle      = By.xpath("//*[contains(text(), 'Sign-In')]");

    //Verify sign in title
    public void verifySignIn()throws IOException {
        Reports.extentTest.log(Status.INFO,"Sign In header verification");
        click(primeBtn);
        isElementReachable(primeBtn);
        click(loginPrimeBtn);
        isElementReachable(loginPrimeBtn);
        try {
            isElementReachable(signInTitle);
            String actualTitle = getText(signInTitle);
            boolean contains = actualTitle.contains("Sign-In");
            Assert.assertTrue(contains);
            Reports.extentTest.log(Status.PASS, "Sign in header is verified.The header title is " + actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Sign in header verification is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }


}
