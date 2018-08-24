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
public class LoginToGmailTest {
    private WebDriverSingleton instance = WebDriverSingleton.getInstance();
    private WebDriver driver = instance.openBrowser();
    private LoginGmailPage loginPage = new LoginGmailPage(driver);
    private LogoutGmailPage logoutPage = new LogoutGmailPage(driver);

    public LoginToGmailTest() throws MalformedURLException {
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

    @Feature("Login to Gmail")
    @Description("Check login functionality")
    @Issue("2")
    @Test(dataProvider = "testData")
    public void loginToGmail(String usernme, String pass) {
        loginPage.login(usernme, pass);
        String accountName = loginPage.findLoggedAccountName();
        Assert.assertTrue(accountName.contains(usernme + "@gmail.com"), "Account name is incorrect" + accountName);
    }

    @AfterMethod
    public void afterFinishMethod() {
        logoutPage.logoutGmail();
        logoutPage.navigationBtwChangeAccounts();
    }

    @AfterClass
    public void finishCompletely() {
        driver.quit();
    }
}
