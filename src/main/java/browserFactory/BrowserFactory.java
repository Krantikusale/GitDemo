package browserFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class BrowserFactory {
	static WebDriver driver;

	public static WebDriver getBrowserInstance() {
		return driver;
	} 

	public static WebDriver startBrowser(String browserName, String applicationURL) {
		//WebDriver driver = null;
		if (browserName.contains("Chrome") || browserName.contains("Google Chrome ")) {
			driver = new ChromeDriver();
		} else if (browserName.contains("Firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.contains("Edge")) {
			driver = new EdgeDriver();

		} else {
			System.out.println("sorry " + browserName + " not supported, Running test in default browser");

			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
		driver.get(applicationURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	public static WebElement highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red; ')", element);
		waitForSeconds(1);
		js.executeScript("arguments[0].setAttribute('style', 'border:2px solid white; ')", element);
		return element;
	}

	public static WebElement highlightElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red; ')", element);
		waitForSeconds(1);
		js.executeScript("arguments[0].setAttribute('style', 'border:2px solid white; ')", element);
		return element;
	}

	public static Alert waitForAlert(WebDriver driver) {
		Alert alt = null;

		for (int a = 0; a <= 15; a++) {
			try {
				alt = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				System.out.println("No alert found = Waiting for alert");
				waitForSeconds(1);
			}
		}
		return alt;

	}

	public static Alert waitForAlert(WebDriver driver, int time) {
		Alert alt = null;

		for (int a = 0; a <= time; a++) {
			try {
				alt = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				System.out.println("No alert found - Waiting for alert");
				waitForSeconds(1);
			}
		}
		return alt;

	}

	public static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {

		}
	}

	public static void captureScreenshot(WebDriver driver) {
		try {
			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("./screenshots/Screenshot_" + getCurrentTime() + ".png"));
		} catch (IOException e) {
			System.out.println("Something went wrong " + e.getMessage());
		}
	}

	public static void captureScreenshotOfWebElement(WebDriver driver, WebElement element) {
		try {
			// Capture screenshot of the element
			File src = element.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("./elementScreenshot/Screenshot_" + getCurrentTime() + ".png"));
		} catch (IOException e) {
			System.out.println("Something went wrong " + e.getMessage());
		}

	}

	public static String getCurrentTime() {
		String date = new SimpleDateFormat("HHmmss_ddMMyyyy").format(new Date());
		return date;
	}
}