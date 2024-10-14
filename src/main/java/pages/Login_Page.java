package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Page {
	WebDriver driver;

	public Login_Page(WebDriver driver) {
		this.driver = driver;
	}

	By user = By.id("email1");
	By pass = By.name("password1");
	By login = By.xpath("//button[text()='Sign in']");

	public void loginToApp(String username, String password) {
		driver.findElement(user).sendKeys(username);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(login).click();
		
		

	}
}
