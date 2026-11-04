import Pages.LoginPage;
import Utils.DataDriven;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {
        JsonObject root = DataDriven.jsonReader();
        JsonObject valid = root.getAsJsonObject("valid");
        String username = valid.get("username").getAsString();
        String password = valid.get("password").getAsString();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/inventory.html"), "Expected to be on inventory page, but URL was: " + currentUrl);
    }
    @Test
    public void verifyUnsuccessfulLogin() {
        JsonObject root = DataDriven.jsonReader();
        JsonObject invalid = root.getAsJsonObject("invalid");
        String username = invalid.get("username").getAsString();
        String password = invalid.get("password").getAsString();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);


        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage);
        Assert.assertTrue(errorMessage.contains("Epic sadface"), "Expected error message not found.");
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