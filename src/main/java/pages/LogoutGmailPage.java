package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutGmailPage extends BaseClassAllPages {
    private By profileIdentifierSelector = By.xpath("//div[@id='profileIdentifier']/..");
    private By useAnotherAccountSelector = By.xpath("//p[text()='Use another account']/..");
    private By signOutDropdownSelector = By.cssSelector("[href*=\"SignOutOptions\"]");
    private By logoutButton = By.cssSelector("[href*=\"Logout\"]");
    private By logoutPageTitle = By.cssSelector("#logo");

    public LogoutGmailPage(WebDriver driver) {
        super(driver);
    }

    public void logoutGmail() {
        waitForElementToPerformActions(signOutDropdownSelector).click();
        driver.findElement(logoutButton).click();
    }

    public String checkLogoutPageTitle() {
        return driver.findElement(logoutPageTitle).getAttribute("title");
    }

    public void navigationBtwChangeAccounts() {
        if (isElementPresent(profileIdentifierSelector)) {
            driver.findElement(profileIdentifierSelector).click();
            waitForElementToPerformActions(useAnotherAccountSelector).click();
        } else {
            waitForElementToPerformActions(useAnotherAccountSelector).click();
        }
    }
}
