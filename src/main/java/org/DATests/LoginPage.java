package org.DATests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPageClass{

    // Call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // Set up the driver for this page
    public LoginPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // Buttons:
    // Button to don't allow notifications
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement buttonDenyNotifications;

    // Button full content page
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/contentFrame\"]")
    private WebElement buttonContentPage;

    // Button contact number
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/etLogin\"]")
    private WebElement buttonContactNumber;

    // Button password
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/etPassword\"]")
    private WebElement buttonPassword;

    // Button log in
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnLogin\"]")
    private WebElement buttonLogIn;

    // Functions:
    // Deny notifications
    public boolean denyNotifications() {
        try {
            System.out.println("Attempting to deny notifications");
            if (isElementPresent(buttonDenyNotifications)) {
                boolean buttonDenyNotificationsVisible = clickIfVisible(buttonDenyNotifications, 8);
                System.out.println("Deny notifications button clicked: " + buttonDenyNotificationsVisible);
            } else {
                System.out.println("Deny notifications button not found");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error denying notifications: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to check if an element is present
    private boolean isElementPresent(WebElement element) {
        try {
            waitForVisibility(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Enter contact number
    public boolean enterContactNumber() {
        try {
            System.out.println("Attempting to enter contact number");
            WebElement buttonContentPageVisible = waitForVisibility(buttonContentPage);
            buttonContentPageVisible.click();
            System.out.println("Content page button clicked");

            WebElement buttonContactNumberVisible = waitForVisibility(buttonContactNumber);
            buttonContactNumberVisible.click();
            System.out.println("Contact number field clicked");

            String phoneNumber = configLoader.getProperty("DRIVER_PHONENUMBER");
            System.out.println("Entering phone number: " + phoneNumber);
            buttonContactNumber.sendKeys(phoneNumber);

            // Press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonContactNumber, Keys.RETURN).perform();
            System.out.println("Phone number entered and enter key pressed");
            return true;
        } catch (Exception e) {
            System.out.println("Error entering contact number: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Enter password
    public boolean enterPassword() {
        try {
            System.out.println("Attempting to enter password");
            WebElement buttonPasswordVisible = waitForVisibility(buttonPassword);
            buttonPasswordVisible.click();
            System.out.println("Password field clicked");

            String password = configLoader.getProperty("DRIVER_PASSWORD");
            System.out.println("Entering password");
            buttonPassword.sendKeys(password);

            // Press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonPassword, Keys.RETURN).perform();
            System.out.println("Password entered and enter key pressed");

            // Dismiss the keyboard
            driver.hideKeyboard();
            System.out.println("Keyboard dismissed");
            return true;
        } catch (Exception e) {
            System.out.println("Error entering password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Click login button
    public boolean clickLogIn() {
        try {
            System.out.println("Attempting to click login button");
            WebElement buttonLogInVisible = waitForVisibility(buttonLogIn);
            buttonLogInVisible.click();
            System.out.println("Login button clicked");
            return true;
        } catch (Exception e) {
            System.out.println("Error logging in: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}