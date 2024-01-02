package Test;

import org.testng.annotations.Test;

import Utility.ElementUtility;
import constants.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest {

	WebDriver driver;

	@Parameters({"browser"})	
	@BeforeMethod(alwaysRun=true) // whatever be the condition, always beforeMethod runs
	public void beforeMethod(@Optional ("chrome")String browser) { // if class is run or no parameter is mentioned in xml file, chrome browser need to be run by default.

		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Please enter correct browser");
		}

		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
		//		 driver.get("https://qalegend.com/crm/index.php");
		driver.get(ElementUtility.getPropertyValue("baseurl"));
	}

	@AfterMethod

	public void takeScreenShotOnFailure(ITestResult iTestResult) throws IOException {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			takeScreenShotOnFailure(iTestResult.getName());
		}
		//			driver.quit();
	}

	public String takeScreenShotOnFailure(String name) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//takes screenshot
		String destination =Constants.screenShot_path + name + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}

