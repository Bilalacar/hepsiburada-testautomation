package com.hepsiburada.base;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.hepsiburada.utility.*;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

public class AbstractTest {

	public static String reportFilePath;

	@BeforeSuite
	public void startSuite(ITestContext ctx) {

		log.startTestCase(ctx.getCurrentXmlTest()
		                     .getSuite()
		                     .getName());
	}

	@BeforeMethod
	public void setUp(Method method) {

		log.startTestCase(method.getName());
		ExtentManager.getReporter();
		reportFilePath = ExtentManager.REPORT_FILE_PATH;
	}

	@AfterMethod
	public void getResult(ITestResult result, Method method) {

		String testName = method.getName();
		if (result.getStatus() == ITestResult.SKIP) {
			List<Log> logs = ExtentTestManager.getTest()
			                                  .getTest()
			                                  .getLogList();
			for (Log log : logs)
				log.setLogStatus(LogStatus.SKIP);
			ExtentTestManager.getTest()
			                 .log(LogStatus.WARNING, "Tekrarlandi!");
			ExtentTestManager.getTest()
			                 .getTest()
			                 .getLogList()
			                 .clear();
			ExtentTestManager.getTest()
			                 .getTest()
			                 .setStatus(LogStatus.INFO);
		}
		if (result.getStatus() == ITestResult.FAILURE)
			ExtentTestManager.getTest()
			                 .log(LogStatus.FAIL, result.getThrowable());
		else if (result.getStatus() == ITestResult.SUCCESS)
			ExtentTestManager.getTest()
			                 .log(LogStatus.PASS, "Test Passed");

		ExtentTestManager.endTest();
		ExtentManager.getReporter()
		             .flush();

		System.out.println(reportFilePath);
		log.endTestCase(testName);
	}

	@AfterSuite
	public void endSuite(ITestContext ctx) {

		System.out.println(reportFilePath);

		log.endTestCase(ctx.getCurrentXmlTest()
		                   .getSuite()
		                   .getName());
	}
}
