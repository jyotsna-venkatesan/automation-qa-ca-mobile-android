package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LandingPage;
import org.DATests.LoginPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;
public class LandingPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public LandingPageTest() {
        // Initialize driver here if needed
    }

    public LandingPageTest(AndroidDriver driver) {
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
        // boolean isLocationSettings = landingPage.locationSettings();
        // assertTrue(isLocationSettings, "failed to do location settings.");
        // System.out.println("Location Settings: Success");

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
