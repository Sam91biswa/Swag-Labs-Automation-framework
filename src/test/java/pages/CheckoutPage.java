package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BaseClass;

public class CheckoutPage extends BaseClass {
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "finish")
    private WebElement finishBtn;
    
    @FindBy(className = "complete-header")
    private WebElement successMessage;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void checkout(String fname, String lname, String zip) {
        checkoutBtn.click();
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        postalCode.sendKeys(zip);
        continueBtn.click();
        finishBtn.click();
    }
    
    public String getSuccessMessage() {
        return successMessage.getText();
    }
}
