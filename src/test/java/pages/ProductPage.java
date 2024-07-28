package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCartBtn;

	@FindBy(id = "shopping_cart_container")
	private WebElement cartIcon;

	@FindBy(className = "inventory_item_name")
	private WebElement cartItemName;

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void addProductToCart() {
		addToCartBtn.click();
	}

	public void goToCart() {
		cartIcon.click();
	}

	public String getCartItem() {
		return cartItemName.getText();
	}
}