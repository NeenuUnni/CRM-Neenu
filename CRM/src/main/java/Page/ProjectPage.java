package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ElementUtility;
import Utility.WaitUtility;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage {
	WebDriver driver;

	@FindBy(xpath="//span[text()='Projects']" )
	WebElement projects;
	@FindBy(xpath="//span[text()='All Projects']")
	WebElement allprojects;
	@FindBy(xpath="//a[@title='Add project']")
	WebElement addproject;
	@FindBy(xpath="//input[@name='title']")
	WebElement titleField;
	@FindBy(xpath="//textarea[@name='description']")
	WebElement descriptionField;
	@FindBy(xpath="//button[@type='submit']")
	WebElement savebutton;

	@FindBy(xpath="//input[@type='search']")
	WebElement searchField;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr[1]//td[9]//a[1]")
	WebElement editproject;
	@FindBy(xpath="//input[@name='title']")
	WebElement edittitle;
	@FindBy(xpath="//button[@type='submit']")
	WebElement editsave;

	@FindBy(xpath="//input[@type='search']")
	WebElement searchdelete;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr[1]//td[9]//a[2]")
	WebElement delete;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement confirmdelete;

	@FindBy(xpath="//button[text()=' Close']")
	WebElement closebutton;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr//td[2]")
	WebElement projecttitle;
	@FindBy(xpath="//div[text()='The record has been deleted.']")
	WebElement recorddelete;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr//td[1]")
	WebElement deletemsg;

	@FindBy(xpath="//input[@id='s2id_autogen6_search']")
	WebElement clientlabel;

	@FindBy(xpath="//input[@id='start_date']")
	WebElement startdatefield;
	@FindBy(xpath="//input[@id='deadline']")
	WebElement deadlinefield;

	WaitUtility waitUtil;
	ElementUtility elementutil;

	public ProjectPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		waitUtil=new WaitUtility(driver);
		elementutil=new ElementUtility(driver);
	}

	public String addProject(String title, String description, String billdate, String duedate) {
		waitUtil.waitForClick(addproject);
		addproject.click();
		titleField.sendKeys(title);
		descriptionField.sendKeys(description);
		elementutil.dateSelect(startdatefield, billdate);
		elementutil.dateSelect(deadlinefield, duedate);
		savebutton.click();
		waitUtil.waitForClick(closebutton);
		closebutton.click();
		clickproject();
		searchField.sendKeys(title);
		String actual=projecttitle.getText();
		System.out.println(actual);
		return actual;
	}

	public String editProject(String editvalue) {
		waitUtil.waitForClick(searchField);
		editproject.click();
		edittitle.clear();
		edittitle.sendKeys(editvalue);
		editsave.click();
		waitUtil.waitForClick(closebutton);
		closebutton.click();
		clickproject();
		searchField.sendKeys(editvalue);
		String actual=projecttitle.getText();
		System.out.println(actual);
		return actual;
	}

	public String deleteProject(String search) {
		waitUtil.waitForClick(searchdelete);
		searchdelete.sendKeys(search);
		delete.click();
		confirmdelete.click();
		waitUtil.waitForClick(recorddelete);
		recorddelete.click();
		clickproject();
		String actual=deletemsg.getText();
		System.out.println(actual);
		return actual;
	}

	public void clickproject() {
		projects.click();
		allprojects.click();
	}

}
