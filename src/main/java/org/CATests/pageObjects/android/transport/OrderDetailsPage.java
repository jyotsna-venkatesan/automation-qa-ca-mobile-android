package org.CATests.pageObjects.android.transport;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.pageObjects.android.JavaScriptHelper;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class OrderDetailsPage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;
    // call the javascript helper to scroll
    protected JavaScriptHelper jsHelper;

    // set up the driver for this page
    public OrderDetailsPage(AndroidDriver driver) {
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.jsHelper = new JavaScriptHelper(driver);
    }

    //buttons
    // button for included cargo compensation
    @AndroidFindBy(xpath = "(//androidx.cardview.widget.CardView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/card_view\"])[1]/android.view.ViewGroup")
    private WebElement buttonIncludeCargoCompensation;

    // button for basic cargo compensation
    @AndroidFindBy(xpath = "(//androidx.cardview.widget.CardView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/card_view\"])[2]/android.view.ViewGroup")
    private WebElement buttonBasicCargoCompensation;

    // button for standard cargo compensation
    @AndroidFindBy(xpath = "(//androidx.cardview.widget.CardView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/card_view\"])[3]/android.view.ViewGroup")
    private WebElement buttonStandardCargoCompensation;

    // button to increase number of passengers
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/ivPlus\"])[1]")
    private WebElement buttonIncreasePassengers;

    // button to decrease number of passengers
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/ivMinus\"])[1]")
    private WebElement buttonDecreasePassengers;

    // button to increase number of carts
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/ivPlus\"])[2]")
    private WebElement buttonIncreaseCart;

    // button for goods longer than 6 ft
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Goods longer than 6 ft\"]")
    private WebElement buttonLonger6ft;

    // button for goods taller than 2 ft
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Goods taller than 2 ft\"]")
    private WebElement buttonTaller2ft;

    // button for pet-friendly driver
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Pet-Friendly Driver\"]")
    private WebElement buttonPetFriendly;

    // button for english-speaking driver
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"English-speaking driver\"]")
    private WebElement buttonEnglishSpeaking;

    // button for tunnel preference
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Tunnel preference\"]")
    private WebElement buttonTunnelPreference;

    // no preference button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"No preference\"]")
    private WebElement buttonNoTunnelPreference;

    // Cross-Harbour Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Cross-Harbour Tunnel\"]")
    private WebElement buttonCrossHarbourTunnel;

    // Eastern Harbour Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Eastern Harbour Crossing\"]")
    private WebElement buttonEastHarbourTunnel;

    // Western Harbour Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Western Harbour Crossing\"]")
    private WebElement buttonWestHarbourTunnel;

    // Lion rock Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Lion Rock Tunnel\"]")
    private WebElement buttonLionTunnel;

    // Tate's Cairn Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Tate's Cairn Tunnel\"]")
    private WebElement buttonTateTunnel;

    // Eagle's Nest Tunnel button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Eagle's Nest Tunnel\"]")
    private WebElement buttonEagleTunnel;

    // button for move-door-to-door
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Move door-to-door\"]")
    private WebElement buttonMoveDoorToDoor;

    // button for transport or dispose waste if no goods longer than 6
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/title\" and @text=\"Transport or dispose waste\"]")
    private WebElement buttonTransportWaste;


    // note to driver button
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/vNoteToDriver\"]/android.widget.ImageView[2]")
    private WebElement buttonNoteToDriver;

    // note to driver text box
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/vNoteToDriver\"]/android.widget.ImageView[2]")
    private WebElement noteToDriverTextBox;

    // add order contact info
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"ORDER_DETAIL_VC_YOUR_CONTACT_INFO\"]")
    private WebElement buttonAddContactInfo;

    // add order contact info - name
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[1]")
    private WebElement buttonAddContactInfoName;

    // add order contact info - phone number
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[2]")
    private WebElement buttonAddContactInfoNumber;

    // add order contact info - phone number extension
    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/inputView\"])[3]")
    private WebElement buttonAddContactInfoExtension;

    // add order contact info - save
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BOTTOM_SHEET_BOTTOM_BTN\"]")
    private WebElement buttonAddContactInfoSave;

    // the next button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/nextButton\"]")
    private WebElement buttonNext;


    // methods
    // select cargo compensation
    public boolean selectCargoCompensation() {
        try {
            String cargoOption = configLoader.getProperty("CARGO_COMPENSATION");
            if(cargoOption.equalsIgnoreCase("Included")){
                return true;
            }
            else if(cargoOption.equalsIgnoreCase("Basic")){
                WebElement buttonBasicCargoCompensationVisible = waitForVisibility(buttonBasicCargoCompensation);
                buttonBasicCargoCompensationVisible.click();
            }
            else if(cargoOption.equalsIgnoreCase("Standard")){
                WebElement buttonStandardCargoCompensationVisible = waitForVisibility(buttonStandardCargoCompensation);
                buttonStandardCargoCompensationVisible.click();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // set number of passengers
    public boolean selectNumberOfPassengers() {
        try {
            String noOfPassengers = configLoader.getProperty("NUMBER_OF_PASSENGERS");
            int passengerCount = Integer.parseInt(noOfPassengers);
            if(passengerCount == 2){
                return true;
            }
            if (passengerCount != 1){
                for (int i = 0; i < (passengerCount-2); i++){
                    WebElement buttonIncreasePassengersVisible = waitForVisibility(buttonIncreasePassengers);
                    buttonIncreasePassengersVisible.click();
                }
            }
            else if (passengerCount == 1){
                WebElement buttonDecreasePassengersVisible = waitForVisibility(buttonIncreasePassengers);
                buttonDecreasePassengersVisible.click();
                return true;
            }
            else if (passengerCount == 0){
                WebElement buttonDecreasePassengersVisible2 = waitForVisibility(buttonDecreasePassengers);
                buttonDecreasePassengersVisible2.click();
                WebElement buttonDecreasePassengersVisible3 = waitForVisibility(buttonDecreasePassengers);
                buttonDecreasePassengersVisible3.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // set number of cart
    public boolean selectNumberOfCart() {
        try {
            String noOfCart = configLoader.getProperty("RENT_A_CART");
            int cartCount = Integer.parseInt(noOfCart);
            if (cartCount == 0){
                return true;
            }
            else if (cartCount != 0){
                for (int i = 0; i < cartCount; i++){
                    WebElement buttonIncreaseCartVisible = waitForVisibility(buttonIncreaseCart);
                    buttonIncreaseCartVisible.click();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // select the other options
    // 1. goods longer than 6 ft
    // 2. pet friendly driver
    // 3. english-speaking driver
    public boolean selectOtherOptions() {
        try {
            String longerThan6 = configLoader.getProperty("GOODS_LONGER_THAN_6_FT");
            String tallerThan2 = configLoader.getProperty("GOODS_TALLER_THAN_2_FT");
            String petFriendly = configLoader.getProperty("PET_FRIENDLY");
            String englishSpeaking = configLoader.getProperty("ENGLISH_SPEAKING");
            if (longerThan6.equalsIgnoreCase("true")){
                WebElement buttonLonger6ftVisible = waitForVisibility(buttonLonger6ft);
                buttonLonger6ftVisible.click();
                System.out.println("Clicked on buttonLonger6ftVisible");
                if(tallerThan2.equalsIgnoreCase("true")){
                    WebElement buttonTaller2ftVisible = waitForVisibility(buttonTaller2ft);
                    buttonTaller2ftVisible.click();
                    System.out.println("Clicked on buttonTaller2ftVisible");
                }
            }
            if (petFriendly.equalsIgnoreCase("true")){
                WebElement buttonPetFriendlyVisible = waitForVisibility(buttonPetFriendly);
                buttonPetFriendlyVisible.click();
                System.out.println("Clicked on buttonPetFriendlyVisible");
            }
            if (englishSpeaking.equalsIgnoreCase("true")){
                WebElement buttonEnglishSpeakingVisible = waitForVisibility(buttonEnglishSpeaking);
                buttonEnglishSpeakingVisible.click();
                System.out.println("Clicked on buttonEnglishSpeakingVisible");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectTunnelPreference() {
        try {
            System.out.println("Entered the tunnel preference function");
            String tunnelFlag = configLoader.getProperty("TUNNEL_PREFERENCE");
            if (tunnelFlag.equalsIgnoreCase("false")) {
                return true;
            }
            System.out.println("it is not false");

            // Use By locators instead of WebElement
            System.out.println("going to check if visible");
            WebElement buttonTunnelPreferenceVisible = waitForVisibilityWithScroll(buttonTunnelPreference);
            System.out.println("Clicked tunnel prefeence");
            buttonTunnelPreferenceVisible.click();
            System.out.println("Clicked tunnel preference");

            String selectedTunnel = configLoader.getProperty("SELECTED_TUNNEL");
            switch (selectedTunnel) {
                case "No preference":
                    WebElement buttonNoTunnelPreferenceVisible = waitForVisibilityWithScroll(buttonNoTunnelPreference);
                    buttonNoTunnelPreferenceVisible.click();
                    break;

                case "Cross-Harbour Tunnel":
                    WebElement buttonCrossHarbourTunnelVisible = waitForVisibilityWithScroll(buttonCrossHarbourTunnel);
                    buttonCrossHarbourTunnelVisible.click();
                    break;

                case "Eastern Harbour Crossing":
                    WebElement buttonEastHarbourTunnelVisible = waitForVisibilityWithScroll(buttonEastHarbourTunnel);
                    buttonEastHarbourTunnelVisible.click();
                    break;

                case "Western Harbour Crossing":
                    WebElement buttonWestHarbourTunnelVisible = waitForVisibilityWithScroll(buttonWestHarbourTunnel);
                    buttonWestHarbourTunnelVisible.click();
                    break;

                case "Lion Rock Tunnel":
                    WebElement buttonLionTunnelVisible = waitForVisibilityWithScroll(buttonLionTunnel);
                    buttonLionTunnelVisible.click();
                    break;

                case "Tate's Cairn Tunnel":
                    WebElement buttonTateTunnelVisible = waitForVisibilityWithScroll(buttonTateTunnel);
                    buttonTateTunnelVisible.click();
                    break;

                case "Eagle's Nest Tunnel":
                    WebElement buttonEagleTunnelVisible = waitForVisibilityWithScroll(buttonEagleTunnel);
                    buttonEagleTunnelVisible.click();
                    break;

                default:
                    return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // select the quote options
    // 1. move door to door
    // 2. transport or dispose waste
    public boolean selectQuoteOptions() {
        try {
            String moveDoorToDoor = configLoader.getProperty("MOVE_DOOR_TO_DOOR");
            String transportWaste = configLoader.getProperty("TRANSPORT_WASTE");
            if (moveDoorToDoor.equalsIgnoreCase("false") && transportWaste.equalsIgnoreCase("false")) {
                return true;
            }
            if (moveDoorToDoor.equalsIgnoreCase("true")){
                WebElement moveDoorToDoorVisible = waitForVisibilityWithScroll(buttonMoveDoorToDoor);
                moveDoorToDoorVisible.click();
                System.out.println("Clicked on buttonMoveDoorToDoor");
            }
            if (transportWaste.equalsIgnoreCase("true")){
                WebElement transportWasteVisible = waitForVisibilityWithScroll(buttonTransportWaste);
                transportWasteVisible.click();
                System.out.println("Clicked on buttonTransportWaste");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // add contact information
    public boolean addContactInfo() {
        try {
            WebElement buttonAddContactInfoVisible = waitForVisibilityWithScroll(buttonAddContactInfo);
            buttonAddContactInfoVisible.click();
            System.out.println("clicked Add contact information");

            WebElement buttonAddContactInfoNameVisible = waitForVisibility(buttonAddContactInfoName);
            String userName = configLoader.getProperty("USER_NAME");
            buttonAddContactInfoName.clear();
            buttonAddContactInfoName.sendKeys(userName);
            WebElement buttonAddContactInfoNumberVisible = waitForVisibility(buttonAddContactInfoNumber);
            String userNumber = configLoader.getProperty("PHONE_NUMBER");
            buttonAddContactInfoNumber.clear();
            buttonAddContactInfoNumber.sendKeys(userNumber);
            String extensionFlag = configLoader.getProperty("EXTENSION_FLAG");
            if (extensionFlag.equalsIgnoreCase("true")){
                String userExtension = configLoader.getProperty("EXTENSION");
                buttonAddContactInfoExtension.clear();
                buttonAddContactInfoExtension.sendKeys(userExtension);
            }
            WebElement buttonAddContactInfoSaveVisible = waitForVisibility(buttonAddContactInfoSave);
            buttonAddContactInfoSaveVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error adding the contact info: " + e.getMessage());
            return false;
        }
    }

    // click on the review order button
    public boolean clickReviewOrder() {
        try {
            WebElement buttonNextVisible = waitForVisibility(buttonNext);
            buttonNextVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the review order button: " + e.getMessage());
            return false;
        }
    }



}
