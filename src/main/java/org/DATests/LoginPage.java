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

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // set up the driver for this page
    public LoginPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // buttons:
    // button to don't allow notifications
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement buttonDenyNotifications;

    // button full content page
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/contentFrame\"]")
    private WebElement buttonContentPage;

    // button contact number
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/etLogin\"]")
    private WebElement buttonContactNumber;

    // button password
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/etPassword\"]")
    private WebElement buttonPassword;

    // button log in
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnLogin\"]")
    private WebElement buttonLogIn;

    // functions:
    // deny notifications
    public boolean denyNotifications() {
        try {
            WebElement buttonDenyNotificationsVisible = waitForVisibility(buttonDenyNotifications);
            buttonDenyNotificationsVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error denying notifications: " + e.getMessage());
            return false;
        }
    }

    // enter contact number
    public boolean enterContactNumber() {
        try {
            WebElement buttonContentPageVisible = waitForVisibility(buttonContentPage);
            buttonContentPageVisible.click();
            WebElement buttonContactNumberVisible = waitForVisibility(buttonContactNumber);
            buttonContactNumberVisible.click();
            String phoneNumber = configLoader.getProperty("DRIVER_PHONENUMBER");
            buttonContactNumber.sendKeys(phoneNumber);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonContactNumber, Keys.RETURN).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // enter password
    public boolean enterPassword() {
        try {
            WebElement buttonPasswordVisible = waitForVisibility(buttonPassword);
            buttonPasswordVisible.click();
            String password = configLoader.getProperty("DRIVER_PASSWORD");
            buttonPassword.sendKeys(password);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonPassword, Keys.RETURN).perform();

            // Dismiss the keyboard
            driver.hideKeyboard();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // click login button
    public boolean clickLogIn() {
        try {
            WebElement buttonLogInVisible = waitForVisibility(buttonLogIn);
            buttonLogInVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error in logging in: " + e.getMessage());
            return false;
        }
    }
}
