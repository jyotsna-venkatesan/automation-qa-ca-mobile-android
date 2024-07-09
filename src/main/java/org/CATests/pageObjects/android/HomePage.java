package org.CATests.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends AbstractPageClass {

    // set up the driver for this page
    public HomePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    // buttons:
    // transport page button, in case if it is not opened already
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Transport\"])[1]")
    private WebElement buttonToTransportPage;

    // buttons:
    // transport page button, in case if it is not opened already
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]")
    private WebElement buttonNotifications;

    // methods:

    // method to click on the button
    public boolean clickAllowNotifications() {
        try {
            boolean clickbuttonNotifications = clickIfVisible(buttonNotifications, 3);
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the notifications button: " + e.getMessage());
            return false;
        }
    }

    // method to click on the button
    public boolean clickTransportButton() {
        try {
            WebElement buttonToTransportPageVisible = waitForVisibility(buttonToTransportPage);
            buttonToTransportPageVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the transport button: " + e.getMessage());
            return false;
        }
    }


}
