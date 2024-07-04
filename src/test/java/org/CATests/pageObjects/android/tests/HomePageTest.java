package org.CATests.pageObjects.android.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.HomePage;
import static org.testng.Assert.assertTrue;

public class HomePageTest {

    private AndroidDriver driver;

    public HomePageTest(AndroidDriver driver) {
        this.driver = driver;
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
        assertTrue(isClicked, "failed to click on the transport button.");
        System.out.println("Going to Transport Page: Success");
    }

}
