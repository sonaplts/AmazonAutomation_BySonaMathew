import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    public static WebDriver openBrowser(){
        String baseDirectory=System.getProperty("user.dir");
        //Linux system path
        System.setProperty("webdriver.chrome.driver","/home/plabs/Documents/assigments/chromedriver");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.in");
        return driver;
    }


    public static void closeBrowser(WebDriver driver){
        driver.close();
    }
}
