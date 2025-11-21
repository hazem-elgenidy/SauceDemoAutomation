package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private WebDriver driver;

    // Locators
    private By cartItemNames = By.className("inventory_item_name");

    private By linkedinIcon = By.cssSelector("a[href*='linkedin']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    private By twitterIcon = By.cssSelector("a[href*='twitter']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✔ Get number of items
    public int getItemsCount() {
        return driver.findElements(cartItemNames).size();
    }

    // ✔ Get item names list
    public List<String> getCartItemNames() {
        return driver.findElements(cartItemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // ✔ Click social icons
    public void clickLinkedIn() {
        driver.findElement(linkedinIcon).click();
    }

    public void clickFacebook() {
        driver.findElement(facebookIcon).click();
    }

    public void clickTwitter() {
        driver.findElement(twitterIcon).click();
    }

    // ✔ Remove item by name
    public void removeItemByName(String productName) {
        driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button")
        ).click();
    }
}
