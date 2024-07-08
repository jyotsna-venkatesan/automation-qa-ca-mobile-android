package org.CATests.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

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
    // button to open settings
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_text\" and @text=\"Settings\"]")
    private WebElement buttonOpenSettings;

    // button to click log in or sign up
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvLine1\" and @text=\"Hi guest\"]")
    private WebElement buttonLoginOrSignup;

    // button to input username
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"login_email_number__input__email_number\"]")
    private WebElement buttonInputUsername;

    // button to click next
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"login_email_number__button__next\"]")
    private WebElement buttonNext;

    // button to input password
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"login_password__input__password\"]")
    private WebElement buttonInputPassword;

    // button to click login
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"login_password__button__login\"]")
    private WebElement buttonLogIn;

    // button for ok on popup
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/materialButton\"]")
    private WebElement buttonOK;

    // button to exit
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"NAVIGATION_BUTTON\"]")
    private WebElement buttonExit;

    // functions:
    // open settings
    public boolean openSettings() {
        try {
            WebElement buttonOpenSettingsVisible = waitForVisibility(buttonOpenSettings);
            buttonOpenSettingsVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the settings button: " + e.getMessage());
            return false;
        }
    }

    // click on login or sign up
    public boolean clickLoginOrSignUp() {
        try {
            WebElement buttonLoginOrSignupVisible = waitForVisibility(buttonLoginOrSignup);
            buttonLoginOrSignupVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the login or sign up button: " + e.getMessage());
            return false;
        }
    }

    // enter email address or phone number
    public boolean enterUsername() {
        try {
            WebElement buttonInputUsernameVisible = waitForVisibility(buttonInputUsername);
            buttonInputUsernameVisible.click();
            String userName = configLoader.getProperty("EMAIL_OR_PHONENUMBER");
            buttonInputUsername.sendKeys(userName);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonInputUsername, Keys.RETURN).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // click next
    public boolean clickNext() {
        try {
            WebElement buttonNextVisible = waitForVisibility(buttonNext);
            buttonNextVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
    }

    // enter password
    public boolean enterPassword() {
        try {
            WebElement buttonInputPasswordVisible = waitForVisibility(buttonInputPassword);
            buttonInputPasswordVisible.click();
            String userPassword = configLoader.getProperty("PASSWORD");
            buttonInputPassword.sendKeys(userPassword);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonInputUsername, Keys.RETURN).perform();
            // Assuming buttonOK and buttonExit are already defined as WebElement
            boolean clickedOK = clickIfVisible(buttonOK, 5);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // click login
    public boolean clickLogIn() {
        try {
            WebElement buttonLogInVisible = waitForVisibility(buttonLogIn);
            buttonLogInVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the log in button: " + e.getMessage());
            return false;
        }
    }

    // click ok on popup
    public boolean clickOK() {
        try {
            WebElement buttonOKVisible = waitForVisibility(buttonOK);
            buttonOKVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the OK button: " + e.getMessage());
            return false;
        }
    }

    // click exit
    public boolean clickExit() {
        try {
            WebElement buttonExitVisible = waitForVisibility(buttonExit);
            buttonExitVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the exit button: " + e.getMessage());
            return false;
        }
    }





}
