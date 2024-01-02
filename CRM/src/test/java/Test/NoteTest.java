package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Page.LoginPage;
import Page.NotePage;
import Utility.FakerUtility;

public class NoteTest extends BaseTest

{
	@Test(priority=1,groups= {"smoke","regression"}, retryAnalyzer = generaltests.Retry.class)
	public void verifynote() {
		SoftAssert softassert=new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		NotePage np = new NotePage(driver);
		np.clickNote();
		String actual=np.addNote("CRM - New Note By Neenu", FakerUtility.emailID());
		String expected="CRM - New Note By Neenu";
		softassert.assertEquals(actual, expected);
		softassert.assertAll();
	}

	@Test(priority=2)
	public void SearchNote()
	{
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com","12345678");
		NotePage np=new NotePage(driver);
		np.clickNote();
		String actual=np.search("CRM - New Note By Neenu");
		String expected="CRM - New Note By Neenu";
		Assert.assertEquals(actual, expected);
	}  

	@Test(priority=3, groups= {"smoke"})
	public void verifyeditNote() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		NotePage np = new NotePage(driver);
		np.clickNote();
		np.search("CRM - New Note By Neenu");
		String actualmsg=np.editNote("Edited-CRM - New Note by Neenu");
		String expected="Edited-CRM - New Note by Neenu";
		Assert.assertEquals(actualmsg, expected);
	}

	@Test(priority=4,groups= {"smoke","regression"})
	public void verifydeleteNote() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		NotePage np = new NotePage(driver);
		np.clickNote();
		String actual=np.deleteNote("Edited-CRM - New Note by Neenu");
		String expected="No record found.";
		Assert.assertEquals(actual, expected);
	}
}
