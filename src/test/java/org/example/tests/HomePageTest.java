
package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest extends BaseTest {

    //@Test
//    public void openHomePage() {
//        HomePage homePage = new HomePage(driver);
//
//        homePage.open();
//
//        String currentUrl = homePage.getCurrentUrl();
//
//        Assert.assertTrue(
//                currentUrl.contains("kontakt.az"),
//                "Homepage did not open. Actual URL: " + currentUrl
//        );
//    }

    @Test(dataProvider = "productNames")
    public void searchInputAcceptsProductName(String productName) {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        Assert.assertTrue(
                homePage.isSearchInputDisplayed(),
                "Search input is not displayed"
        );

        homePage.enterSearchText(productName);

        Assert.assertEquals(
                homePage.getSearchInputValue(),
                productName,
                "Search input did not accept the product name" + productName
        );
    }

    @Test
    public void searchForProduct() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.searchForProduct("Samsung");

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("/search?q=Samsung"),
                "Search navigation failed. Actual URL: " + currentUrl
        );
    }
    @DataProvider(name = "productNames")
    public Object[][] productNames(){
        return new Object[][]{
                {"Samsung"},
                {"Iphone"},
                {"13254"},
                {"@?>"}
        };
    }
}

