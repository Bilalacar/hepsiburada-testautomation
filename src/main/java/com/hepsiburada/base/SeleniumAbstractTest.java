package com.hepsiburada.base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.hepsiburada.data.GetData;
import com.hepsiburada.utility.*;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumAbstractTest extends AbstractTest {

	protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	@BeforeSuite
	public void startSuite(ITestContext ctx) {

		super.startSuite(ctx);
	}

	@BeforeMethod
	public void setUp(Method method) {

		super.setUp(method);
	}

	public WebDriver setUpBrowser() {

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-infobars");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		driver.set(new ChromeDriver(options));

		driver.get()
		      .manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));
		driver.get()
		      .manage()
		      .timeouts()
		      .pageLoadTimeout(Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));

		return driver.get();
	}

	@AfterMethod
	public void getResult(ITestResult result, Method method) {

		if (result.getStatus() == ITestResult.FAILURE)
			ExtentTestManager.getTest()
			                 .log(LogStatus.FAIL, "Snapshot below: "
			                                      + ExtentTestManager.getTest()
			                                                         .addBase64ScreenShot(GetScreenShot.capture(driver.get())));

		driver.get()
		      .close();
		driver.get()
		      .quit();
		log.info("Browser closed.");

		super.getResult(result, method);
	}
}