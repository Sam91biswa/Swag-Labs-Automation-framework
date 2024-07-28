package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import library.BaseClass;
import library.UtilityClass;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class LoginPageTest extends BaseClass {
	 private static int TCID = 0;
	    LoginPage login;
	    ProductPage productPage;
	    CheckoutPage checkoutPage;

	@BeforeMethod
	public void setup() {
		login = new LoginPage(driver);
		productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			UtilityClass.captureSS(TCID);
		}
		driver.quit();
	}

	@Test
	public void verifyURL() throws IOException 
	{ TCID=1;
		String actURL = login.verifyURL();
		String expURL = UtilityClass.readPFData("URL");
		Assert.assertEquals(actURL, expURL);
	}

	@Test
	public void verifyLogo() 
	{ TCID=2;
		boolean actLogo = login.verifyLOGO();
		boolean expLogo = true;
		Assert.assertEquals(actLogo, expLogo);
	}

	@Test
	public void verifyPageTitle() 
	{ TCID=3;
		login.verifyPageTitle();
	}

	@DataProvider(name="credentials")
	public Object[] [] getData()
	{
		return new Object[] []
		{  {"valid", "standard_user", "secret_sauce"},
			{"valid", "problem_user", "secret_sauce"},
			{"valid", "performance_glitch_user", "secret_sauce"},
			{"valid", "error_user", "secret_sauce"},
			{"valid", "visual_user", "secret_sauce"},
			{"invalid", "abcd123", "abcd@123"},
			{"invalidUN", "abcdsfg", "secret sauce"},
			{"invalidPWD", "standard_user", "abcd@123"},
			{"Blank", "", ""}
			
		};
	}
	
	@Test(dataProvider="credentials")
	public void verifyLoginFunctionality(String scenerio, String username, String password) throws IOException
	{ TCID=4;
		login.credentials(username, password);
		if(scenerio.equals("valid"))
		{
			String actresult = login.verifyURL();
			String expresult = UtilityClass.readPFData("productPageURL");
			Assert.assertEquals(actresult, expresult);
		}
		else if(scenerio.equals("invalid"))
		{
			String actresult = login.getErrorMsg();
			String expresult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actresult, expresult);
		}
		else if(scenerio.equals("invalidUN"))
		{
			String actresult = login.getErrorMsg();
			String expresult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actresult, expresult);
		}
		else if(scenerio.equals("invalidPWD"))
		{
			String actresult = login.getErrorMsg();
			String expresult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actresult, expresult);
		}
		else if(scenerio.equals("blank"))
		{
			String actresult = login.getErrorMsg();
			String expresult = "Epic sadface: Username is required";
			Assert.assertEquals(actresult, expresult);
		}
	}
	
	@Test
    public void testProductAdditionToCart() throws IOException 
	{ TCID= 5;
        login.credentials("standard_user", "secret_sauce");
        productPage.addProductToCart();
        productPage.goToCart();
        // Add assertions to verify product is in the cart
        String cartItem = productPage.getCartItem();  // This assumes you have a method to get the item in the cart
        Assert.assertEquals(cartItem, "Sauce Labs Backpack");
    }

    @Test
    public void testCheckoutProcess() throws IOException 
    { TCID=6;
        login.credentials("standard_user", "secret_sauce");
        productPage.addProductToCart();
        productPage.goToCart();
        checkoutPage.checkout("John", "Doe", "12345");
        // Add assertions to verify successful checkout
        String checkoutSuccessMessage = checkoutPage.getSuccessMessage();  // This assumes you have a method to get the success message
        Assert.assertEquals(checkoutSuccessMessage, "Thank you for your order!");
    }
}
