import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CartTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    // Runs after BaseTest.setup(), so every test starts logged in on the inventory page.
    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
    }

    /**
     * Switches to the tab the link just opened, waits for its page to start loading,
     * returns its URL, then closes it and goes back to the original tab.
     */
    private String urlOfNewTab(String originalTab) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(d -> !d.getCurrentUrl().startsWith("about:blank"));
        String url = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalTab);
        return url;
    }

    @Test
    public void testSocialIcons() {
        String originalTab = driver.getWindowHandle();

        // LinkedIn
        inventoryPage.clickLinkedIn();
        String linkedInUrl = urlOfNewTab(originalTab);
        Assert.assertTrue(linkedInUrl.contains("linkedin"), "Unexpected LinkedIn URL: " + linkedInUrl);

        // Facebook
        inventoryPage.clickFacebook();
        String facebookUrl = urlOfNewTab(originalTab);
        Assert.assertTrue(facebookUrl.contains("facebook"), "Unexpected Facebook URL: " + facebookUrl);

        // Twitter (twitter.com now redirects to x.com)
        inventoryPage.clickTwitter();
        String twitterUrl = urlOfNewTab(originalTab);
        Assert.assertTrue(twitterUrl.contains("x.com") || twitterUrl.contains("twitter.com"),
                "Unexpected Twitter/X URL: " + twitterUrl);
    }

    @Test
    public void testCartEmpty() {
        inventoryPage.openCart();

        Assert.assertEquals(cartPage.getItemsCount(), 0,
                "Cart is not empty");
    }

    @Test
    public void testAddThreeProducts() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProduct("Sauce Labs Onesie");

        inventoryPage.openCart();

        List<String> items = cartPage.getCartItemNames();

        Assert.assertTrue(items.contains("Sauce Labs Backpack"));
        Assert.assertTrue(items.contains("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(items.contains("Sauce Labs Onesie"));
    }

    @Test
    public void testRemoveOneProduct() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProduct("Sauce Labs Onesie");

        inventoryPage.openCart();
        cartPage.removeItemByName("Sauce Labs Bolt T-Shirt");

        inventoryPage.goBack();

        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Bolt T-Shirt"), "Add to cart");
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Backpack"), "Remove");
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Onesie"), "Remove");
    }
}
