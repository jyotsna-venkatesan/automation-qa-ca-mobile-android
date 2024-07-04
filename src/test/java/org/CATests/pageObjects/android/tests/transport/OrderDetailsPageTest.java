package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.OrderDetailsPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class OrderDetailsPageTest {

    private AndroidDriver driver;

    public OrderDetailsPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    @Test
    public void testAutomateTheOrderDetailsPage() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        System.out.println("-- Order Details Page --");

        // select cargo compensation
        boolean isCargoCompensationSelected = orderDetailsPage.selectCargoCompensation();
        assertTrue(isCargoCompensationSelected, "Failed to select cargo compensation.");
        System.out.println("Select cargo compensation: Success");

        // select number of passengers
        boolean isPassengerNumberSelected = orderDetailsPage.selectNumberOfPassengers();
        assertTrue(isPassengerNumberSelected, "Failed to select number of passengers.");
        System.out.println("Select passenger count: Success");

        // select number of carts
        boolean isCartNumberSelected = orderDetailsPage.selectNumberOfCart();
        assertTrue(isCartNumberSelected, "Failed to select number of carts.");
        System.out.println("Select cart count: Success");

        // select the other true or false options:
        // 1. goods longer than 6 ft
        // 2. pet friendly driver
        // 3. english-speaking driver
        boolean isOtherSelected= orderDetailsPage.selectOtherOptions();
        assertTrue(isOtherSelected, "Failed to select other options.");
        System.out.println("Select options: Success");


        // select tunnel preference if any
        boolean isTunnelPreferenceSelected = orderDetailsPage.selectTunnelPreference();
        assertTrue(isTunnelPreferenceSelected, "Failed to select tunnel preference.");
        System.out.println("Select tunnel preference: Success");

        // select the quote options
        // 1. move door to door
        // 2. transport or dispose waste
        boolean isQuoteSelected= orderDetailsPage.selectQuoteOptions();
        assertTrue(isQuoteSelected, "Failed to select quote options.");
        System.out.println("Select quote options: Success");

        // add contact information
        boolean isContactInfoAdded = orderDetailsPage.addContactInfo();
        assertTrue(isContactInfoAdded, "Failed to add contact info.");
        System.out.println("Add contact info: Success");

        // click review order to exit
        boolean isReviewOrderClicked2 = orderDetailsPage.clickReviewOrder();
        assertTrue(isReviewOrderClicked2, "Failed to click review order to exit.");
        System.out.println("Review order: Success");

    }
}
