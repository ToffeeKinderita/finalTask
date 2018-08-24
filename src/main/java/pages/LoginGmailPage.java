package pages;

import helper.IncorrectPageLoadedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginGmailPage extends BaseClassAllPages {
    public static final String GMAIL_URL = "https://accounts.google.com";
    private By navigateToGmailSelector = By.cssSelector("[role=navigation]>a[href*=\"mail\"]");
    private By usernameSelector = By.cssSelector("input[type=email]");
    private By passwordSelector = By.cssSelector("input[type=password]");
    private By nextButtonOnUsernameSelector = By.cssSelector("#identifierNext");
    private By nextButtonOnPasswordSelector = By.cssSelector("#passwordNext");
    private By signOutDropdownSelector = By.cssSelector("[href*=\"SignOutOptions\"]");

    public LoginGmailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get(LoginGmailPage.GMAIL_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if (!url.equals(LoginGmailPage.GMAIL_URL)) {
            try {
                throw new IncorrectPageLoadedException(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void login(String usernme, String pass) {
        waitForElementToPerformActions(usernameSelector).sendKeys(usernme);
        waitForElementToPerformActions(nextButtonOnUsernameSelector).click();
        waitForElementToPerformActions(passwordSelector).sendKeys(pass);
        driver.findElement(nextButtonOnPasswordSelector).click();
        checkGeneralPage();
    }

    public String findLoggedAccountName() {
        return driver.findElement(signOutDropdownSelector).getAttribute("title");
    }

    public void navigateToGmailPage() {
        driver.findElement(navigateToGmailSelector).click();
    }

    public void checkGeneralPage() {
        if (isElementPresent(navigateToGmailSelector)) {
            navigateToGmailPage();
        } else {
        }
    }
}
