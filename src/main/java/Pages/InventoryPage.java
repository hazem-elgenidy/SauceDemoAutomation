package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    private By cartIcon = By.id("shopping_cart_container");
    private By backButton = By.id("back-to-products");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✔ Open cart
    public void openCart() {
        driver.findElement(cartIcon).click();
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

    // ✔ Go back from cart to inventory
    public void goBack() {
        driver.findElement(backButton).click();
    }

    // ✔ Social icons (inventory page also has them)
    private By linkedinIcon = By.cssSelector("a[href*='linkedin']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    private By twitterIcon = By.cssSelector("a[href*='twitter']");

    public void clickLinkedIn() { driver.findElement(linkedinIcon).click(); }
    public void clickFacebook() { driver.findElement(facebookIcon).click(); }
    public void clickTwitter() { driver.findElement(twitterIcon).click(); }
}
