package org.CATests.pageObjects.android.delivery;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.CATests.utils.GlobalState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PlacedOrderDeliveryPage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public PlacedOrderDeliveryPage(AndroidDriver driver){
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // buttons:

    // button to open looking for a driver thing
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/orderStateHistoryInfoView\"]")
    private WebElement buttonOrderDetailsExpand;

    // button with order id
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/orderIdTextView\"]")
    private WebElement buttonOrderIDLocator;

    // credit card got it button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/primaryButton\"]")
    private WebElement buttonCreditCardGotIt;

    // cancel order button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/primaryButton\"]")
    private WebElement buttonCancelOrder;

    // pickup code
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/pickupCodeTextView\"]")
    private WebElement buttonCode;


    // functions:
    // expand order details
    public boolean expandOrderDetails() {
        try {
            // Assuming buttonOK and buttonExit are already defined as WebElement
            boolean clickbuttonCreditCardGotIt = clickIfVisible(buttonCreditCardGotIt, 15);
            System.out.println("checked for creditcard");
            WebElement buttonOrderDetailsExpandVisible = waitForVisibility(buttonOrderDetailsExpand);
            buttonOrderDetailsExpandVisible.click();
            System.out.println("expanded order details");
            return true;
        } catch (Exception e) {
            System.out.println("Error denying notifications: " + e.getMessage());
            return false;
        }
    }

    // get order id
    public boolean getOrderID() {
        try {
            WebElement buttonOrderIDVisible = waitForVisibility(buttonOrderIDLocator);
            // Extract the text from the element
            GlobalState.globalOrderID = buttonOrderIDLocator.getText();

            WebElement buttonCodeVisible = waitForVisibility(buttonCode);
            // Extract the text from the element
            GlobalState.globalPickUpCode = buttonCode.getText();

            return true;
        } catch (Exception e) {
            System.out.println("Error denying notifications: " + e.getMessage());
            return false;
        }
    }

    private String parseOrderID(String orderText) {
        // Assuming the text format is "Van · Order ID AU2945539"
        String[] parts = orderText.split("Order ID ");
        if (parts.length > 1) {
            return parts[1].trim();
        } else {
            // Handle unexpected format
            return null;
        }
    }

    public boolean cancelOrder() {
        try {
            String cancelFlag = configLoader.getProperty("CANCEL_FLAG");
            if(cancelFlag.equalsIgnoreCase("false")){
                System.out.println("Don't need to cancel the order");
                return true;
            }
            else{
                System.out.println("Cancel order is true");
                driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+"Cancel order"+"\"));"));
                System.out.println("Scrolling down");
                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[contains(@text,'Cancel order')]")).click();
                WebElement buttonCancelOrderVisible = waitForVisibility(buttonCancelOrder);
                buttonCancelOrderVisible.click();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error canceling order: " + e.getMessage());
            return false;
        }
    }



}
