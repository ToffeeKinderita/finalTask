import helper.Listener;
import helper.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomeInboxPage;
import pages.LoginGmailPage;
import pages.LogoutGmailPage;
import pages.SentPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static pages.HomeInboxPage.emailSubject;

@Listeners(Listener.class)
public class EmailSendingReceivingTest {
    private WebDriverSingleton instance = WebDriverSingleton.getInstance();
    private WebDriver driver = instance.openBrowser();
    private LoginGmailPage loginPage = new LoginGmailPage(driver);
    private LogoutGmailPage logoutPage = new LogoutGmailPage(driver);
    private HomeInboxPage inbox = new HomeInboxPage(driver);
    private String username = "seleniumtests10";
    private String password = "060788avavav";
    private String username2 = "seleniumtests10";
    private String password2 = "060788avavav";

    public EmailSendingReceivingTest() throws MalformedURLException {
    }

    @BeforeClass
    public void start() {
        driver.get("https://accounts.google.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage.login(username, password);
    }

    @Feature("Send Email / Receive Email")
    @Description("Check send/receive of emails by different users")
    @Issue("1")
    @Test
    public void sendEmailAndCheckItsReceiving() {
        inbox.writeEmailAndSend();
        logoutPage.logoutGmail();
        logoutPage.navigationBtwChangeAccounts();
        loginPage.login(username2, password2);
        inbox.getEmailSubject();
        Assert.assertEquals(inbox.getEmailSubject(), emailSubject);
    }

    @AfterClass
    public void finishCompletely() {
        driver.quit();
    }
}

