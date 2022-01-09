package com.hepsiburada.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hepsiburada.basepages.SeleniumAbstractPage;

public class WebCommons extends SeleniumAbstractPage {

	public WebCommons(WebDriver driver) {

		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public String ilkUrun = "";
	public String ikinciUrun = "";
	
	public void control(WebElement elem, String onTrue, String onFalse) {

		try {
			if (getTextOfElement(elem).contains(onTrue)) {
				LogPASS(onTrue);
			} else {
				LogFAIL(onFalse);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			LogFAIL(onFalse);
			Assert.assertTrue(false);
		}
	}
}
