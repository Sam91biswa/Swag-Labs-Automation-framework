package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BaseClass;

public class LoginPage extends BaseClass 
{
	@FindBy(xpath = "//div[@class='login_logo']")
	private WebElement logo;
	
	@FindBy(id="user-name")
	private WebElement UN;
	
	@FindBy(id="password")
	private WebElement PWD;
	
	@FindBy(id="login-button")
	private WebElement loginbtn;
	
	@FindBy(xpath="//div[@class='error-message-container error']")
	private WebElement errorMsg;

	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public boolean verifyLOGO() 
	{
		boolean lg = logo.isDisplayed();
		return lg;
	}

	public String verifyURL() 
	{
		String url = driver.getCurrentUrl();
		return url;
	}

	public String verifyPageTitle() 
	{
		return driver.getTitle();
	}
	
	public void credentials(String username, String password)
	{
		UN.sendKeys(username);
		PWD.sendKeys(password);
		loginbtn.click(); 
	}
	
	public String getErrorMsg()
	{
		return errorMsg.getText();
	}
}
