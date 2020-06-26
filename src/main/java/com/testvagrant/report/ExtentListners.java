package com.testvagrant.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.testvagrant.util.Utils.captureAsBase64;

public class ExtentListners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.extentReportGenerator();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test case failed is : "+result.getName());
        test.log(Status.FAIL, "Test case failed info : "+result.getThrowable());
        if(! result.getName().toLowerCase().contains("api")){
                test.addScreenCaptureFromBase64String(captureAsBase64());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test case passed is : "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test case  : "+result.getName()+" is skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
