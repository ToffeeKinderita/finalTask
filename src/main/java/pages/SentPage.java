package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentPage extends BaseClassAllPages {
    private By sentFolderLink = By.cssSelector("[href*=\"sent\"]");
    private By findSentEmailSelector = By.cssSelector("[role=main] tbody>tr:nth-child(1) .bog>b");

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public void goToSentFolder() {
        driver.findElement(sentFolderLink).click();
    }

    public String findSentEmail() {
        return driver.findElement(findSentEmailSelector).getText();
    }
}
