package org.CATests.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SideBarPage extends AbstractPageClass{

    // set up the driver for this page
    public SideBarPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // buttons:
    // button to open the sidebar
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"home__header__navigation_icon__menu\"]")
    private WebElement buttonOpenSideBar;

    // deny access to device's location
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]")
    private WebElement buttonDeny;

    // functions

    // click deny access to device's location
    public boolean clickDeny() {
        try {
            boolean clickbuttonDeny = clickIfVisible(buttonDeny, 3);
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the where from button: " + e.getMessage());
            return false;
        }
    }

    // open the sidebar
    public boolean openSideBar() {
        try {
            WebElement buttonOpenSideBarVisible = waitForVisibility(buttonOpenSideBar);
            buttonOpenSideBarVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the sidebar button: " + e.getMessage());
            return false;
        }
    }


}
