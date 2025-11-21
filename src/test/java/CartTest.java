import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CartTest extends BaseTest {
    LoginPage loginPage;
    pages.InventoryPage inventoryPage;
    pages.CartPage cartPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new pages.InventoryPage(driver);
        cartPage = new pages.CartPage(driver);


        loginPage.login("standard_user", "secret_sauce");
    }

    private void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @Test
    public void testSocialIcons() {
        loginPage.login("standard_user", "secret_sauce");

        // LinkedIn
        inventoryPage.clickLinkedIn();
        switchToNewTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());

        // Facebook
        inventoryPage.clickFacebook();
        switchToNewTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());

        // Twitter
        inventoryPage.clickTwitter();
        switchToNewTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("x.com"));
        driver.close();
    }

    @Test
    public void testCartEmpty() {
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage.openCart();

        Assert.assertEquals(cartPage.getItemsCount(), 0,
                "Cart is not empty");
    }

    @Test
    public void testAddThreeProducts() {
        loginPage.login("standard_user", "secret_sauce");

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
        loginPage.login("standard_user", "secret_sauce");

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
