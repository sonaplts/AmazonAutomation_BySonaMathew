import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    // To locate elments
    public WebElement locateElement(By locator){
        WebDriverWait wait=new WebDriverWait(driver,20);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //todo findelements ,operation sendkeys etc
    public void click(By locator){
        locateElement(locator).click();
    }
    // To take screenshots
    public String takeScreenshot() throws IOException {

        File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Random random=new Random();
        String fileName="Screenshot"+random.nextInt(10000);
        Files.move(Paths.get(screenshot.getAbsolutePath()), Paths.get("/home/plabs/Documents/assigments/ICTSubimissionApril2021-master/report/"+fileName+".png"));
        return  fileName+".png";
    }
    // To get text
    public String getText(By locator){
        return locateElement(locator).getText();
    }


    //todo findelements ,operation sendkeys etc

    public void sendElements(By locator,String product) {
        locateElement(locator).sendKeys(product);
    }
    // Wait time
    public void isElementReachable(By locator){
        int i=0;
        WebDriverWait wait = new WebDriverWait(driver, 15);
        while (i < 10) {// increase as required
            try{
                //locate element use explicit wait
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            }
            catch(Exception e)
            {
                i++;
            }
        }
    }
    // Wait time
    public void isElementReachableSearch(By locator){
        int i=0;
        WebDriverWait wait = new WebDriverWait(driver, 15);
        while (i < 50) {// increase as required
            try{
                //locate element use explicit wait
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            }
            catch(Exception e)
            {
                i++;
            }
        }
    }
    // To scroll
    public void scroll(Integer length){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,length)");
    }

    // To get no of items in the cart
    public Integer getNoOfCartItems(By locator){
      int total = Integer.parseInt(locateElement(locator).getText());
      return total;
    }

}
