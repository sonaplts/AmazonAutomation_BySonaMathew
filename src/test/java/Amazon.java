import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Amazon {

    private WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        driver=Browser.openBrowser();
    }


    // Test ID = 1 , Validate Pincode
    @Test(dataProvider = "validPincode",dataProviderClass = DataSupply.class)
    public void verifyValidPincode(String validPincode) throws InterruptedException,IOException{
        Reports.createTest("Verify valid pincode");
        HomePage homePage=new HomePage(driver);
        homePage.verifyValidPincode(validPincode);
    }

    // Test ID = 2 , Validate Invalid login
    @Test(dataProvider = "invalidPincode",dataProviderClass = DataSupply.class)
    public void verifyInvalidPincode(String invalidPincode) throws InterruptedException,IOException{
        Reports.createTest("Verify invalid pincode");
        HomePage homePage=new HomePage(driver);
        homePage.verifyInvalidPincode(invalidPincode);
    }

    //Test ID = 3, Search an item from home page
    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
    public void verifySearch(String searchData) throws InterruptedException,IOException{
        Reports.createTest("Verify search");
        Search search=new Search(driver);
        search.enterSearchKey(searchData);
    }

    // Test ID = 13, Vefify footer elements
    @Test
    public void openAboutUs()throws InterruptedException,IOException{
        Reports.createTest("Open about us page");
        VerifyFooter verifyFooter=new VerifyFooter(driver);
        verifyFooter.openAboutUs();
    }

    // Test ID = 8, Vefify invalid account opening
    @Test
    public void verifyInvalidAccountOpen() throws InterruptedException,IOException{
        Reports.createTest("Verify invalid account opening");
        Account account=new Account(driver);
        account.verifyInvalidAccount();
    }

    // Test ID = 7, Vefify sign in page is displayed
    @Test
    public void verifySignIn() throws IOException{
        Reports.createTest("Verify sign in title");
        SignIn signIn=new SignIn(driver);
        signIn.verifySignIn();
    }

    // Test ID = 6, Vefify navigation to home page
    @Test(dataProvider = "pageTitle",dataProviderClass = DataSupply.class)
    public void verifyNavigation(String pageTitle) throws InterruptedException,IOException{
        Reports.createTest("Verify home page navigation");
        HomePage homePage=new HomePage(driver);
        homePage.verifyNavigation(pageTitle);
    }

    // Test ID = 11, Vefify navigation to product detail page
    @Test
    public void verifyProductNavigation() throws InterruptedException,IOException{
        Reports.createTest("Verify product navigation");
        HomePage homePage=new HomePage(driver);
        homePage.verifyProductNavigation();
    }

    // Test ID = 14, Vefify flter options
    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
    public void verifySearchFilter(String searchData) throws InterruptedException,IOException{
        Reports.createTest("Verify search and filter is working");
        Search searchFilter=new Search(driver);
        searchFilter.verifySearchFilter(searchData);
    }

    // Test ID = 15, Vefify filter option in search
    @Test(dataProvider = "bookTitle",dataProviderClass = DataSupply.class)
    public void verifySearchDropdownFilter(String bookTitle) throws InterruptedException,IOException{
        Reports.createTest("Verify search dropdown filter is working");
        Search searchFilter=new Search(driver);
        searchFilter.verifySearchDropdownFilter(bookTitle);

    }

    // Test ID = 10, Vefify sorting is done
    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
    public void verifySorting(String searchData) throws InterruptedException,IOException{
        Reports.createTest("Verify sorting");
        Search search=new Search(driver);
        search.verifySorting(searchData);
    }
    // Test ID = 9, verify price
    @Test
    public void verifyTodaysDealsFiltering() throws InterruptedException,IOException{
        Reports.createTest("Verify Today's deals filtering");
        Search search=new Search(driver);
        search.verifyTodaysDealsFiltering();
    }

    //    // Test ID = 4, Validate add to cart
//    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
//    public void verifyAddToCart(String searchData) throws InterruptedException,IOException{
//        Reports.createTest("Verify add to cart");
//        Cart addToCart=new Cart(driver);
//        addToCart.verifyAddToCart(searchData);
//    }
//    // Test ID = 12, validate able to move the item for save later
//    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
//    public void verifySaveForLater(String searchData) throws InterruptedException,IOException{
//        Reports.createTest("Verify save for later functionality");
//        Cart saveLater=new Cart(driver);
//        saveLater.verifySaveForLater(searchData);
//    }
//    //Test ID = 5, Validate remove item from cart
//    @Test(dataProvider = "searchData",dataProviderClass = DataSupply.class)
//    public void removeCartItem(String searchData) throws InterruptedException,IOException{
//        Reports.createTest("Verify the remove cart functionality");
//        Cart removeItem=new Cart(driver);
//        removeItem.removeCartItem(searchData);
//    }

    @AfterMethod
    public void closeBrowser(){
        Browser.closeBrowser(driver);
    }
}
