import helper.Listener;
import helper.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginGmailPage;
import pages.LogoutGmailPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static pages.LoginGmailPage.GMAIL_URL;

@Listeners(Listener.class)
public class LogoutFromGmailTest {
    private WebDriverSingleton instance = WebDriverSingleton.getInstance();
    private WebDriver driver = instance.openBrowser();
    private LoginGmailPage loginPage = new LoginGmailPage(driver);
    private LogoutGmailPage logoutPage = new LogoutGmailPage(driver);
    private String textOfLogoutPage = "Google";

    public LogoutFromGmailTest() throws MalformedURLException {
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{"seleniumtests10", "060788avavav"},
                new Object[]{"seleniumtests30", "060788avavav"},
                new Object[]{"seleniumtests40", "060788avavav"},
        };
    }

    @BeforeClass
    public void start() {
        driver.get(GMAIL_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Feature("Logout from Gmail")
    @Description("Check logout functionality")
    @Issue("3")
    @Test(dataProvider = "testData")
    public void logoutFromGmail(String username, String password) {
        loginPage.login(username, password);
        logoutPage.logoutGmail();
        Assert.assertTrue(logoutPage.checkLogoutPageTitle().contains(textOfLogoutPage));
    }

    @AfterMethod
    public void afterFinishMethod() {
        logoutPage.navigationBtwChangeAccounts();
    }

    @AfterClass
    public void finishCompletely() {
        driver.quit();
    }
}
