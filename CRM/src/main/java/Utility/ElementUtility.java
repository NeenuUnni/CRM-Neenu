package Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import constants.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class ElementUtility 
{
	WebDriver driver;

	public ElementUtility(WebDriver driver)
	{
		this.driver=driver;
	}

	public static String getPropertyValue(String key) 
	{

		File src=new File(Constants.propertyConfig_File); // class to read to file
		Properties pro=new Properties(); // Properties to read .properties file
		try {
			FileInputStream fis = new FileInputStream (src); // class to read to file

			pro.load(fis); // to load the properties
		} catch (Exception e) { // if an exception occur during file reading or property loading stackrace is printed

			e.printStackTrace();
		}

		String value=pro.get(key).toString();
		return value;
	}

	public void scroll(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void dropdownSelectByIndex(WebElement index, int a)
	{
		Select indexdown = new Select(index);
		indexdown.selectByIndex(a);
	}

	public void dropdownSelectByValue(WebElement value, String b)
	{
		Select indexdown = new Select(value);
		indexdown.selectByValue(b);
	}

	public void dropdownSelectByVisibleText(WebElement text, String c)
	{
		Select indexdown = new Select(text);
		indexdown.selectByVisibleText(c);
	}

	public void radiobutton(List<WebElement> radio, int ab)
	{
		radio.get(ab).click();
	}

	public void checkbox(List<WebElement> check, int ac)
	{
		check.get(ac).click();
	}
	public void dateSelect(WebElement element,String dateValue) 
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+dateValue+"');", element);
	}

	public int getTableDataRowCount(List<WebElement> tableRowData ,String expectedValue)
	{
		int counter=0;
		for(int i=0;i<tableRowData.size();i++)
		{
			String value=tableRowData.get(i).getText();
			if(expectedValue.equalsIgnoreCase(value))
			{
				counter=i+1;
				break;
			}
		}
		return counter;
	}

	public void clearAndSend(WebElement element,String text)
	{
		element.clear();
		element.sendKeys(text);
	}

}
