package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public String doLogin(String email,String password) 
	{
		WebElement emailField=driver.findElement(By.xpath("//input[@name='email']"));
		emailField.sendKeys(email);
		WebElement passwordField=driver.findElement(By.xpath("//input[@name='password']"));
		passwordField.sendKeys(password);
		WebElement signIn=driver.findElement(By.xpath("//button[text()='Sign in']"));
		signIn.click();
		WebElement homepage=driver.findElement(By.xpath("//span[text()='Dashboard']"));
		String actualhomepage=homepage.getText();
		return actualhomepage;
	}
}