package org.CATests.pageObjects.android.delivery;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SendPackagePage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // the button to input estate number
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/middleTextView\"]")
    private WebElement buttonEstateNumber;

    // estate number input bar
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"]")
    private WebElement buttonEstateNumberInput;

    // the first option to show up for the estate number section
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"route_item_1\"]/android.widget.LinearLayout")
    private WebElement buttonEstateNumberFirstOption;

    // the button for room floor and block
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[1]")
    private WebElement buttonRoomFloor;

    // the button for sender name
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[2]")
    private WebElement buttonName;

    // the button for sender number
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[3]")
    private WebElement buttonNumber;

    // the button for next
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/nextButton\"]")
    private WebElement buttonNext;


    // navigate back button
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"]")
    private WebElement buttonBack;

    // set up the driver for this page
    public SendPackagePage(AndroidDriver driver){
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // methods:
    // enter the estate/ street and number
    public boolean enterEstateNumber() {
        try {
            WebElement buttonEstateNumberVisible = waitForVisibility(buttonEstateNumber);
            buttonEstateNumberVisible.click();
            WebElement buttonEstateNumberInputVisible = waitForVisibility(buttonEstateNumberInput);
            String estateInput = configLoader.getProperty("ESTATE_NUMBER_RECIPIENT");
            buttonEstateNumberInput.sendKeys(estateInput);
            Thread.sleep(3000);
            WebElement buttonEstateNumberFirstOptionVisible = waitForVisibility(buttonEstateNumberFirstOption);
            buttonEstateNumberFirstOptionVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error inputting the estate number: " + e.getMessage());
            return false;
        }
    }

    // enter the room floor and block
    public boolean enterRoomFloor() {
        try{
            WebElement buttonRoomFloorVisible = waitForVisibility(buttonRoomFloor);
            String roomFloor = configLoader.getProperty("ROOM_FLOOR_RECIPIENT");
            buttonRoomFloor.sendKeys(roomFloor);
            return true;
        } catch (Exception e) {
            System.out.println("Error entering the room, floor and block: " + e.getMessage());
            return false;
        }
    }

    // enter the senders name
    public boolean enterName() {
        try{
            WebElement buttonNameVisible = waitForVisibility(buttonName);
            String senderName = configLoader.getProperty("RECIPIENT_NAME");
            buttonName.sendKeys(senderName);
            return true;
        } catch (Exception e) {
            System.out.println("Error entering the sender name: " + e.getMessage());
            return false;
        }
    }

    // enter the senders number
    public boolean enterNumber() {
        try{
            WebElement buttonNumberVisible = waitForVisibility(buttonNumber);
            String senderNumber = configLoader.getProperty("RECIPIENT_NUMBER");
            buttonNumber.sendKeys(senderNumber);
            return true;
        } catch (Exception e) {
            System.out.println("Error entering the sender number: " + e.getMessage());
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
            System.out.println("Error clicking next " + e.getMessage());
            return false;
        }
    }


}
