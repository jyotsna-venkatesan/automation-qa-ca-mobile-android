package org.CATests.pageObjects.android.delivery;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PackageInfoPage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // package content types
    // the button for food/drinks
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Food / Drinks\"]")
    private WebElement buttonFoodDrinks;

    // the button for flower/gifts
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Flowers / Gifts\"]")
    private WebElement buttonFlowerGifts;

    // the button for electronics
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Electronics\"]")
    private WebElement buttonElectronics;

    // the button for household
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Household\"]")
    private WebElement buttonHousehold;

    // the button for medication
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Medication\"]")
    private WebElement buttonMedication;

    // select size buttons
    // select size button
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"CONTACT_INFO_CELL_BTN\" and @text=\"Select size\"]")
    private WebElement buttonSelectSize;

    // select size 20 button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvSizeName\" and @text=\"≤ 20 × 20 × 10 cm\"]")
    private WebElement buttonSize20;

    // select size 30 button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvSizeName\" and @text=\"≤ 30 × 30 × 30 cm\"]")
    private WebElement buttonSize30;

    // select size 40
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvSizeName\" and @text=\"≤ 40 × 40 × 40 cm\"]")
    private WebElement buttonSize40;

    // select size 50
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvSizeName\" and @text=\"≤ 50 × 50 × 50 cm\"]")
    private WebElement buttonSize50;

    // select size 60
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvSizeName\" and @text=\"≤ 60 × 60 × 60 cm\"]")
    private WebElement buttonSize60;

    // select weight buttons
    // select weight button
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"CONTACT_INFO_CELL_BTN\" and @text=\"Select weight\"]")
    private WebElement buttonSelectWeight;

    // select weight 5
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvWeightName\" and @text=\"≤ 5 kg\"]")
    private WebElement buttonWeight5;

    // select weight 10
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvWeightName\" and @text=\"≤ 10 kg\"]")
    private WebElement buttonWeight10;

    // select weight 15
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvWeightName\" and @text=\"≤ 15 kg\"]")
    private WebElement buttonWeight15;

    // select weight 20
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvWeightName\" and @text=\"≤ 20 kg\"]")
    private WebElement buttonWeight20;

    // button for review order
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"ggv__order_detail__footer__button__review_order\"]")
    private WebElement buttonNext;

    // select a payment method button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvPaymentTypeTitle\"]")
    private WebElement buttonPaymentMethod;

    // button for cash
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[1]")
    private WebElement buttonCash;

    // button for fps
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[2]")
    private WebElement buttonFps;

    // button for recipient
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[3]")
    private WebElement buttonRecipient;

    // button for No tip
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"No tip\"]")
    private WebElement buttonNoTip;

    // button for $20 tip
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"$20\"])[1]")
    private WebElement button20Tip;

    // button for $30 tip
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$30\"]")
    private WebElement button30Tip;

    // button for $40 tip
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$40\"]")
    private WebElement button40Tip;

    // button for $50 tip
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$50\"]")
    private WebElement button50Tip;

    // place order button
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"ggv__order_summary__footer__button__place_order\"]")
    private WebElement buttonPlaceOrder;

    // set up the driver for this page
    public PackageInfoPage(AndroidDriver driver){
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // methods:
    // chose the package content type
    public boolean choosePackageContent() {
        try {
            String contentType = configLoader.getProperty("CONTENT_TYPE");
            if (contentType.equalsIgnoreCase("Food Drinks")){
                return true;
            }
            else if (contentType.equalsIgnoreCase("Flowers Gifts")){
                WebElement buttonFlowersVisible = waitForVisibility(buttonFlowerGifts);
                buttonFlowersVisible.click();
                return true;
            }
            else if (contentType.equalsIgnoreCase("Electronics")){
                WebElement buttonElectronicsVisible = waitForVisibility(buttonElectronics);
                buttonElectronicsVisible.click();
                return true;
            }
            else if (contentType.equalsIgnoreCase("Household")){
                WebElement buttonHouseholdVisible = waitForVisibility(buttonHousehold);
                buttonHouseholdVisible.click();
                return true;
            }
            else if (contentType.equalsIgnoreCase("Medication")){
                WebElement buttonMedicationVisible = waitForVisibility(buttonMedication);
                buttonMedicationVisible.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting the package size: " + e.getMessage());
            return false;
        }
    }

    // select package size
    public boolean selectSize() {
        try {
            WebElement buttonSizeVisible = waitForVisibility(buttonSelectSize);
            buttonSizeVisible.click();
            String contentSize = configLoader.getProperty("SIZE");
            if (contentSize.equalsIgnoreCase("20")){
                WebElement button20Visible = waitForVisibility(buttonSize20);
                button20Visible.click();
                return true;
            }
            else if (contentSize.equalsIgnoreCase("30")){
                WebElement button30Visible = waitForVisibility(buttonSize30);
                button30Visible.click();
                return true;
            }
            else if (contentSize.equalsIgnoreCase("40")){
                WebElement button40Visible = waitForVisibility(buttonSize40);
                button40Visible.click();
                return true;
            }
            else if (contentSize.equalsIgnoreCase("50")){
                WebElement button50Visible = waitForVisibility(buttonSize50);
                button50Visible.click();
                return true;
            }
            else if (contentSize.equalsIgnoreCase("60")){
                WebElement button60Visible = waitForVisibility(buttonSize60);
                button60Visible.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting the package weight: " + e.getMessage());
            return false;
        }
    }

    // chose the package content type
    public boolean selectWeight() {
        try {
            WebElement buttonWeightVisible = waitForVisibility(buttonSelectWeight);
            buttonWeightVisible.click();
            String contentWeight = configLoader.getProperty("WEIGHT");
            if (contentWeight.equalsIgnoreCase("5")){
                WebElement button5Visible = waitForVisibility(buttonWeight5);
                button5Visible.click();
                return true;
            }
            else if (contentWeight.equalsIgnoreCase("10")){
                WebElement button10Visible = waitForVisibility(buttonWeight10);
                button10Visible.click();
                return true;
            }
            else if (contentWeight.equalsIgnoreCase("15")){
                WebElement button15Visible = waitForVisibility(buttonWeight15);
                button15Visible.click();
                return true;
            }
            else if (contentWeight.equalsIgnoreCase("20")){
                WebElement button20Visible = waitForVisibility(buttonWeight20);
                button20Visible.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error inputting the estate number: " + e.getMessage());
            return false;
        }
    }

    // click next
    public boolean selectPaymentMethod() {
        try {

            WebElement buttonPaymentVisible = waitForVisibility(buttonPaymentMethod);
            buttonPaymentVisible.click();
            String paymentMethod = configLoader.getProperty("PAYMENT_METHOD");
            if(paymentMethod.equalsIgnoreCase("Cash")){
                WebElement buttonCashVisible = waitForVisibility(buttonCash);
                buttonCashVisible.click();
                return true;
            }
            else if(paymentMethod.equalsIgnoreCase("Pay by recipient")){
                WebElement buttonRecipientVisible = waitForVisibility(buttonRecipient);
                buttonRecipientVisible.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting payment method " + e.getMessage());
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

    // function to select the tip
    public boolean selectTip() {
        try {
            String tipAmount = configLoader.getProperty("TIP");
            if(tipAmount.equalsIgnoreCase("No tip")){
                return true;
            }
            else if(tipAmount.equalsIgnoreCase("20")){
                WebElement button20TipVisible = waitForVisibility(button20Tip);
                button20TipVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("30")){
                WebElement button30TipVisible = waitForVisibility(button30Tip);
                button30TipVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("40")){
                WebElement button40TipVisible = waitForVisibility(button40Tip);
                button40TipVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("50")){
                WebElement button50TipVisible = waitForVisibility(button50Tip);
                button50TipVisible.click();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // click next
    public boolean placeOrder() {
        try {

            WebElement buttonPlaceOrderVisible = waitForVisibility(buttonPlaceOrder);
            buttonPlaceOrderVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error placing order " + e.getMessage());
            return false;
        }
    }



}
