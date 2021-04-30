import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
// To verify footer elements
public class VerifyFooter extends BasePage {

    private WebDriver driver;
    public VerifyFooter(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    //Locators
    private By aboutLink = By.linkText("About Us");

    //To check about us page is opened or not
    public void openAboutUs()throws IOException {
        Reports.extentTest.log(Status.INFO,"search  for About us page ");
        scroll(4500);
        try{
            click(aboutLink);
            Reports.extentTest.log(Status.PASS,"About us page is opened.Footer element verification is success", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Footer elememnt verification is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }


}
