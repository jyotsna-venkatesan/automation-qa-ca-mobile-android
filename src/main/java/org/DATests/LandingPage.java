package org.DATests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.CATests.utils.GlobalState.globalOrderID;

public class LandingPage extends AbstractPageClass{

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // set up the driver for this page
    public LandingPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // buttons:
    // button to close document expiry popup
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/closeImageView\"]")
    private WebElement buttonCloseDocumentExpiryPopup;

    // button to go to the orders page
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/navigation_bar_item_icon_view\"])[1]")
    private WebElement buttonOrders;

    // button to sort orders
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/ivSort\"]")
    private WebElement buttonSortOrders;

    // button to sort latest orders
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/tvItem\" and @text=\"Latest order\"]")
    private WebElement buttonSortLatestOrders;

    // first order
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/order_base\"])[1]")
    private WebElement buttonFirstOrder;

    // button to go settings
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/primaryButton\"]")
    private WebElement buttonSettings;

    // button permissions
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Permissions\"]")
    private WebElement buttonPermissions;

    // button location permission
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Location\"]")
    private WebElement buttonLocationPermission;

    // button location permission allow all the time
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.android.permissioncontroller:id/allow_always_radio_button\"]")
    private WebElement buttonAlwaysLocationPermission;

    // button navigate back
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    private WebElement buttonNavigateBack;

    // pick the first order
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnPickOrder\"]")
    private WebElement buttonPickFirstOrder;

    // confirm pick order
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnStartDriving\"]")
    private WebElement buttonPickUpConfirm;

    // begin driving
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnStartDriving\"]")
    private WebElement buttonBeginDriving;

    // arrived pick up point
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnArrivedAtWaypoint\"]")
    private WebElement buttonArrivedPickupPoint;

    // arrived drop off point
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnArrivedDestination\"]")
    private WebElement buttonArrivedDropPoint;

    // view order price
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnCompleteOrder\"]")
    private WebElement buttonViewOrderPrice;

    // complete order
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/btnCompleteOrder\"]")
    private WebElement buttonCompleteOrder;

    // continue to pick order
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/secondaryButton\"]")
    private WebElement buttonContinueToPickOrder;




    // functions:
    // close document expiry popup
    public boolean closeDocumentExpiryPopup() {
        try {
            boolean clickbuttonDocmentExpiry = clickIfVisible(buttonCloseDocumentExpiryPopup, 3);
            return true;
        } catch (Exception e) {
            System.out.println("Error closing document expiry popup : " + e.getMessage());
            return false;
        }
    }

    // go to orders page
    public boolean goToOrders() {
        try {
            WebElement buttonOrdersVisible = waitForVisibility(buttonOrders);
            buttonOrdersVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error opening orders page: " + e.getMessage());
            return false;
        }
    }


    // click on first order
    public boolean clickFirstOrder() {
        try {
            // String formattedGlobalOrderID = "#" + globalOrderID;
            // String orderIDXPath = "//android.widget.TextView[@resource-id='hk.gogovan.GoGoDriver.staging:id/order_id_light']";
            // List<WebElement> orderIDElements = driver.findElements(By.xpath(orderIDXPath));

            // Iterate through the elements to find the matching order ID
            // for (WebElement orderIDElement : orderIDElements) {
                // if (orderIDElement.getText().equals(formattedGlobalOrderID)) {
                    // orderIDElement.click();
                    // WebElement buttonPickFirstOrderVisible = waitForVisibility(buttonPickFirstOrder);
                    // buttonPickFirstOrderVisible.click();
                    // return true;
                // }
            // }
            WebElement buttonFirstOrderVisible = waitForVisibility(buttonFirstOrder);
            buttonFirstOrderVisible.click();
            WebElement buttonPickFirstOrderVisible = waitForVisibility(buttonPickFirstOrder);
            buttonPickFirstOrderVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking order: " + e.getMessage());
            return false;
        }
    }

    // location settings
    public boolean sortOrders() {
        try {
            WebElement buttonSortOrdersVisible = waitForVisibility(buttonSortOrders);
            buttonSortOrdersVisible.click();
            WebElement buttonSortLatestOrdersVisible = waitForVisibility(buttonSortLatestOrders);
            buttonSortLatestOrdersVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking first order: " + e.getMessage());
            return false;
        }
    }


    // location settings
    public boolean locationSettings() {
        try {
            WebElement buttonFirstOrderVisible = waitForVisibility(buttonFirstOrder);
            buttonFirstOrderVisible.click();
            WebElement buttonSettingsVisible = waitForVisibility(buttonSettings);
            buttonSettingsVisible.click();
            WebElement buttonPermissionsVisible = waitForVisibility(buttonPermissions);
            buttonPermissionsVisible.click();
            WebElement buttonLocationPermissionVisible = waitForVisibility(buttonLocationPermission);
            buttonLocationPermissionVisible.click();
            WebElement buttonAlwaysLocationPermissionVisible = waitForVisibility(buttonAlwaysLocationPermission);
            buttonAlwaysLocationPermissionVisible.click();
            WebElement buttonNavigateBackVisible = waitForVisibility(buttonNavigateBack);
            buttonNavigateBackVisible.click();
            buttonNavigateBackVisible.click();
            buttonNavigateBackVisible.click();
            Thread.sleep(7000);
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking first order: " + e.getMessage());
            return false;
        }
    }



    // complete the order
    public boolean completeOrder() {
        try {
            Thread.sleep(15000); // wait for order to confirm pickup
            WebElement buttonBeginDrivingVisible = waitForVisibility(buttonBeginDriving);
            buttonBeginDrivingVisible.click();
            boolean clickStartDrivingAgain = clickIfVisible(buttonBeginDrivingVisible, 3);
            System.out.println("started driving");
            WebElement buttonArrivedPickUpVisible = waitForVisibility(buttonArrivedPickupPoint);
            buttonArrivedPickUpVisible.click();
            System.out.println("arrived pickup");
            WebElement buttonArrivedDropVisible = waitForVisibility(buttonArrivedDropPoint);
            buttonArrivedDropVisible.click();
            System.out.println("arrive drop");
            WebElement buttonViewPriceVisible = waitForVisibility(buttonViewOrderPrice);
            buttonViewPriceVisible.click();
            System.out.println("viewed price");
            WebElement buttonCompleteVisible = waitForVisibility(buttonCompleteOrder);
            buttonCompleteVisible.click();
            System.out.println("completed order");
            WebElement buttonContinueVisible = waitForVisibility(buttonContinueToPickOrder);
            buttonContinueVisible.click();
            System.out.println("continuing to pick order");

            return true;
        } catch (Exception e) {
            System.out.println("Error completing order: " + e.getMessage());
            return false;
        }
    }


}
