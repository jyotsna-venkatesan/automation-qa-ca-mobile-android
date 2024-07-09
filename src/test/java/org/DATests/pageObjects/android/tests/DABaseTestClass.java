package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchSessionException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// Import the pages to automate it


public class DABaseTestClass {

    private static final Logger logger = LogManager.getLogger(DABaseTestClass.class);
    private AndroidDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    private ConfigLoader configLoader;

    public DABaseTestClass(AndroidDriver driver, ExtentReports extent, ExtentTest test) {
        this.driver = driver;
        this.extent = extent;
        this.test = test;
        configLoader = new ConfigLoader();
    }

    // New method to run tests with Order ID
    public void runTestsWithOrderID(String orderID, String pickupCode) throws MalformedURLException {
        try {
            // Open the driver app
            switchToDriverApp();

            // Reload properties to ensure the latest value is read
            configLoader.reload();
            String orderType = configLoader.getProperty("ORDER_TYPE");
            System.out.println("The type of order is " + orderType);

            if (orderType.equalsIgnoreCase("T")) {
                testLandingPage(orderID);
            }
            else if (orderType.equalsIgnoreCase("D")) {
                testLandingDeliveryPage(orderID, pickupCode);
                System.out.println("landing delivery page and back to base test");
            }

            // Mark the test as passed
            test.pass("Test passed successfully!");

        } catch (Exception e) {
            // Mark the test as failed
            test.fail("Test failed: " + e.getMessage());
        } finally {
            // Close the app and switch back to the previous app
            System.out.println("in the finally block");
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

    private void testLandingDeliveryPage(String orderID, String pickupCode) {
        try {
            System.out.println("Inside the testLandingDeliveryPage function");
            LandingDeliveryPageTest landingDeliveryPageTest = new LandingDeliveryPageTest(driver);
            System.out.println("Created LandingDeliveryPageTest inside the testLandingDeliveryPage function");
            landingDeliveryPageTest.testAutomateTheLandingDeliveryPage(orderID, pickupCode);
            System.out.println("Back from the testAutomateTheLandingDeliveryPage method inside the testLandingDeliveryPage function");
        } catch (Exception e) {
            System.out.println("Exception occurred in testLandingDeliveryPage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void testLogOutPage(String orderID) {
        LogOutPageTest logoutPageTest = new LogOutPageTest(driver);
        logoutPageTest.testAutomateTheLogOutPage(orderID);
    }

    private void switchBackToPreviousApp() {
        if (driver != null) {
            try {
                System.out.println("terminating the driver app");
                // Terminate the driver app
                driver.terminateApp("hk.gogovan.GoGoDriver.staging");

                // Activate the previous app
                driver.activateApp("hk.gogovan.GoGoVanClient2.staging");

                System.out.println("Switched back to the previous app.");

                // Sleep for 5 seconds so that we can check
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Handle the InterruptedException
                System.err.println("Thread was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        }
    }
}