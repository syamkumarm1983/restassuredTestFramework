package com.syam.test.utils.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyTestLisiner implements ITestListener {

    private static ExtentReports extent  = MyExtentReporter.getInstance();
    public void onTestStart(ITestResult result) {
       ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        MyExtentReporter.extentTest.set(test);

    }

    public void onTestSuccess(ITestResult result) {
        MyExtentReporter.extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        MyExtentReporter.extentTest.get().log(Status.FAIL, "Test Failed");
        MyExtentReporter.extentTest.get().fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        MyExtentReporter.extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        extent.flush(); // Write the report to the file
    }
}
