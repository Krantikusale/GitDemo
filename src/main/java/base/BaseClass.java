package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

public class BaseClass {
	public WebDriver driver;

	@BeforeClass
	public void setupBrowesr() {
		System.out.println("LOG:INFO - Setting up browser");
		//Config-Does not suite for cross browser
		driver = BrowserFactory.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));
		System.out.println("LOG:INFO - App. is up and running");

	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("Closing the browser and app.");
	}
}