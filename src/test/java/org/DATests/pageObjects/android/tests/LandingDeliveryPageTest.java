package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LandingDeliveryPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LandingDeliveryPageTest {

    private AndroidDriver driver;

    public LandingDeliveryPageTest(AndroidDriver driver) {
        this.driver = driver;
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