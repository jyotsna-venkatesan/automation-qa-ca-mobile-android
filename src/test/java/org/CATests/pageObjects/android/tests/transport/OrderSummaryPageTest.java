package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.OrderDetailsPage;
import org.CATests.pageObjects.android.transport.OrderSummaryPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class OrderSummaryPageTest {

    private AndroidDriver driver;

    public OrderSummaryPageTest(AndroidDriver driver) {
        this.driver = driver;
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
