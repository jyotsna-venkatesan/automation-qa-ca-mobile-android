package org.CATests.pageObjects.android.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.SideBarPage;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.HomePage;
import static org.testng.Assert.assertTrue;

public class SideBarTest {

    private AndroidDriver driver;

    public SideBarTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate opening the side bar
    @Test
    public void testAutomateTheSideBarPage() {
        SideBarPage sideBarPage = new SideBarPage(driver);
        System.out.println("-- Side Bar --");

        // Deny access to the device's location
        boolean isDenyClicked = sideBarPage.clickDeny();
        assertTrue(isDenyClicked, "Failed to click the deny access to device location button");
        System.out.println("Deny Access to device location: Success");

        // open sidebar
        boolean isSideBarButtonClicked= sideBarPage.openSideBar();
        assertTrue(isSideBarButtonClicked, "failed to click on the button to open side bar.");
        System.out.println("Open side bar: Success");
    }

}


