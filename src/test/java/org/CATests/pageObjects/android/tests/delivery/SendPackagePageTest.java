package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.SendPackagePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SendPackagePageTest {

    private AndroidDriver driver;

    public SendPackagePageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate the send package page
    @Test
    public void testAutomateTheSendPackagePage() {
        SendPackagePage sendPackagePage = new SendPackagePage(driver);

        System.out.println("-- Send a package to Page --");

        // Enter estate number
        boolean isEstateNumberEntered = sendPackagePage.enterEstateNumber();
        assertTrue(isEstateNumberEntered, "Failed to enter estate number.");
        System.out.println("Enter estate number: Success");

        // Enter room floor
        boolean isRoomEntered = sendPackagePage.enterRoomFloor();
        assertTrue(isRoomEntered, "Failed to enter room and floor.");
        System.out.println("Enter room floor: Success");

        // Enter name
        boolean isNameEntered = sendPackagePage.enterName();
        assertTrue(isNameEntered, "Failed to enter name.");
        System.out.println("Enter name: Success");

        // Enter number
        boolean isNumberEntered = sendPackagePage.enterNumber();
        assertTrue(isNumberEntered, "Failed to enter number.");
        System.out.println("Enter number: Success");

        // click next
        boolean isNextClicked = sendPackagePage.clickNext();
        assertTrue(isNextClicked, "Failed to click next.");
        System.out.println("Click next: Success");

    }
}
