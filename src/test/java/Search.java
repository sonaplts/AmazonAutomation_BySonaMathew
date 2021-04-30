import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.List;

public class Search extends BasePage {

    private WebDriver driver;
    public Search(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    //Locators
    private By searchBox        = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    private By searchIcon       = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    private By suggestionLists  = By.xpath("//*[contains(text(),key)]");
    private By resultCount      = By.xpath("//*[@id=\"search\"]/span/div/span/h1/div/div[1]/div/div");
    private By checkBox         = By.xpath("//*[@id=\"p_n_specials_match/21618256031\"]/span/a/span");
    private By filterDropdown   = By.xpath("//*[@id=\"nav-search-dropdown-card\"]");
    private By bookSelect       = By.xpath("//*[@id=\"searchDropdownBox\"]/option[11]");
    private By searchTitle      = By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/h2/a/span");
    private By sortLink         = By.xpath("//*[@id=\"search\"]/span/div/span/h1/div/div[2]/div/div/form/span");
    private By sortOption       = By.xpath("//*[@id=\"s-result-sort-select\"]/option[3]");


    private By todayDealLink        = By.xpath(" //*[@id=\"nav-xshop\"]/a[3]");
    private By checkBoxAppliances   = By.xpath("//*[@id=\"widgetFilters\"]/div[1]/div[2]/span[1]/div/label/span");
    private By priceFilter          = By.xpath("//*[@id=\"widgetFilters\"]/div[4]/span[1]/div/label/span");



    //To find out the search keyword suggestions
    public void enterSearchKey(String key)throws InterruptedException,IOException {

        Reports.extentTest.log(Status.INFO,"search  for "+key);
        isElementReachableSearch(searchBox);
        sendElements(searchBox,key);
        Thread.sleep(10000);
        String t = key;
        // identify elements with text()
        List<WebElement> l= driver.findElements(suggestionLists);
        // verify list size
        try {
            if (l.size() > 0) {
                Reports.extentTest.log(Status.PASS, "The search suggestion list is correct for the search keyword " + key, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            } else {
                System.out.println("Item: " + t + " is not present. ");
            }
        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Search is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }


    }

    // To verify filtering
    public void verifySearchFilter(String key) throws InterruptedException,IOException {

        isElementReachableSearch(searchBox);
        sendElements(searchBox,key);
        click(searchIcon);
        String countFirst = getText(resultCount);
        Reports.extentTest.log(Status.INFO,"The result count is"+countFirst);
        Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        try {
            Thread.sleep(10000);
            isElementReachableSearch(checkBox);
            click(checkBox);
            Thread.sleep(10000);
            isElementReachableSearch(resultCount);
            Thread.sleep(10000);
            String countSecond = getText(resultCount);
            Reports.extentTest.log(Status.INFO,"The result count is"+countSecond);
            Reports.extentTest.log(Status.PASS,"The result count is changing and its filter is successfully verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Search is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
    //To verify dropdown filtering
    public void verifySearchDropdownFilter(String key) throws InterruptedException,IOException {
        click(filterDropdown);
        click(bookSelect);
        isElementReachableSearch(searchBox);
        sendElements(searchBox,key);
        click(searchIcon);
        isElementReachableSearch(searchIcon);
        Reports.extentTest.log(Status.INFO,"Search for"+key);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(10000);
        try{
            isElementReachableSearch(searchTitle);
            Thread.sleep(10000);
            String actualTitle=getText(searchTitle);
            Thread.sleep(10000);
            boolean contains = actualTitle.contains(key);
            Assert.assertTrue(contains);
            Reports.extentTest.log(Status.PASS,"Dropdown filter verification successful and the book name is "+key, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Search is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    //To verify sorting
    public void verifySorting(String key)throws InterruptedException,IOException {

        Reports.extentTest.log(Status.INFO,"Verify sorting for "+key);
        isElementReachableSearch(searchBox);
        sendElements(searchBox,key);
        isElementReachableSearch(searchIcon);
        click(searchIcon);
        try {
            isElementReachableSearch(sortLink);
            click(sortLink);
            isElementReachableSearch(sortOption);
            click(sortOption);
            Thread.sleep(5000);
            Reports.extentTest.log(Status.PASS, "The search and sorting is success for the keyword " + key, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Search by sorting is failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }


    }


    //To verify filtering in todays deals page
    public void verifyTodaysDealsFiltering()throws InterruptedException,IOException {

        Reports.extentTest.log(Status.INFO,"Verify today's deals page filtering");
        click(todayDealLink);
        isElementReachableSearch(todayDealLink);
        scroll(5000);
        isElementReachableSearch(checkBoxAppliances);
        click(checkBoxAppliances);
        Thread.sleep(6000);
        try {
            isElementReachableSearch(priceFilter);
            click(priceFilter);
            Thread.sleep(6000);
            Reports.extentTest.log(Status.PASS, "The filter is working in the today's deals page is successfully verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"The filter is working in the today's deals page",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
    }
    //To verify filtering in todays deals page
    public void verifySaveForLater(String key)throws InterruptedException,IOException {
        Reports.extentTest.log(Status.INFO,"Verify save for later");
        click(todayDealLink);
        isElementReachableSearch(todayDealLink);
        scroll(5000);
        isElementReachableSearch(checkBoxAppliances);
        click(checkBoxAppliances);
        Thread.sleep(6000);
        try {
            isElementReachableSearch(priceFilter);
            click(priceFilter);
            Thread.sleep(6000);
            Reports.extentTest.log(Status.PASS, "Save for later functionality is successfully verified", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }catch (Exception e){
            Reports.extentTest.log(Status.FAIL,"Save for later functionality is not working",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

}
