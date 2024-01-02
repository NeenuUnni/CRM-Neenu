package Test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Page.LoginPage;
import Utility.ExcelRead;
import constants.Constants;

public class LoginTest extends BaseTest
{
	@Test(priority=1,groups= {"smoke","regression"}, dataProvider="dp")
	public void verifylogin(String email,String password) 
	{
		LoginPage lp = new LoginPage(driver);
		String actual=lp.doLogin(email, password);
		String expected="Dashboard";
		Assert.assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] dp() throws InvalidFormatException, IOException
	{
		Object[][]data=ExcelRead.getDataFromExcel(Constants.excel_test,"Sheet1");
		return data;
	}
}

