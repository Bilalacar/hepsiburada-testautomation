package com.hepsiburada.controller;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;

import com.hepsiburada.base.ServicesAbstractTest;
import com.hepsiburada.pages.services.ServicesActions;
import com.hepsiburada.utility.ExtentTestManager;

public class ServiceController extends ServicesAbstractTest {

	protected ThreadLocal<ServicesActions> tl = new ThreadLocal<ServicesActions>();

	@BeforeMethod
	public void Before(Method method, Object[] testData) {

		ExtentTestManager.startTest(method.getName());

		tl.set(new ServicesActions());
	}

	protected ServicesActions startTest() {

		return tl.get();
	}
}
