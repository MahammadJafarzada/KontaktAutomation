
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.cssSelector(
            "form.header__search-form--desktop input[name='q']"
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void open() {
        driver.get("https://kontakt.az/");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isSearchInputDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        ).isDisplayed();
    }

    public void enterSearchText(String productName) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        input.clear();
        input.sendKeys(productName);

        wait.until(
                ExpectedConditions.attributeToBe(
                        searchInput,
                        "value",
                        productName
                )
        );
    }


    public String getSearchInputValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        ).getDomProperty("value");
    }


    public void searchForProduct(String productName) {
        enterSearchText(productName);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchInput)
        ).sendKeys(Keys.ENTER);

        wait.until(
                ExpectedConditions.urlContains("/search?q=")
        );
    }




    public void clearSearchInput() {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.BACK_SPACE);

        wait.until(driver -> {
            WebElement currentInput = driver.findElement(searchInput);
            return currentInput.getDomProperty("value").isEmpty();
        });
    }




}
