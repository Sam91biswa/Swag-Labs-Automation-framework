package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class UtilityClass extends BaseClass 
{
	public static String readPFData(String key) throws IOException 
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//TestData//Config.properties");
		Properties prop = new Properties();
		prop.load(file);
		String value = prop.getProperty(key);
		return value;
	}

	public static void captureSS(int TCID) throws IOException 
	{
		File dest = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File src = new File(
				System.getProperty("user.dir") + "//FailedTestCaseScreenshot//FailedTestCase_" + TCID + ".jpg");
		FileHandler.copy(dest, src);
	}
}
