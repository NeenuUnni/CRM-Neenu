package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ElementUtility;
import Utility.WaitUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class NotePage {
	WebDriver driver;
	//@FindBy(locator="")
	//WebElement elementname;

	@FindBy(xpath="//span[text()='Notes']")
	WebElement notes;
	@FindBy(xpath="//a[text()=' Add note']")
	WebElement addnote;
	@FindBy(xpath="//input[@name='title']")
	WebElement addtitleField;
	@FindBy(xpath="//textarea[@name='description']")
	WebElement descriptionField;
	@FindBy(xpath="//button[@type='submit']")
	WebElement save;

	@FindBy(xpath="//input[@type='search']")
	WebElement notesearch;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[4]//a[1]")
	WebElement editnote;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[4]//a[2]")
	WebElement delete;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement confirmdelete;

	@FindBy(xpath="//button[text()=' Close']")
	WebElement closebutton;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr//td[2]")
	WebElement titlecoloumn;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr//td[1]")
	WebElement deletemsg;
	@FindBy(xpath="//button[@class='close']")
	WebElement recorddelete;

	@FindBy(xpath="//div[@id='s2id_note_labels']")
	WebElement labelclick;
	@FindBy(xpath="//div[@id='s2id_note_labels']//descendant::input") //
	WebElement labelField;
	@FindBy(xpath="//div[@id='select2-drop']//ul//li[1]//span")
	WebElement selectclick;
	WaitUtility waitUtil;
	ElementUtility elementutil; // object creation

	
	public NotePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // initializing webelements located using @FindBy		
		waitUtil=new WaitUtility(driver);
		elementutil=new ElementUtility(driver);  // object initialization
	}

	public String addNote(String title,String description)
	{
		waitUtil.waitForClick(addnote);
		addnote.click();
		addtitleField.sendKeys(title);
		descriptionField.sendKeys(description);
		save.click();	  
		waitUtil.waitForClick(closebutton);
		closebutton.click();
		clickNote();
		String actual=search(title);
		System.out.println(actual);
		return actual;
	}	  

	public String editNote(String editvalue)
	{
		editnote.click();
		addtitleField.clear();
		addtitleField.sendKeys(editvalue);
		save.click();	
		waitUtil.waitForClick(closebutton);
		closebutton.click();
		clickNote();
		String actual=search(editvalue);
		System.out.println(actual);
		return actual;
	}

	public String deleteNote(String searchvalue) {
		notesearch.sendKeys(searchvalue);
		delete.click();
		confirmdelete.click();
		waitUtil.waitForClick(recorddelete);
		recorddelete.click();
		clickNote();
		String actual=deletemsg.getText();
		System.out.println(actual);
		return actual;
	}
	public void clickNote() { // this method is created to click "notes", everytime when we need to perform action on notes
		notes.click();
	}

	public String search(String searchvalue) 
	{
		waitUtil.visibilityWait(notesearch);
		notesearch.sendKeys(searchvalue);
		By locator=By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]");
		waitUtil.visibilityWait(locator);
		List<WebElement> notetable=driver.findElements(By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]"));
		waitUtil.visibilityWait(notetable);
		int row=elementutil.getTableDataRowCount(notetable, searchvalue);
		String message="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='note-table']//tbody//tr["+row+"]//td[2]"));
			message=tableRow.getText();
			System.out.println(message);

		}
		return message;
	}
}
