import helper.Listener;
import helper.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomeInboxPage;
import pages.LoginGmailPage;
import pages.SentPage;
import pages.TrashPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static pages.HomeInboxPage.emailSubject;
import static pages.LoginGmailPage.GMAIL_URL;

@Listeners(Listener.class)
public class SentAndTrashFoldersTest {
    private WebDriverSingleton instance = WebDriverSingleton.getInstance();
    private WebDriver driver = instance.openBrowser();
    private LoginGmailPage loginPage = new LoginGmailPage(driver);
    private HomeInboxPage inbox = new HomeInboxPage(driver);
    private TrashPage trash = new TrashPage(driver);
    private SentPage sentPage = new SentPage(driver);
    private String username = "seleniumtests10";
    private String password = "060788avavav";

    public SentAndTrashFoldersTest() throws MalformedURLException {
    }

    @BeforeClass
    public void start() {
        driver.get(GMAIL_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage.login(username, password);
    }

    @Feature("Sent Folder")
    @Description("Check sent emails are displayed inSent Folder")
    @Issue("4")
    @Test
    public void sendEmailAndCheckSentFolder() {
        inbox.writeEmailAndSend();
        sentPage.goToSentFolder();
        String getSubjectOfSentEmail = sentPage.findSentEmail();
        Assert.assertTrue(getSubjectOfSentEmail.equals(emailSubject));
    }

    @Feature("Trash Folder")
    @Description("Check deleting and appearing of emails in the bin")
    @Issue("5")
    @Test
    public void deleteEmailAndCheckTrashFolder() {
        inbox.selectEmail();
        String getSubjOfEmail = inbox.getEmailSubject();
        inbox.clickDeleteButton();
        inbox.clickMoreButton();
        trash.goToTrashFolder();
        String getSubjectOfDeletedEmail = trash.findDeletedEmail();
        Assert.assertEquals(getSubjectOfDeletedEmail, getSubjOfEmail);
    }

    @AfterClass
    public void finishCompletely() {
        driver.quit();
    }
}
