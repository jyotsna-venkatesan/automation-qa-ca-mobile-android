package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.PlacedOrderPage;
import org.CATests.pageObjects.android.transport.TimeAndVehiclePage;
import org.DATests.pageObjects.android.tests.DABaseTestClass;
import org.testng.annotations.Test;
import org.CATests.utils.GlobalState;
import static org.testng.Assert.assertTrue;


public class PlacedOrderPageTest {

    private AndroidDriver driver;

    public PlacedOrderPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    @Test
    public void testAutomateThePlacedOrderPage() {

        System.out.println("Inside the placed order page test ");

        PlacedOrderPage placedOrderPage = new PlacedOrderPage(driver);

        System.out.println("-- Placed order Page --");

        // click on the date and time button
        boolean isOrderDetailsExpanded = placedOrderPage.expandOrderDetails();
        assertTrue(isOrderDetailsExpanded, "Failed to expand order details.");
        System.out.println("Expanded the order details");

        // get order ID
        boolean isGetOrderID = placedOrderPage.getOrderID();
        assertTrue(isGetOrderID, "Failed to get order idß.");

        // Extract and store the Order ID as a global variable
        if (placedOrderPage.getOrderID()) {
            // Use the globalOrderID for further testing
            String orderID = GlobalState.globalOrderID;
            System.out.println("Global Order ID: " + orderID);
        } else {
            // Handle the case where the Order ID could not be extracted
            System.out.println("Failed to extract Order ID");
        }

        // cancel order if needed
        boolean isOrderCanceled = placedOrderPage.cancelOrder();
        assertTrue(isOrderCanceled, "Failed to cancel order.");
        System.out.println("Cancel the order if needed: Success");


    }
}