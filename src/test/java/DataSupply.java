import org.testng.annotations.DataProvider;

public class DataSupply {

    //Test url
    @DataProvider(name = "testUrl")
    public Object[][] testUrl(){
        //write code to retrieve data
        return new Object[][]{{"https://www.amazon.in"}};
    }
    //Search key
    @DataProvider(name = "searchData")
    public Object[][] searchItem(){
        //write code to retrieve data
        return new Object[][]{{"iPhone 12"}};
    }

    //Valid pincoe
    @DataProvider(name = "validPincode")
    public Object[][] validPincode(){
        //write code to retrieve data
        return new Object[][]{{"695011"}};
    }


    //Invalid pincoe
    @DataProvider(name = "invalidPincode")
    public Object[][] invalidPincode(){
        //write code to retrieve data
        return new Object[][]{{"1234"}};
    }

    //Page title
    @DataProvider(name = "pageTitle")
    public Object[][] pageTitle(){
        //write code to retrieve data
        return new Object[][]{{"Hello. What can we help you with?"}};
    }

    //Book title
    @DataProvider(name = "bookTitle")
    public Object[][] bookTitle(){
        //write code to retrieve data
        return new Object[][]{{"Da Vinci Code"}};
    }




}
