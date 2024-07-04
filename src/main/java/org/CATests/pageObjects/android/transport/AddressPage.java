package org.CATests.pageObjects.android.transport;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class AddressPage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // the 'where from' button
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"ggv__home__bottom_sheet__input_waypoint__pick_up_point\"]")
    private WebElement buttonWhereFrom;

    // the box to enter address
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/etWayPointName\"]")
    private WebElement buttonInput;

    // the first option to show up
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"route_item_1\"]/android.widget.LinearLayout")
    private WebElement buttonFirstOption;

    // the done button for where from
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/rightTextButton\"]")
    private WebElement buttonDoneWhereFrom;

    // the 'where to' button
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/etWayPointName\" and @text=\"Where to?\"]")
    private WebElement buttonWhereTo;

    // the next button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/nextButton\"]")
    private WebElement buttonNext;

    // the swap button
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/btnReverseWayPointLower\"]")
    private WebElement buttonSwap;

    // the add stop button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvAddWaypoint\"]")
    private WebElement buttonAddStop;

    // the where to button for the add stop function
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/etWayPointName\" and @text=\"Where to?\"]")
    private WebElement buttonAddStopWhereTo;

    // the input bar for the add stop function
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/etWayPointName\"]")
    private WebElement buttonAddStopInput;

    // the first option to show up for add stop function
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"route_item_1\"]/android.widget.LinearLayout")
    private WebElement buttonFirstOptionAddStop;


    // set up the driver for this page
    public AddressPage(AndroidDriver driver){
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // methods:
    // click on the next button
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

    // click on the first option under where from after entering address
    public boolean clickFirstOption() {
        try {
            Thread.sleep(3000);
            WebElement buttonFirstOptionVisible = waitForVisibility(buttonFirstOption);
            buttonFirstOptionVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the first option " + e.getMessage());
            return false;
        }
    }

    // click on the done button
    public boolean clickDone() {
        try {
            buttonDoneWhereFrom.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the done button " + e.getMessage());
            return false;
        }
    }

    // where from methods:
    // click on the where from button
    public boolean clickWhereFrom() {
        try {
            buttonWhereFrom.click();
            Thread.sleep(3000);
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the where from button: " + e.getMessage());
            return false;
        }
    }

    // enter address for where from
    public boolean enterAddressFrom() {
        try {
            Thread.sleep(3000);
            System.out.println("looking for input bar for address for where from");
            WebElement buttonInputVisible = waitForVisibility(buttonInput);
            System.out.println("found it!");
            String fromAddress = configLoader.getProperty("FROM_ADDRESS");
            buttonInput.sendKeys(fromAddress);
            System.out.println("send keys into input bar for address for where from");
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonInput, Keys.RETURN).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // where to methods:
    // click on the where to button
    public boolean clickWhereTo() {
        try {
            buttonWhereTo.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the where to button: " + e.getMessage());
            return false;
        }
    }


    // enter address for where to
    public boolean enterAddressTo() {
        try {
            WebElement buttonInputVisible = waitForVisibility(buttonInput);
            buttonInputVisible.click();
            String toAddress = configLoader.getProperty("TO_ADDRESS");
            buttonInput.sendKeys(toAddress);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonInput, Keys.RETURN).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // if the user wants to swap destination and from address
    public boolean swapAddress(){
        try{
            String swapFlag = configLoader.getProperty("SWAP");
            if (swapFlag.equals("false")){
                return true;
            }
            WebElement buttonSwapVisible = waitForVisibility(buttonSwap);
            buttonSwapVisible.click();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // add the stop if needed
    public boolean addStop() {
        try {
            String stopFlag = configLoader.getProperty("STOP_NEEDED");
            if (stopFlag.equals("false")){
                return true;
            }
            WebElement buttonAddStopVisible = waitForVisibility(buttonAddStop);
            buttonAddStopVisible.click();
            WebElement buttonAddStopWhereToVisible = waitForVisibility(buttonAddStopWhereTo);
            buttonAddStopWhereToVisible.click();
            WebElement buttonAddStopInputVisible = waitForVisibility(buttonAddStopInput);
            buttonAddStopInputVisible.click();
            String stopAddress = configLoader.getProperty("STOP_ADDRESS");
            buttonAddStopInput.sendKeys(stopAddress);
            // press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(buttonAddStopInput, Keys.RETURN).perform();
            WebElement buttonFirstOptionAddStopVisible = waitForVisibility(buttonFirstOptionAddStop);
            buttonFirstOptionAddStopVisible.click();
            buttonDoneWhereFrom.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}