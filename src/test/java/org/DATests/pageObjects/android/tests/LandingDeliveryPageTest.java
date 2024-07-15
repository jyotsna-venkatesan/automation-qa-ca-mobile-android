package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LandingDeliveryPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class LandingDeliveryPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public LandingDeliveryPageTest() {
        // Initialize driver here if needed
    }

    public LandingDeliveryPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Initialize the driver here if not already done
        if (this.driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            // Set desired capabilities here if needed
            this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
    }

    // Function to automate opening the login process
    @Test
    public void testAutomateTheLandingDeliveryPage(String orderID, String pickupCode) {
        try {
            LandingDeliveryPage landingdPage = new LandingDeliveryPage(driver);
            System.out.println("-- Landing Delivery Page --");

            // Open orders page
            boolean isSwitchPage = landingdPage.switchToDelivery();
            assertTrue(isSwitchPage, "Failed to switch to delivery.");
            System.out.println("Switch to delivery: Success");

            // Click first order
            boolean isFirstOrderClicked = landingdPage.clickFirstOrder2();
            assertTrue(isFirstOrderClicked, "Failed to click first order.");
            System.out.println("Click first order: Success");

            // Pick order
            boolean isPickOrderClicked = landingdPage.pickOrder();
            assertTrue(isPickOrderClicked, "Failed to pick first order.");
            System.out.println("Pick order: Success");

            // Complete the order
            boolean isOrderCompleted = landingdPage.completeOrder();
            assertTrue(isOrderCompleted, "Failed to complete order from DA.");
            System.out.println("Complete order from DA: Success");

        } catch (Exception e) {
            System.out.println("Exception occurred in testAutomateTheLandingDeliveryPage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}