package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page.LoginPage;
import Page.ProjectPage;

public class ProjectTest extends BaseTest{
	@Test(priority=1,groups= {"smoke"})
	public void verifyproject() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectPage pg = new ProjectPage(driver);
		pg.clickproject();
		String actual=pg.addProject("CRM-New Project by Neenu","CRM new project added by Neenu as informed","2023-12-13","2023-12-28");
		String expected="CRM-New Project by Neenu";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority=2,groups= {"smoke","regression"})
	public void verifyeditproject() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectPage pg = new ProjectPage(driver);
		pg.clickproject();
		String actual=pg.editProject("Edited-CRM-New Project By Neenu");  
		String expected="Edited-CRM-New Project By Neenu";
		Assert.assertEquals(actual, expected);

	}
	@Test(priority=3, groups= {"smoke"})
	public void verifydeleteproject() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectPage pg = new ProjectPage(driver);
		pg.clickproject();
		String actual=pg.deleteProject("Edited-CRM-New Project by Neenu");
		String expected = "No record found.";
		Assert.assertEquals(actual, expected);	  
	}
}
