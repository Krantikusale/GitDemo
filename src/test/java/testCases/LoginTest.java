package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import base.BaseClass;
import browserFactory.BrowserFactory;
import dataProvider.CustomDataProvider;
import pages.Login_Page;

public class LoginTest extends BaseClass{
	@Test(dataProvider="loginDetails",dataProviderClass=CustomDataProvider.class)
	public void loginToApp(String uname,String pass ) {
		Login_Page login=new Login_Page(driver);
		login.loginToApp(uname,pass);
		
		

	}
}
