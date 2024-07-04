package org.CATests.pageObjects.android.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.LoginPage;
import org.CATests.pageObjects.android.SideBarPage;
import org.testng.annotations.Test;
import org.CATests.pageObjects.android.HomePage;
import static org.testng.Assert.assertTrue;

public class LoginPageTest {

    private AndroidDriver driver;

    public LoginPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate opening the login process
    @Test
    public void testAutomateTheLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("-- Login Process --");

        // click on the settings button
        boolean isSettingsButtonClicked= loginPage.openSettings();
        assertTrue(isSettingsButtonClicked, "failed to click on the button to open settings.");
        System.out.println("Open settings: Success");

        // click on the login or sign up button
        boolean isLoginOrSignUpButtonClicked= loginPage.clickLoginOrSignUp();
        assertTrue(isLoginOrSignUpButtonClicked, "failed to click on the button to login or sign up.");
        System.out.println("Open Login Or Sign Up: Success");

        // enter username
        boolean isUsernameEntered= loginPage.enterUsername();
        assertTrue(isUsernameEntered, "failed to enter username.");
        System.out.println("Enter username: Success");

        // click on the next button after inputting username
        boolean isNextButtonClicked= loginPage.clickNext();
        assertTrue(isNextButtonClicked, "failed to click on the next button after inputting username");

        // enter username
        boolean isPasswordEntered= loginPage.enterPassword();
        assertTrue(isPasswordEntered, "failed to enter password.");
        System.out.println("Enter password: Success");

        // click on the OK on popup
        // only if you have already created the account before
        // boolean isOKButtonClicked= loginPage.clickOK();
        // assertTrue(isOKButtonClicked, "failed to click on the OK button for the popup");


        // click on the exit button
        boolean isExitButtonClicked= loginPage.clickExit();
        assertTrue(isExitButtonClicked, "failed to click on the exit button");


    }
}
