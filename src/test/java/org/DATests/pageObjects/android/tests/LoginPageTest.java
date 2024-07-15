package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LoginPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class LoginPageTest {

    private AndroidDriver driver;

    // No-argument constructor
    public LoginPageTest() {
        // Initialize driver here if needed
    }

    public LoginPageTest(AndroidDriver driver) {
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

    // function to automate opening the login process
    @Test
    public void testAutomateTheLoginPage(String orderID) {
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("-- Login Process --");

        // click on the deny notifications button
        boolean isDenyNotificationsButtonClicked = loginPage.denyNotifications();
        assertTrue(isDenyNotificationsButtonClicked, "failed to click on the deny notifications.");
        System.out.println("Deny notifications if needed: Success");

        // input the contact number
        boolean isContactNumberEntered = loginPage.enterContactNumber();
        assertTrue(isContactNumberEntered, "failed to enter the contact number.");
        System.out.println("Enter contact number: Success");

        // input the password
        boolean isPasswordEntered = loginPage.enterPassword();
        assertTrue(isPasswordEntered, "failed to enter the password.");
        System.out.println("Enter password: Success");

        // click login
        boolean isLogIn = loginPage.clickLogIn();
        assertTrue(isLogIn, "failed to log in.");
        System.out.println("Log In: Success");

    }
}
