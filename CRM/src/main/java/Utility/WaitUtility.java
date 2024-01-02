package Utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;

	public WaitUtility(WebDriver driver)
	{
		this.driver=driver;
	}

	public void waitForClick(WebElement waitVar)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.elementToBeClickable(waitVar));
	}
	//expected conditions	
	public void visibilityWait(WebElement waitVis)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.visibilityOf(waitVis));
	}

	public void visibilityWait(List<WebElement> waitVis)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.visibilityOfAllElements(waitVis));
	}

	public void visibilityWait(By waitVis)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.visibilityOfElementLocated(waitVis));
	}
	public void selectWait(WebElement waitSel)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.elementToBeSelected(waitSel));
	}


	public void frameWait(WebElement waitFrame)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(250));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(waitFrame));

	}
}
