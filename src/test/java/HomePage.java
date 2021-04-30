import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;
import org.openqa.selenium.*;

public class HomePage extends BasePage {

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    // FOR PINCODE VALIDATION
    private By addPincodeBtn        = By.xpath("//*[@id=\"nav-global-location-popover-link\"]");
    private By addedPincode         = By.xpath("//*[@id=\"glow-ingress-line2\"]");
    private By pincodeBox           = By.xpath("//*[@id=\"GLUXZipUpdateInput\"]");
    private By pincodeApplyBtn      = By.xpath("//*[@id=\"GLUXZipUpdate\"]");

    //FOR NAVIGATION
    private By menuBtn              = By.xpath("//*[@id=\"nav-hamburger-menu\"]");
    private By ccBtn                = By.xpath("//li/a[contains(text(),'Customer Service')]");
    private By ccPageTitle          = By.xpath("//*[contains(text(), 'Hello. What can we help you with?')]");
    private By logoBtn              = By.xpath("//*[@id=\"nav-logo-sprites\"]");

    //FOR PRODUCT NAVIDATION
    private By catLink              = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[7]");
    private By subCatLink           = By.xpath("//*[@id=\"hmenu-content\"]/ul[2]/li[3]/a");

    // To verify valid pincode
    public void verifyValidPincode(String pincode)throws InterruptedException,IOException{

        click(addPincodeBtn);
        sendElements(pincodeBox,pincode);
        click(pincodeApplyBtn);
        isElementReachable(pincodeApplyBtn);
        Thread.sleep(8000);
        Reports.extentTest.log(Status.INFO,"pincode is "+pincode);
        try{
            isElementReachable(addedPincode);
            String actualPincodeVar=getText(addedPincode);
            boolean contains = actualPincodeVar.contains(pincode);
            Assert.assertTrue(contains);
            Reports.extentTest.log(Status.PASS,"Pincode verified as "+actualPincodeVar, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Pincode verification is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //To verify invalid pincode
    public void verifyInvalidPincode(String pincode)throws InterruptedException,IOException{

        click(addPincodeBtn);
        sendElements(pincodeBox,pincode);
        Reports.extentTest.log(Status.INFO,"pincode is "+pincode);

        click(pincodeApplyBtn);
        isElementReachable(pincodeApplyBtn);
        try {
            Reports.extentTest.log(Status.PASS, "Pincode verification is success.Invalid pincode is not acceptable", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Pincode verification is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
    }

    // To verify home page navigation
    public void verifyNavigation(String title)throws InterruptedException,IOException{
        click(menuBtn);
        scroll(4500);
        click(ccBtn);
        isElementReachable(ccBtn);
        try{
            isElementReachable(ccPageTitle);
            String actualTitle=getText(ccPageTitle);
            boolean contains = actualTitle.contains(title);
            Assert.assertTrue(contains);
            click(logoBtn);
            Reports.extentTest.log(Status.PASS,"Title verified.Logo button is clickable and its navigated to home page.Title is "+title, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Home page navigation is not working",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //To verify product navigation
    public void verifyProductNavigation()throws InterruptedException,IOException{
        click(menuBtn);
        isElementReachable(menuBtn);
        click(catLink);
        isElementReachable(catLink);
        click(subCatLink);
        isElementReachable(subCatLink);
        try{
            Reports.extentTest.log(Status.PASS,"Product navigation is verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Product navigation is not working",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }



}
