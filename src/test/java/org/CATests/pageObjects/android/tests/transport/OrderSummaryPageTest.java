package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.OrderDetailsPage;
import org.CATests.pageObjects.android.transport.OrderSummaryPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class OrderSummaryPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public OrderSummaryPageTest() {
        // Initialize driver here if needed
    }

    public OrderSummaryPageTest(AndroidDriver driver) {
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
    public void testAutomateTheOrderSummaryPage() {
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
        System.out.println("-- Order Summary Page --");

        // select tip
        boolean isTipSelected = orderSummaryPage.selectTip();
        assertTrue(isTipSelected, "Failed to select tip.");
        System.out.println("Select tip: Success");

        // add coupon
        boolean isCouponAdded = orderSummaryPage.applyCoupon();
        assertTrue(isCouponAdded, "Failed to add coupon.");
        System.out.println("Add coupon: Success");

        // select payment method
        boolean isPaymentMethodSelected = orderSummaryPage.selectPaymentMethod();
        assertTrue(isPaymentMethodSelected, "Failed to select payment method.");
        System.out.println("Select payment method: Success");

        // place order
        boolean isOrderPlaced = orderSummaryPage.placeOrder();
        assertTrue(isOrderPlaced, "Failed to place order.");
        System.out.println("Place Order: Success");
    }

}
