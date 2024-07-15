package org.CATests.pageObjects.android.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.SideBarPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class SideBarTest {

    private AndroidDriver driver;

    // No-argument constructor
    public SideBarTest() {
        // Initialize driver here if needed
    }

    public SideBarTest(AndroidDriver driver) {
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


