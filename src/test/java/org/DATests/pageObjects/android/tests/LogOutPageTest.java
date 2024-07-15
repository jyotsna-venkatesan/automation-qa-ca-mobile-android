package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LogOutPage;
import org.DATests.LoginPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class LogOutPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public LogOutPageTest() {
        // Initialize driver here if needed
    }


    public LogOutPageTest(AndroidDriver driver) {
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

    // function to automate opening the logout process
    @Test
    public void testAutomateTheLogOutPage(String orderID) {
        LogOutPage logoutPage = new LogOutPage(driver);
        System.out.println("-- Log out Process --");

        // open the side bar
        boolean isSideBarClicked = logoutPage.openSideBar();
        assertTrue(isSideBarClicked, "failed to open side bar.");
        System.out.println("Open Sidebar: Success");

        // open settings
        boolean isSettingsClicked = logoutPage.goToSettings();
        assertTrue(isSettingsClicked, "failed to open settings.");
        System.out.println("Open Settings: Success");

        // click log out
        boolean isLogoutClicked = logoutPage.clickLogOut();
        assertTrue(isLogoutClicked, "failed to log out.");
        System.out.println("Log out: Success");

    }
}
