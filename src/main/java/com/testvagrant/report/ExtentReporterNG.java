package com.testvagrant.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.testvagrant.Base.BaseClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporterNG extends BaseClass {

    /**
     * Generate Extent Report
     * @name extentReportGenerator
     * @return ExtentReports
     */
    public static ExtentReports extentReportGenerator(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String resultPath = System.getProperty("user.dir")+"\\reports\\"+dtf.format(now)+"\\report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(resultPath);
        reporter.config().setReportName("Test Vargant Report");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Raman");
        extent.setSystemInfo("Environment","Local");
        return extent;
    }
}
