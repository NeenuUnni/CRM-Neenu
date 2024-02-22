package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import Utility.ElementUtility;
import Utility.WaitUtility;

public class InvoicePage {
	WebDriver driver;

	@FindBy(xpath="//span[text()='Invoices']")
	WebElement Invoices;
	@FindBy(xpath="//a[@title='Add invoice']")
	WebElement addinvoice;
	@FindBy(xpath="//input[@id='invoice_bill_date']")
	WebElement billdate;
	@FindBy(xpath="//input[@id='invoice_due_date']")
	WebElement duedate;
	@FindBy(xpath="//div[@id='s2id_invoice_client_id']")
	WebElement clientclick;
	@FindBy(xpath="//input[@id='s2id_autogen12_search']")
	WebElement clientField;
	@FindBy(xpath="//div[@class='select2-result-label']//span")
	WebElement select;
	@FindBy(xpath="//textarea[@id='invoice_note']")
	WebElement notefield;
	@FindBy(xpath="//button[text()=' Save']")
	WebElement savefield;

	@FindBy(xpath="//button[text()=' Close']")
	WebElement closebutton;
	@FindBy(xpath="//input[@type='search']")
	WebElement searchfield;

	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr//td[1]")
	WebElement searchinvoice;
	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr//td[9]//button")
	WebElement actionbutton;
	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr//td[9]//ul//a[1]")
	WebElement editinvoice;
	@FindBy(xpath="//input[@id='invoice_due_date']")
	WebElement editduedate;

	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr//td[1]")
	WebElement searcheditinvoice;
	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr//td[9]//ul//li[2]//a[1]")
	WebElement deleteinvoice;
	@FindBy(xpath="//div[text()='The record has been deleted.']")
	WebElement recorddelete;
	@FindBy(xpath="//table[@id='monthly-invoice-table']//tbody//tr[1]")
	WebElement deletemsg;

	@FindBy(xpath="//div[@id='page-content']//h1")
	WebElement invoicenum;

	WaitUtility waitUtil;
	ElementUtility elementutil;

	public InvoicePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

		waitUtil=new WaitUtility(driver);
		elementutil=new ElementUtility(driver);
	}

	public String addInvoice(String bill,String due, String note)
	{
		waitUtil.waitForClick(addinvoice);
		addinvoice.click();
		elementutil.dateSelect(billdate,bill);
		elementutil.dateSelect(duedate, due);
		clientclick.click();
		waitUtil.waitForClick(clientField);
		select.click();
		elementutil.scroll(billdate);
		notefield.sendKeys(note);
		savefield.click();	
		String actual=invoicenum.getText();
		System.out.println(actual);
		return actual;
	}
	public void editInvoice(String searchinv,String editdue)
	{
		waitUtil.waitForClick(Invoices);
		clickInvoice();
		searchfield.sendKeys(searchinv);
		actionbutton.click();
		editinvoice.click();
		elementutil.dateSelect(editduedate,editdue);
		String editedduedate=editduedate.getAttribute("value");
		System.out.println(editedduedate);
		savefield.click();
	}

	public String deleteInvoice(String searcheditinv)
	{
		searchfield.sendKeys(searcheditinv);
		actionbutton.click();
		deleteinvoice.click();
		recorddelete.click();	
		clickInvoice();
		searchfield.sendKeys(searcheditinv);
		String recordnotfound=deletemsg.getText();
		System.out.println(recordnotfound);
		return recordnotfound;	
	}

	public void clickInvoice()
	{
		waitUtil.waitForClick(Invoices);
		Invoices.click();
	}

	public String search(String searchvalue)
	{
		waitUtil.visibilityWait(searchfield);
		searchfield.sendKeys(searchvalue);
		String invoice=searcheditinvoice.getText();
		System.out.println(invoice);
		return invoice;
	}
}
