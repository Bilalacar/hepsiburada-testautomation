package com.hepsiburada.controller;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.hepsiburada.base.SeleniumAbstractTest;
import com.hepsiburada.pages.web.PageHepsiburadaAnaSayfa;
import com.hepsiburada.utility.ExtentTestManager;

public class WebController extends SeleniumAbstractTest {
	protected ThreadLocal<PageHepsiburadaAnaSayfa> tl = new ThreadLocal<PageHepsiburadaAnaSayfa>();

	@BeforeMethod
	public void Before(Method method) {

		WebDriver driver;
		ExtentTestManager.startTest(method.getName());
		driver = super.setUpBrowser();
		tl.set(new PageHepsiburadaAnaSayfa(driver));
	}

	protected PageHepsiburadaAnaSayfa startTest() {

		return tl.get();
	}
}
