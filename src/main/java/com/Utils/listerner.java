package com.Utils;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

public class listerner implements ITestListener{

    TestBase b=new TestBase();
    // This method is invoked before any test method gets executed.
    @Override
    public void onTestStart(ITestResult result) {
    }

    // This method is invoked when a test method successfully completes.
    @Override
    public void onTestSuccess(ITestResult result) {
    }

    // This method is invoked when a test method fails.
    @Override
    public void onTestFailure(ITestResult TestName) {
        try {
            b.takeScreenShot(TestName.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Here, you can add code to take a screenshot or log the error details.
    }

    // This method is invoked when a test method is skipped.
    @Override
    public void onTestSkipped(ITestResult result) {
    }

    // This method is invoked when a test method fails but within the success percentage.
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    // This method is invoked before starting all the test methods in a suite.
    @Override
    public void onStart(ITestContext context) {
    }

    // This method is invoked after all the test methods in a suite have run.
    @Override
    public void onFinish(ITestContext context) {
    }
}

