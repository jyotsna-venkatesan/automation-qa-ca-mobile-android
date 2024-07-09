package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.PickUpPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class PickUpPageTest {

    private AndroidDriver driver;

    public PickUpPageTest(AndroidDriver driver) {
        this.driver = driver;
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
