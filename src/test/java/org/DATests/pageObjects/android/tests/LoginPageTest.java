package org.DATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.DATests.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginPageTest {

    private AndroidDriver driver;

    public LoginPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate opening the login process
    @Test
    public void testAutomateTheLoginPage(String orderID) {
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("-- Login Process --");

        // click on the deny notifications button
        // boolean isDenyNotificationsButtonClicked = loginPage.denyNotifications();
        // assertTrue(isDenyNotificationsButtonClicked, "failed to click on the deny notifications.");
        // System.out.println("Deny notifications: Success");

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
