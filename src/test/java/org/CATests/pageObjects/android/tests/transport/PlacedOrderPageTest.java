package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.PlacedOrderPage;
import org.CATests.pageObjects.android.transport.TimeAndVehiclePage;
import org.DATests.pageObjects.android.tests.DABaseTestClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.CATests.utils.GlobalState;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;


public class PlacedOrderPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public PlacedOrderPageTest() {
        // Initialize driver here if needed
    }

    public PlacedOrderPageTest(AndroidDriver driver) {
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