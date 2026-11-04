package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private WebDriver driver;

    private By cartIcon = By.id("shopping_cart_container");
    private By continueShoppingButton = By.id("continue-shopping");
    private By inventoryItems = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✔ Page checks used by InventoryTest
    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    // ✔ Open cart, and wait until the cart page has actually loaded
    // (otherwise the next step can still see the inventory page)
    public void openCart() {
        driver.findElement(cartIcon).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("cart.html"));
    }

    // ✔ Add a product by name
    public void addProduct(String productName) {
        driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")
        ).click();
    }

    // ✔ Remove a product by name
    public void removeProduct(String productName) {
        driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[text()='Remove']")
        ).click();
    }

    // ✔ Get button text for a product
    public String getButtonText(String productName) {
        return driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")
        ).getText();
    }

    // ✔ Go back from cart to inventory ("Continue Shopping" on the cart page)
    public void goBack() {
        driver.findElement(continueShoppingButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("inventory.html"));
    }

    // ✔ Social icons (inventory page also has them)
    private By linkedinIcon = By.cssSelector("a[href*='linkedin']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    // Twitter is now X, so match the link whether it points to twitter.com or x.com
    private By twitterIcon = By.cssSelector("a[data-test='social-twitter'], a[href*='twitter'], a[href*='x.com']");

    public void clickLinkedIn() { driver.findElement(linkedinIcon).click(); }
    public void clickFacebook() { driver.findElement(facebookIcon).click(); }
    public void clickTwitter() { driver.findElement(twitterIcon).click(); }
}
