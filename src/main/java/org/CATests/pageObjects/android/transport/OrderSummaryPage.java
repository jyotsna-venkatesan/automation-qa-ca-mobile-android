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

public class OrderSummaryPage extends AbstractPageClass{

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // call the javascript helper to scroll
    protected JavaScriptHelper jsHelper;
    public OrderSummaryPage(AndroidDriver driver) {
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.jsHelper = new JavaScriptHelper(driver);
    }

    // buttons:
    // the account and payment methods button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvPaymentTypeTitle\"]")
    private WebElement buttonAccountAndPaymentMethods;

    // button for card
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,\"paymentMethodsView\")]//android.widget.LinearLayout/android.widget.TextView[contains(@text,\"{payment_method_visa}\")]")
    private WebElement buttonCard;

    // button for cash
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[1]")
    private WebElement buttonCash;

    // button for fps
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[2]")
    private WebElement buttonFps;

    // button for recipient
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/paymentMethodsView\"]/android.widget.LinearLayout[3]")
    private WebElement buttonRecipient;

    // button for wallet
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,\"paymentMethodsView\")]//android.widget.LinearLayout/android.widget.TextView[contains(@text,\"{payment_method_wallet}\")]")
    private WebElement buttonWallet;

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

    // button for coupon
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_caption\" and @text=\"Apply\"]")
    private WebElement buttonCoupon;

    // navigation back button
    @AndroidFindBy(xpath = "(//android.widget.ImageButton[@content-desc=\"NAVIGATION_BUTTON\"])[2]")
    private WebElement buttonNavigation;

    // place order button
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"ggv__time_vehicle__bottom_sheet__button__next\"]")
    private WebElement buttonPlaceOrder;




    // methods:
    // function to select the tip
    public boolean selectTip() {
        try {
            String tipAmount = configLoader.getProperty("TIP");
            if(tipAmount.equals("No tip")){
                WebElement buttonNoTipVisible = waitForVisibility(buttonNoTip);
                buttonNoTipVisible.click();
            }
            else if(tipAmount.equals("20")){
                WebElement button20TipVisible = waitForVisibility(button20Tip);
                button20TipVisible.click();
            }
            else if(tipAmount.equals("30")){
                WebElement button30TipVisible = waitForVisibility(button30Tip);
                button30TipVisible.click();
            }
            else if(tipAmount.equals("40")){
                WebElement button40TipVisible = waitForVisibility(button40Tip);
                button40TipVisible.click();
            }
            else if(tipAmount.equals("50")){
                WebElement button50TipVisible = waitForVisibility(button50Tip);
                button50TipVisible.click();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // function to select the tip
    public boolean applyCoupon() {
        try {
            WebElement buttonCouponVisible = waitForVisibility(buttonCoupon);
            buttonCouponVisible.click();
            WebElement buttonNavigationVisible = waitForVisibility(buttonNavigation);
            buttonNavigationVisible.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // function to select the account and payment method
    public boolean selectPaymentMethod() {
        try {
            WebElement buttonAccountAndPaymentMethodsVisible = waitForVisibility(buttonAccountAndPaymentMethods);
            buttonAccountAndPaymentMethodsVisible.click();
            String paymentMethod = configLoader.getProperty("PAYMENT_METHOD");
            String accountType = configLoader.getProperty("EMAIL_OR_PHONENUMBER");
            if(paymentMethod.equals("Cash")){
                WebElement buttonCashVisible = waitForVisibility(buttonCash);
                buttonCashVisible.click();
            }
            else if(paymentMethod.equals("FPS")){
                WebElement buttonFpsVisible = waitForVisibility(buttonFps);
                buttonFpsVisible.click();
            }
            else if(paymentMethod.equals("Pay by recipient")){
                WebElement buttonRecipientVisible = waitForVisibility(buttonRecipient);
                buttonRecipientVisible.click();
            }
            else if(paymentMethod.equals("Wallet")){
                WebElement buttonWalletVisible = waitForVisibility(buttonWallet);
                buttonWalletVisible.click();
            } else if(paymentMethod.equals("Card")){
                if(accountType.equals("qadmaster1@gogotech.hk")){
                    WebElement buttonCashVisible = waitForVisibility(buttonCash);
                    buttonCashVisible.click();
                }
            }


            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // function to place order
    public boolean placeOrder() {
        try {
            WebElement buttonPlaceOrderVisible = waitForVisibility(buttonPlaceOrder);
            buttonPlaceOrderVisible.click();
            Thread.sleep(5000);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
