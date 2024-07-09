package org.DATests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.By;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.CATests.utils.GlobalState.globalOrderID;
import static org.CATests.utils.GlobalState.globalPickUpCode;

public class LandingDeliveryPage extends AbstractPageClass{

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // set up the driver for this page
    public LandingDeliveryPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    // buttons:
    // button to switch to delivery
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/item_segment_tv\" and @text=\"Delivery\"]")
    private WebElement buttonSwitchDelivery;

    // button to click on first order

    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView/android.widget.FrameLayout/android.view.ViewGroup")
    private WebElement buttonFirstOrder;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/recyclerView\"]")
    private WebElement buttonOrderCards;

    // button to confirm pick order after swipe
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Confirm pickup\"]")
    private WebElement buttonPickOrderAfterSwipe;

    // code inputs
    // 1st digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[1]")
    private WebElement buttonCode1;

    // 2nd digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[2]")
    private WebElement buttonCode2;

    // 3rd digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[3]")
    private WebElement buttonCode3;

    // 4th digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[4]")
    private WebElement buttonCode4;

    // 5th digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[5]")
    private WebElement buttonCode5;

    // 6th digit
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/referenceCodeInput\"]/android.widget.EditText[6]")
    private WebElement buttonCode6;

    // submit code
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Submit\"]")
    private WebElement buttonCodeSubmit;

    // confirm delivery
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/confirmDeliveryButton\"]")
    private WebElement buttonConfirmDelivery;

    // camera button
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Add\"])[1]")
    private WebElement buttonCamera;

    // camera button permissions allow
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    private WebElement buttonCameraAllow;

    // camera button click
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"hk.gogovan.GoGoDriver.staging:id/take_photo\"]")
    private WebElement buttonCameraClick;

    // camera button submit
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Submit\"]")
    private WebElement buttonCameraSubmit;

    // complete order final
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Complete Order\"]")
    private WebElement buttonCompleteOrderFinal;

    // navigate back button
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Complete Order\"]")
    private WebElement buttonNavigateBack;




    // functions:
    // switch to delivery
    public boolean switchToDelivery() {
        try {
            WebElement buttonDeliveryVisible = waitForVisibility(buttonSwitchDelivery);
            buttonDeliveryVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error switching to delivery : " + e.getMessage());
            return false;
        }
    }


    // Click on the first order
    public boolean clickFirstOrder() {
        try {
            WebElement buttonDeliveryVisible = waitForVisibility(buttonFirstOrder);
            buttonDeliveryVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking first order : " + e.getMessage());
            return false;
        }
    }
    public boolean clickFirstOrder2() {
        try {
            System.out.println("Locating RecyclerView");
            By recyclerViewLocator = By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='hk.gogovan.GoGoDriver.staging:id/recyclerView']");
            WebElement recyclerView = wait.until(ExpectedConditions.visibilityOfElementLocated(recyclerViewLocator));

            System.out.println("Locating cards within the RecyclerView");
            List<WebElement> cards = recyclerView.findElements(By.xpath("./android.widget.RelativeLayout"));
            System.out.println("Number of cards found: " + cards.size());

            for (WebElement card : cards) {
                System.out.println("Clicking on a card");
                card.click();

                // Adding a short sleep to wait for the next page to load
                Thread.sleep(5000);

                // Construct the XPath to match the order ID starting with '#' and ending with the last three digits of globalOrderID
                String lastThreeDigits = globalOrderID.substring(globalOrderID.length() - 3);
                String orderIdPattern = String.format("#%s", lastThreeDigits);
                String orderIdXpath = String.format("//android.widget.TextView[contains(@text, '%s')]", orderIdPattern);
                By orderIdLocator = By.xpath(orderIdXpath);

                System.out.println("Waiting for the order ID element to be visible using XPath: " + orderIdXpath);
                WebElement orderIdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderIdLocator));

                if (orderIdElement != null) {
                    String orderIdText = orderIdElement.getText();
                    System.out.println("Order ID element found with text: " + orderIdText);
                    if (orderIdText.startsWith("#") && orderIdText.endsWith(lastThreeDigits)) {
                        System.out.println("Order ID matches the pattern");
                        return true; // Order ID matches the pattern
                    } else {
                        System.out.println("Order ID does not match the pattern, navigating back");
                        // Go back to the previous screen
                        buttonNavigateBack = waitForVisibility(buttonNavigateBack);
                        buttonNavigateBack.click();

                        // Re-locate the RecyclerView after navigating back
                        System.out.println("Re-locating RecyclerView after navigating back");
                        recyclerView = wait.until(ExpectedConditions.visibilityOfElementLocated(recyclerViewLocator));
                        cards = recyclerView.findElements(By.xpath("./android.widget.RelativeLayout"));
                    }
                } else {
                    System.out.println("Order ID element not found");
                    return false;
                }
            }

            System.out.println("No matching order ID found");
            return false;

        } catch (Exception e) {
            System.out.println("Error clicking first order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean pickOrder() {
        try {
            // Calculate start and end coordinates for the swipe
            int startX = 43;
            int startY = 2101;
            int endX = 1074;
            int endY = 2101;

            // Create a new PointerInput instance for touch actions
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Perform the swipe action
            driver.perform(Arrays.asList(swipe));
            Thread.sleep(5000);

            return true;
        } catch (Exception e) {
            System.out.println("Error clicking picking order with swipe: " + e.getMessage());
            return false;
        }
    }

    // complete order
    public boolean completeOrder() {
        try {
            WebElement buttonCompleteOrderAfterSwipeVisible = waitForVisibility(buttonPickOrderAfterSwipe);
            buttonCompleteOrderAfterSwipeVisible.click();

            System.out.println("Complete Order");

            String globalCode = globalPickUpCode;

            // Array of input elements
            WebElement[] inputs = {buttonCode1, buttonCode2, buttonCode3, buttonCode4, buttonCode5, buttonCode6};

            // Iterate over the characters in the global code and input them into the fields
            for (int i = 0; i < globalCode.length(); i++) {
                inputs[i].sendKeys(String.valueOf(globalCode.charAt(i)));
            }

            System.out.println("entered code");

            WebElement buttonCodeSubmitVisible = waitForVisibility(buttonCodeSubmit);
            buttonCodeSubmitVisible.click();

            System.out.println("submitted code");

            boolean clickConfirmDelivery = clickIfVisible(buttonConfirmDelivery, 3);

            System.out.println("confirmed delivery");

            WebElement buttonCameraVisible = waitForVisibility(buttonCamera);
            buttonCameraVisible.click();

            System.out.println("clicked on camera");

            boolean clickbuttonPictureAllowExpiry = clickIfVisible(buttonCameraAllow, 3);

            System.out.println("checked for permission allow");

            WebElement buttonCameraClickVisible = waitForVisibility(buttonCameraClick);
            buttonCameraClickVisible.click();

            System.out.println("clicked the picture");

            WebElement buttonCameraSubmitVisible = waitForVisibility(buttonCameraSubmit);
            buttonCameraSubmitVisible.click();

            System.out.println("submitted the picture");


            WebElement buttonCompleteOrderFinalVisible = waitForVisibility(buttonCompleteOrderFinal);
            buttonCompleteOrderFinalVisible.click();

            System.out.println("completed the order");



            return true;
        } catch (Exception e) {
            System.out.println("Error clicking first order: " + e.getMessage());
            return false;
        }
    }

}
