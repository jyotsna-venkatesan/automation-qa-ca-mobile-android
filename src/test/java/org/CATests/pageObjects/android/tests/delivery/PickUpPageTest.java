package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.PickUpPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class PickUpPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public PickUpPageTest() {
        // Initialize driver here if needed
    }

    public PickUpPageTest(AndroidDriver driver) {
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

    // function to automate the pick up page
    @Test
    public void testAutomateThePickUpPage() {
        PickUpPage pickUpPage = new PickUpPage(driver);

        System.out.println("-- PickUp Page --");

        // Enter estate number
        boolean isEstateNumberEntered = pickUpPage.enterEstateNumber();
        assertTrue(isEstateNumberEntered, "Failed to enter estate number.");
        System.out.println("Enter estate number: Success");

        // Enter room floor
        boolean isRoomEntered = pickUpPage.enterRoomFloor();
        assertTrue(isRoomEntered, "Failed to enter room and floor.");
        System.out.println("Enter room and floor: Success");

        // Enter name
        boolean isNameEntered = pickUpPage.enterName();
        assertTrue(isNameEntered, "Failed to enter name.");
        System.out.println("Enter name: Success");

        // Enter number
        boolean isNumberEntered = pickUpPage.enterNumber();
        assertTrue(isNumberEntered, "Failed to enter number.");

        // click next
        boolean isNextClicked = pickUpPage.clickNext();
        assertTrue(isNextClicked, "Failed to click next.");
        System.out.println("Click next: Success");

    }
}
