package org.CATests.pageObjects.android.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class HomePageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public HomePageTest() {
        // Initialize driver here if needed
    }

    public HomePageTest(AndroidDriver driver) {
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

    // function to automate the home page (clicking on the transport button)
    @Test
    public void testAutomateTheHomePage() {
        HomePage homePage = new HomePage(driver);
        System.out.println("-- Home Page --");

        // click notifications button
        boolean isNotificationsClicked = homePage.clickAllowNotifications();
        assertTrue(isNotificationsClicked, "failed to click on the notifications button.");
        System.out.println("Click allow notifications: Success");

        // click transport button
        boolean isClicked = homePage.clickTransportButton();
        assertTrue(isClicked, "failed to click on the t or d button.");
        System.out.println("Going to T or D Page: Success");
    }

}
