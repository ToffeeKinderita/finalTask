package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeInboxPage extends BaseClassAllPages {
    private By selectFirstEmailInFolder = By.cssSelector("[role=tabpanel] tbody tr:first-child [role=checkbox]");
    private By findEmailSubjectSelector = By.cssSelector("[role=main] tbody tr:first-child .bog");
    private By deleteButton = By.cssSelector("[act=\"10\"]");
    private By writeEmailButton = By.xpath("//div[text()='COMPOSE']");
    private By fieldTo = By.cssSelector("textarea[aria-label=To]");
    private By fieldSubject = By.cssSelector("input[name=subjectbox]");
    private By mailBody = By.cssSelector("div[role=textbox]");
    private By sendEmailButton = By.xpath("//div[text()='Send']");
    private By moreButton = By.cssSelector(".n6 [role=button]");
    private String emailTo = "seleniumtests30@gmail.com";
    public static String emailSubject = "Selenium test by Tamara";
    private String emailBody = "Hello";

    public HomeInboxPage(WebDriver driver) {
        super(driver);
    }

    public void writeEmailAndSend() {
        driver.findElement(writeEmailButton).click();
        waitForElementToPerformActions((fieldTo)).sendKeys(emailTo);
        driver.findElement(fieldSubject).sendKeys(emailSubject);
        driver.findElement(mailBody).sendKeys(emailBody);
        driver.findElement(sendEmailButton).click();
    }

    public void selectEmail() {
        driver.findElement(selectFirstEmailInFolder).click();
    }

    public String getEmailSubject() {
        return driver.findElement(findEmailSubjectSelector).getText();
    }

    public void clickDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public void clickMoreButton() {
        driver.findElement(moreButton).click();
    }
}
