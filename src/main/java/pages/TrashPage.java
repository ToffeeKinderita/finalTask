package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrashPage extends BaseClassAllPages {
    private By goTrashFolder = By.cssSelector("[href*=\"trash\"]");
    private By findDeletedEmail = By.cssSelector("[role=main] tbody tr:first-child .bog");

    public TrashPage(WebDriver driver) {
        super(driver);
    }

    public void goToTrashFolder() {
        waitForElementToPerformActions(goTrashFolder).click();
    }

    public String findDeletedEmail() {
        return waitForElementToPerformActions((findDeletedEmail)).getText();
    }
}
