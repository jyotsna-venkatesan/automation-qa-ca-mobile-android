package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LogOutPage;
import org.DATests.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogOutPageTest {

    private AndroidDriver driver;

    public LogOutPageTest(AndroidDriver driver) {
        this.driver = driver;
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
