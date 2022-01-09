package com.hepsiburada.base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


public class ServicesAbstractTest extends AbstractTest {

	@AfterMethod(groups = { "main" })
	public void getResult(ITestResult result, Method method) {

		super.getResult(result, method);
	}
}
