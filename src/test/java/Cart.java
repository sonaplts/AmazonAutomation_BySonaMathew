import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;


public class Cart extends BasePage {

    private WebDriver driver;
    public Cart(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    //Locators
    private By cartCount            = By.xpath("//*[@id=\"nav-cart-count\"]");
    private By searchBox            = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    private By searchIcon           = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    private By productLink          = By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[3]/div/span/div/div/div/div/div[2]/div[1]/div/div");
    private By qtyLink              = By.xpath("//*[@id=\"quantity\"]");
    private By qtyOption            = By.xpath("//*[@id=\"quantity\"]");
    private By qtyOptionSelected    = By.xpath("//*[@id=\"quantity\"]/option[3]");
    private By cartBtn              = By.xpath("//*[@id=\"add-to-cart-button\"]");
    private By viewCart             = By.xpath("//*[@id=\"hlb-view-cart-announce\"]");
    private By quantity             = By.xpath("//*[@id=\"a-autoid-0-announce\"]/span[2]");
    private By saveLater            = By.xpath("//*[@id=\"sc-item-C7a2b4ee6-8d1b-4fdf-9f4b-2c79985b7a31\"]/div[4]/div/div[1]/div/div/div[2]/div[1]/span[3]/span/input");
    private By deleteItem             = By.xpath("//*[@id=\"sc-item-C8cae126b-6f58-4a70-8c6d-1ef0d73ff0d1\"]/div[4]/div/div[1]/div/div/div[2]/div[1]/span[2]/span/input");


    // To verify add to cart
    public void verifyAddToCart(String key)throws InterruptedException,IOException {
        // To handle multiple  windows
        String parentWindow = driver.getWindowHandle();
        Set<String> childWindow = driver.getWindowHandles();
        isElementReachableSearch(cartCount);
        if (getNoOfCartItems(cartCount) == 0) {
            isElementReachableSearch(searchBox);
            sendElements(searchBox, key);
            isElementReachableSearch(searchIcon);
            click(searchIcon);
            isElementReachableSearch(productLink);
            click(productLink);
            for (String window : childWindow) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    try {
                        click(qtyLink);
                        isElementReachableSearch(qtyLink);
                        driver.manage().window().setPosition(new Point(2000, 0));
                        click(qtyOptionSelected);
                        scroll(1000);
                        isElementReachableSearch(cartBtn);
                        click(cartBtn);
                        isElementReachableSearch(viewCart);
                        click(viewCart);
                        String countSecond = getText(quantity);
                        Reports.extentTest.log(Status.INFO, "The result count is" + countSecond);
                        Assert.assertEquals(Integer.parseInt(countSecond), 3, "The cart count is correct, actual count is " + countSecond);
                        Reports.extentTest.log(Status.PASS, "The product is successfully added to the cart", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

                    } catch (Exception e) {
                        Reports.extentTest.log(Status.INFO, "Product is not added to cart", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
                    }
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
        }

    }

    // To verify save for later functionality
    public void verifySaveForLater(String key)throws InterruptedException,IOException {
        // To handle multiple  windows
        String parentWindow = driver.getWindowHandle();
        Set<String> childWindow = driver.getWindowHandles();
        isElementReachableSearch(cartCount);
        if (getNoOfCartItems(cartCount) == 0) {
            isElementReachableSearch(searchBox);
            sendElements(searchBox, key);
            isElementReachableSearch(searchIcon);
            click(searchIcon);
            isElementReachableSearch(productLink);
            click(productLink);
            for (String window : childWindow) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    try {
                        click(qtyLink);
                        isElementReachableSearch(qtyLink);
                        driver.manage().window().setPosition(new Point(2000, 0));
                        click(qtyOptionSelected);
                        scroll(1000);
                        isElementReachableSearch(cartBtn);
                        click(cartBtn);
                        isElementReachableSearch(viewCart);
                        click(viewCart);
                        isElementReachableSearch(saveLater);
                        click(saveLater);
                        int countSecond = getNoOfCartItems(cartCount);
                        Reports.extentTest.log(Status.INFO, "The result count is" + countSecond);
                        Assert.assertEquals(countSecond, 3, "The cart count is correct, actual count is " + countSecond);
                        Reports.extentTest.log(Status.PASS, "The product is added to cart and it saved for later", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

                    } catch (Exception e) {
                        Reports.extentTest.log(Status.INFO, "Save for later item is not working", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
                    }
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
        }

    }

    // To remove the item from cart
    public void removeCartItem(String key)throws InterruptedException,IOException {
        // To handle multiple  windows

        isElementReachableSearch(cartCount);
        if (getNoOfCartItems(cartCount) == 0) {
            isElementReachableSearch(searchBox);
            sendElements(searchBox, key);
            isElementReachableSearch(searchIcon);
            click(searchIcon);
            isElementReachableSearch(productLink);
            click(productLink);
            String parentWindow = driver.getWindowHandle();
            Set<String> childWindow = driver.getWindowHandles();
            for (String window : childWindow) {
                System.out.println("sona");
                Thread.sleep(10000);
                if (!window.equals(parentWindow)) {
                    Thread.sleep(10000);
                    driver.switchTo().window(window);
                    try {
                        Thread.sleep(10000);
                        click(qtyLink);
                        isElementReachableSearch(qtyLink);
                        driver.manage().window().setPosition(new Point(2000, 0));
                        click(qtyOptionSelected);
                        scroll(1000);
                        isElementReachableSearch(cartBtn);
                        click(cartBtn);
                        isElementReachableSearch(viewCart);
                        click(viewCart);
                        isElementReachableSearch(deleteItem);
                        click(deleteItem);
                        int countSecond = getNoOfCartItems(cartCount);
                        Reports.extentTest.log(Status.INFO, "The result count is" + countSecond);
                        Assert.assertEquals(countSecond, 3, "The cart count is correct, actual count is " + countSecond);
                        Reports.extentTest.log(Status.PASS, "The product is added to cart and it saved for later", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

                    } catch (Exception e) {
                        Reports.extentTest.log(Status.INFO, "Save for later item is not working", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
                    }
                  //  driver.close();
                }
            }
          //  driver.switchTo().window(parentWindow);
        }

    }



}
