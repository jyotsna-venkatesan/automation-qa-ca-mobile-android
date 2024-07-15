package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.transport.AddressPage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class AddressPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public AddressPageTest() {
        // Initialize driver here if needed
    }


    public AddressPageTest(AndroidDriver driver) {
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

    // function to automate the address page
    @Test
    public void testAutomateTheAddressPage() {

        AddressPage addressPage = new AddressPage(driver);

        System.out.println("-- Address Page --");

        // Click on the 'where from' button
        boolean isWhereFromClicked = addressPage.clickWhereFrom();
        assertTrue(isWhereFromClicked, "Failed to click the 'where from' button.");

        // Enter address into the 'where from' input fields
        boolean isFromAddressEntered = addressPage.enterAddressFrom();
        assertTrue(isFromAddressEntered, "Failed to enter the 'from' address.");

        // Click on the first option under 'where from'
        boolean isFirstFromOptionClicked = addressPage.clickFirstOption();
        assertTrue(isFirstFromOptionClicked, "Failed to click the first option under 'where from'.");

        // Click on the done button
        boolean isDoneClicked = addressPage.clickDone();
        assertTrue(isDoneClicked, "Failed to click the done button.");
        System.out.println("From Address Input: Success");

        // Click on the 'where to' button
        boolean isWhereToClicked = addressPage.clickWhereTo();
        assertTrue(isWhereToClicked, "Failed to click the 'where to' button.");

        // Enter address into the 'where to' input fields
        boolean isToAddressEntered = addressPage.enterAddressTo();
        assertTrue(isToAddressEntered, "Failed to enter the 'to' address.");

        // Click on the first option under 'where to'
        boolean isFirstToOptionClicked = addressPage.clickFirstOption();
        assertTrue(isFirstToOptionClicked, "Failed to click the first option under 'where to'.");

        // Click on the done button
        boolean isDoneClickedSecond = addressPage.clickDone();
        assertTrue(isDoneClickedSecond, "Failed to click the done button for the to address.");
        System.out.println("Destination Address Input: Success");

        // Check for swap and swap
        boolean isSwap = addressPage.swapAddress();
        assertTrue(isSwap, "Failed to check for swapping the to address.");
        System.out.println("Address swap: Success");

        // If add stop, perform function
        boolean isAddStopClicked = addressPage.addStop();
        assertTrue(isAddStopClicked, "Failed to check for adding stop.");
        System.out.println("Add stop: Success");

        // Click on the next button
        boolean isNextClicked = addressPage.clickNext();
        assertTrue(isNextClicked, "Failed to click the next button.");

    }

}
