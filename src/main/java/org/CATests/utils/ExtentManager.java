package org.CATests.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance("extentReports/extentReport.html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String filename) {
        ExtentSparkReporter spark = new ExtentSparkReporter(filename);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}