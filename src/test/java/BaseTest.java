import Pages.LoginPage;
import Utils.DataDriven;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
@AfterTest
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
    @Test(description = "Verify Login Without Password")
    public void verifyLoginWithoutPassword() {
        JsonObject root = DataDriven.jsonReader();
        JsonObject validUsernameOnly = root.getAsJsonObject("valid_username_only");
        String username = validUsernameOnly.get("username").getAsString();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(""); // leave password empty
        loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.toLowerCase().contains("password is required"),
                "Expected error containing 'Password is required', but got: " + error);
    }
}


