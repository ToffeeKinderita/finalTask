package pages;

import helper.IncorrectPageLoadedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassAllPages extends LoadableComponent<BaseClassAllPages> {
    public WebDriver driver;

    public BaseClassAllPages(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToPerformActions(By locator) {
        return new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(final By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
    }
}
