import Pages.InventoryPage;
import Pages.LoginPage;
import Utils.DataDriven;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class InventoryTest extends BaseTest{
    @BeforeMethod
    public void loginBeforeTest()
    {
        JsonObject root = DataDriven.jsonReader();
        JsonObject valid = root.getAsJsonObject("valid");
        String username = valid.get("username").getAsString();
        String password = valid.get("password").getAsString();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);
    }
    @Test(description = "Verify Inventory Page Elements After Login")
    public void verifyInventoryPageElements() {
        InventoryPage inventoryPage = new InventoryPage(driver);


        String title = inventoryPage.getPageTitle();
        Assert.assertEquals(title, "Swag Labs", "Page title mismatch");


        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon should be visible on inventory page");


        int productCount = inventoryPage.getProductCount();
        Assert.assertEquals(productCount, 6, "Expected 6 products on inventory page");
    }
}
