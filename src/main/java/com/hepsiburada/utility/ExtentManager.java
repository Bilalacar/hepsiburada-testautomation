package com.hepsiburada.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static ExtentReports extent;
	public static final String REPORT_FILE_PATH = "reports/ExtentReport-" + getSysDateCustom() + ".html";

	private ExtentManager() {

		throw new IllegalStateException("Utility class");
	}

	public static synchronized ExtentReports getReporter() {

		log.info("Report Path: " + REPORT_FILE_PATH);

		if (extent == null) {
			extent = new ExtentReports(REPORT_FILE_PATH, true);

			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(ClassLoader.getSystemResource("extent-config.xml"));
		}

		return extent;
	}

	public static String getSysDateCustom() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH;mm;ss");
		LocalDateTime now = LocalDateTime.now();
		String sysDate = dtf.format(now);

		return sysDate;
	}
}