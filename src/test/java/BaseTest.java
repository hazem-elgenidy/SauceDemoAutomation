import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Opens a fresh Chrome on saucedemo.com before every test and closes it after.
 * Test classes extend this; it holds no tests itself.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver = new ChromeDriver(chromeOptions());
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

    private ChromeOptions chromeOptions()
    {
        ChromeOptions options = new ChromeOptions();

        // Chrome's password manager pop-ups ("save password", "password found in a
        // data breach") can get in the way of the tests, so switch them off.
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // No screen on CI servers (GitHub Actions sets CI=true): run Chrome headless.
        // Locally you can also run headless with: mvn test -Dheadless=true
        if (System.getenv("CI") != null || Boolean.getBoolean("headless"))
        {
            options.addArguments("--headless=new", "--window-size=1920,1080",
                    "--no-sandbox", "--disable-dev-shm-usage");
        }
        return options;
    }
}
