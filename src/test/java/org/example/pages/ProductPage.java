
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By addToCartButton =
            By.cssSelector(".product-sidebar__cart-btn");
    private final By cartLink =
            By.cssSelector("a.header__action[href='/cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void open(String productUrl) {
        driver.get(productUrl);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return wait.until(
                webDriver -> {
                    String title = webDriver.getTitle();
                    return title != null && !title.isBlank()
                            ? title
                            : null;
                }
        );
    }
    public boolean isAddToCartButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(addToCartButton)
        ).isDisplayed();
    }
    public boolean isAddToCartButtonClickable() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        ).isEnabled();
    }

    public void clickAddToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        ).click();
    }
    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/cart")
        );
    }

    public String getCartUrl() {
        return driver.getCurrentUrl();
    }

}
