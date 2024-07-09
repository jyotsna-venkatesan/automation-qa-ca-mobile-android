package org.CATests.pageObjects.android.tests.transport;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.transport.TimeAndVehiclePage;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.transport.AddressPage;
import static org.testng.Assert.assertTrue;

public class TimeAndVehiclePageTest {

    private AndroidDriver driver;

    public TimeAndVehiclePageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    @Test
    public void testAutomateTheTimeAndVehiclePage() {

        TimeAndVehiclePage timeAndVehiclePage = new TimeAndVehiclePage(driver);

        System.out.println("-- Time and Vehicle Page --");

        // click on the date and time button
        boolean isDateAndTimeClicked = timeAndVehiclePage.clickDateAndTime();
        assertTrue(isDateAndTimeClicked, "Failed to click the date and time button.");

        // click on the button of the selected date
        boolean isSelectedDateClicked = timeAndVehiclePage.clickSelectedDate();
        assertTrue(isSelectedDateClicked, "Failed to click the selected date button.");

        // click on the button of the selected time
        boolean isSelectedTimeClicked = timeAndVehiclePage.clickSelectedTime();
        assertTrue(isSelectedTimeClicked, "Failed to click the selected time button.");

        // click on the ok button of pick-up time
        boolean isOKClicked = timeAndVehiclePage.clickOKDate();
        assertTrue(isOKClicked, "Failed to click the ok button for the date.");
        System.out.println("Select time and date: Success");

        // click on the hourly rental button
        boolean isHourlyRentalClicked = timeAndVehiclePage.clickHourlyRental();
        assertTrue(isHourlyRentalClicked, "Failed to click the hourly rental button.");


        // click on the button of the selected hourly rental time
        boolean isSelectedHourlyRentalTimeClicked = timeAndVehiclePage.clickSelectedHourlyRentalTime();
        assertTrue(isSelectedHourlyRentalTimeClicked, "Failed to click the selected hourly rental time button.");

        // click on the ok button of pick-up time
        boolean isOKClicked2 = timeAndVehiclePage.clickOKRental();
        assertTrue(isOKClicked2, "Failed to click the ok button for the hourly rental page.");
        System.out.println("Select hourly rental time: Success");

        // click on vehicle type
        boolean isVehicleSelected = timeAndVehiclePage.vehicleSelect();
        assertTrue(isVehicleSelected, "Failed to select the vehicle type");
        System.out.println("Select vehicle type: Success");

        // Click on the next button
        boolean isNextClicked = timeAndVehiclePage.clickNext();
        assertTrue(isNextClicked, "Failed to click the next button.");


    }

}
