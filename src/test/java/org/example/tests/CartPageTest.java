package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    @Test
    public void cartPageOpens() {
        CartPage cartPage = new CartPage(driver);

        cartPage.open();

        Assert.assertTrue(
                cartPage.isCartPageOpened(),
                "Cart page did not open"
        );
    }
}