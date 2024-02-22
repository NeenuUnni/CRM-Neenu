package Test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Page.InvoicePage;
import Page.LoginPage;
import Utility.ExcelRead;
import constants.Constants;

public class InvoiceTest extends BaseTest
{
	String generatedinvoice;
	@Test(priority=1)
	public void verifyinvoice() throws InvalidFormatException, IOException 
	{
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		InvoicePage ip=new InvoicePage(driver);
		ip.clickInvoice();
		String value=ExcelRead.getDataFromExcel(Constants.excel_test,"Sheet2", 1, 0);
		System.out.println(value);
		generatedinvoice=ip.addInvoice("2023-02-22", "2024-02-29", value,"Neenu");
		ip.clickInvoice();
		String actualinvoice=ip.search(generatedinvoice);
		Assert.assertEquals(actualinvoice, generatedinvoice);
	}
	//@Test
	public void verifyeditinvoice()
	{
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		InvoicePage ip=new InvoicePage(driver);
		ip.clickInvoice();
		ip.editInvoice("INVOICE #26", "2023-12-31");
	}

	@Test(priority=2)
	public void verifydeleteinvoice()
	{
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		InvoicePage ip=new InvoicePage(driver);
		ip.clickInvoice();
		String actual=ip.deleteInvoice(generatedinvoice);
		String expected="No record found.";
		Assert.assertEquals(actual, expected);
	}
}
