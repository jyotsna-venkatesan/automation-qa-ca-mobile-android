package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LandingPage;
import org.DATests.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
public class LandingPageTest {

    private AndroidDriver driver;

    public LandingPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate opening the login process
    @Test
    public void testAutomateTheLandingPage(String orderID) {
        LandingPage landingPage = new LandingPage(driver);
        System.out.println("-- Landing Page --");

        // close document expiry popup
        boolean isCloseDocumentExpiryPopup = landingPage.closeDocumentExpiryPopup();
        assertTrue(isCloseDocumentExpiryPopup, "failed to close document expiry popup.");
        System.out.println("Close document expiry popup: Success");

        // open orders page
        boolean isOpenOrdersPage = landingPage.goToOrders();
        assertTrue(isOpenOrdersPage, "failed to go to orders page.");
        System.out.println("Go to orders page: Success");

        // sort orders
        boolean isOrdersSorted = landingPage.sortOrders();
        assertTrue(isOrdersSorted, "failed to sort orders.");
        System.out.println("Sort orders: Success");

        // location settings
        boolean isLocationSettings = landingPage.locationSettings();
        assertTrue(isLocationSettings, "failed to do location settings.");
        System.out.println("Location Settings: Success");

        // click first order
        boolean isFirstOrderClicked = landingPage.clickFirstOrder();
        assertTrue(isFirstOrderClicked, "failed to click first order.");
        System.out.println("Click first order: Success");

        // complete the order
        boolean isOrderCompleted = landingPage.completeOrder();
        assertTrue(isOrderCompleted, "failed to complete order from DA.");
        System.out.println("Complete order from DA: Success");


    }
}
