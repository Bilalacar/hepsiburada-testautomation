package com.hepsiburada.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class GetScreenShot {

	public static String capture(WebDriver driver) {

		WebDriver augmentedDriver = new Augmenter().augment(driver);
		TakesScreenshot ts = (TakesScreenshot) augmentedDriver;

		return "data:image/png;base64, " + ts.getScreenshotAs(OutputType.BASE64);
	}
}