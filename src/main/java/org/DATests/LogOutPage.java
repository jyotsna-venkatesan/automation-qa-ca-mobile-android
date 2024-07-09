package org.DATests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.CATests.utils.GlobalState.globalOrderID;

public class LogOutPage extends AbstractPageClass{

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // set up the driver for this page
    public LogOutPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // buttons:
    // button to open side bar
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    private WebElement buttonSideBar;

    // button to open settings
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Settings\"]")
    private WebElement buttonSettings;

    // button to log out
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Logout\"]")
    private WebElement buttonLogOut;


    // functions:
    // open the side bar
    public boolean openSideBar() {
        try {
            WebElement buttonSideBarVisible = waitForVisibility(buttonSideBar);
            buttonSideBarVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error opening the side bar : " + e.getMessage());
            return false;
        }
    }

    // go to settings page
    public boolean goToSettings() {
        try {
            WebElement buttonSettingsVisible = waitForVisibility(buttonSettings);
            buttonSettingsVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error opening settings: " + e.getMessage());
            return false;
        }
    }

    // click log out
    public boolean clickLogOut() {
        try {
            WebElement buttonLogOutVisible = waitForVisibility(buttonLogOut);
            buttonLogOutVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking log out: " + e.getMessage());
            return false;
        }
    }


}
