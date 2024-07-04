package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.NoSuchSessionException;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// Import the pages to automate it
import org.DATests.LoginPage;

public class DABaseTestClass {

    private static final Logger logger = LogManager.getLogger(DABaseTestClass.class);
    private AndroidDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    public DABaseTestClass(AndroidDriver driver, ExtentReports extent, ExtentTest test) {
        this.driver = driver;
        this.extent = extent;
        this.test = test;
    }

    // New method to run tests with Order ID
    public void runTestsWithOrderID(String orderID) throws MalformedURLException {
        try {
            // Open the driver app
            switchToDriverApp();

            // Execute tests with the given Order ID
            testLogInPage(orderID);
            testLandingPage(orderID);

            // Mark the test as passed
            test.pass("Test passed successfully!");

        } catch (Exception e) {
            // Mark the test as failed
            test.fail("Test failed: " + e.getMessage());
        } finally {
            // Close the app and switch back to the previous app
            switchBackToPreviousApp();
        }
    }

    private void switchToDriverApp() {
        if (driver != null) {
            try {
                // Activate the driver app (DA)
                driver.activateApp("hk.gogovan.GoGoDriver.staging");
                Thread.sleep(10000);
                System.out.println("Switched to the driver app.");
            } catch (NoSuchSessionException e) {
                System.err.println("Session is terminated or not started: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error while switching apps: " + e.getMessage());
            }
        }
    }
    private void testLogInPage(String orderID) {
        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.testAutomateTheLoginPage(orderID);
    }

    private void testLandingPage(String orderID) {
        LandingPageTest landingPageTest = new LandingPageTest(driver);
        landingPageTest.testAutomateTheLandingPage(orderID);
    }

    private void switchBackToPreviousApp() {
        if (driver != null) {
            try {
                // Terminate the driver app
                driver.terminateApp("hk.gogovan.GoGoDriver.staging");

                // Activate the previous app
                driver.activateApp("hk.gogovan.GoGoVanClient2.staging");

                // Sleep for 5 seconds
                Thread.sleep(5000);

                System.out.println("Switched back to the previous app.");
            } catch (InterruptedException e) {
                // Handle the InterruptedException
                System.err.println("Thread was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        }
    }
}