
package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.ProductPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    private static final String PRODUCT_URL =
            "https://kontakt.az/iphone-18-pro-256-gb-burgundy";

    @Test
    public void productPageOpens() {
        ProductPage productPage = new ProductPage(driver);

        productPage.open(PRODUCT_URL);

        Assert.assertTrue(
                productPage.getCurrentUrl().contains("kontakt.az"),
                "Kontakt.az page did not open"
        );

        Assert.assertFalse(
                productPage.getPageTitle().isBlank(),
                "Page title should not be empty"
        );
    }
    @Test
    public void addToCartButtonIsDisplayedAndClickable() {
        ProductPage productPage = new ProductPage(driver);
        productPage.open(PRODUCT_URL);

        Assert.assertTrue(
                productPage.isAddToCartButtonDisplayed(),
                "Add to Cart button is not displayed"
        );

        Assert.assertTrue(
                productPage.isAddToCartButtonClickable(),
                "Add to Cart button is not enabled"
        );
        productPage.clickAddToCart();

    }
    @Test
    public void userCanOpenCart() {
        ProductPage productPage = new ProductPage(driver);

        productPage.open(PRODUCT_URL);
        productPage.openCart();

        Assert.assertTrue(
                productPage.getCartUrl().contains("/cart"),
                "Cart page did not open"
        );
    }
}
