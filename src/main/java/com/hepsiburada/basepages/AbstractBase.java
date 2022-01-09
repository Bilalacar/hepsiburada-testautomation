package com.hepsiburada.basepages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hepsiburada.utility.*;
import com.relevantcodes.extentreports.LogStatus;

public class AbstractBase {

	protected WebDriverWait wait, waitZero, waitLoader;

	public AbstractBase() {

	}

	public void Control(boolean statement, String onTrue, String onFalse) {

		if (statement == true) {
			AbstractBase.LogPASS(onTrue);
		} else {
			AbstractBase.LogFAIL(onFalse);
			Assert.assertTrue(false);
		}
	}

	public void ControlWarning(boolean statement, String onTrue, String onFalse) {

		if (statement == true)
			AbstractBase.LogPASS(onTrue);
		else
			AbstractBase.LogWARNING(onFalse);
	}

	public static void Wait(int millisecond) {

		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void LogPASS(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.PASS, massege);
		log.info(massege);
	}

	public static void LogFATAL(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.FATAL, massege);
	}

	public static void LogFAIL(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.FAIL, massege);
		massege = massege.replace("<br>", "\n");
		log.error(massege);
	}

	public static void LogERROR(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.ERROR, massege);
	}

	public static void LogINFO(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.INFO, massege);
	}

	public static void LogWARNING(String massege) {

		ExtentTestManager.getTest()
		                 .log(LogStatus.WARNING, massege);
	}
}
