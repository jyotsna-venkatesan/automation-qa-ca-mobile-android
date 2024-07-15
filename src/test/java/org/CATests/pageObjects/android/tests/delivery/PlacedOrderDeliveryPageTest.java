package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.PickUpPage;
import org.CATests.pageObjects.android.delivery.PlacedOrderDeliveryPage;
import org.CATests.utils.GlobalState;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class PlacedOrderDeliveryPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public PlacedOrderDeliveryPageTest() {
        // Initialize driver here if needed
    }

    public PlacedOrderDeliveryPageTest(AndroidDriver driver) {
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

    // function to automate the placed order page
    @Test
    public void testAutomateThePlacedOrderDeliveryPage() {
        PlacedOrderDeliveryPage placedOrderDeliveryPage = new PlacedOrderDeliveryPage(driver);

        System.out.println("-- Placed order page for delivery orders --");

        // expand order details
        boolean isOrderDetailsExpanded = placedOrderDeliveryPage.expandOrderDetails();
        assertTrue(isOrderDetailsExpanded, "Failed to expand order details.");
        System.out.println("Expanded the order details");

        // get order ID
        boolean isGetOrderID = placedOrderDeliveryPage.getOrderID();
        assertTrue(isGetOrderID, "Failed to get order idß.");

        // Extract and store the Order ID as a global variable
        if (placedOrderDeliveryPage.getOrderID()) {
            // Use the globalOrderID for further testing
            String orderID = GlobalState.globalOrderID;
            System.out.println("Global Order ID: " + orderID);
            String orderCode = GlobalState.globalPickUpCode;
            System.out.println("Global Order ID: " + orderCode);
        } else {
            // Handle the case where the Order ID could not be extracted
            System.out.println("Failed to extract Order ID");
        }

        // cancel order if needed
        boolean isOrderCanceled = placedOrderDeliveryPage.cancelOrder();
        assertTrue(isOrderCanceled, "Failed to cancel order.");
        System.out.println("Cancel the order if needed: Success");



    }
}
