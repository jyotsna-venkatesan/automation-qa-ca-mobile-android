package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.TimePage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class TimePageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public TimePageTest() {
        // Initialize driver here if needed
    }

    public TimePageTest(AndroidDriver driver) {
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

    // function to automate the time page
    @Test
    public void testAutomateTheTimePage() {
        TimePage timePage = new TimePage(driver);

        System.out.println("-- time Page --");

        // click on the date and time button
        boolean isDateAndTimeClicked = timePage.clickDateAndTime();
        assertTrue(isDateAndTimeClicked, "Failed to click the date and time button.");

        // click on the button of the selected date
        boolean isSelectedDateClicked = timePage.clickSelectedDate();
        assertTrue(isSelectedDateClicked, "Failed to click the selected date button.");

        // click on the button of the selected time
        boolean isSelectedTimeClicked = timePage.clickSelectedTime();
        assertTrue(isSelectedTimeClicked, "Failed to click the selected time button.");

        // click on the ok button of pick-up time
        boolean isOKClicked = timePage.clickOKDate();
        assertTrue(isOKClicked, "Failed to click the ok button for the date.");
        System.out.println("Select time and date: Success");

        // select drop time
        boolean isDropTimeSelected = timePage.clickDropOffTime();
        assertTrue(isDropTimeSelected, "Failed to select drop time.");
        System.out.println("Select drop time: Success");

        // click next
        boolean isNextClicked = timePage.clickNext();
        assertTrue(isNextClicked, "Failed to click next.");
        System.out.println("Click next: Success");

    }
}
