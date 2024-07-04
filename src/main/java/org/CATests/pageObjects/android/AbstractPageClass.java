package org.CATests.pageObjects.android;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;
import org.openqa.selenium.Dimension;


public class AbstractPageClass {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected JavaScriptHelper jsHelper;
    private int popupMenuHeight = 550;

    // set up driver for all the page classes
    public AbstractPageClass(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.jsHelper = new JavaScriptHelper(driver);
    }

    // send something to a web element but wait until it is visible
    public void sendString(WebElement element, String text) {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    // click on a web element but wait until it is visible
    public void clickElement(WebElement element) {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // wait until a web element is visible
    public WebElement waitForVisibility(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Method to wait for visibility with scrolling
    public WebElement waitForVisibilityWithScroll(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean canScrollMore = true;

        while (canScrollMore) {
            try {
                // Wait for the element to be visible
                System.out.println("Waiting for element to be visible");
                WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));

                // Get the dimensions of the visible area and the element
                int windowHeight = driver.manage().window().getSize().getHeight();
                int elementBottom = visibleElement.getLocation().getY() + visibleElement.getSize().getHeight();

                // Adjust window height by subtracting the popup menu height
                int adjustedWindowHeight = windowHeight - popupMenuHeight;

                // Check if the element is fully visible
                if (elementBottom <= adjustedWindowHeight) {
                    return visibleElement; // Element is fully visible
                } else {
                    System.out.println("Element is partially visible or covered by popup, scrolling down further");
                    jsHelper.scrollToElement("Review order");
                    System.out.println("returned from scrolldown");
                    WebElement visibleElement2 = wait.until(ExpectedConditions.visibilityOf(element));
                    return visibleElement2;
                }
            } catch (org.openqa.selenium.TimeoutException e) {
                // If the element is not visible within the timeout, scroll down using TouchAction
                System.out.println("Element not visible, scrolling down");
                jsHelper.scrollToElement("Review order");

                // Small delay after each scroll to allow UI to update
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted", ie);
                }
            } catch (Exception e) {
                // Handle other exceptions and break the loop if scrolling is no longer possible
                throw new RuntimeException("An error occurred while waiting for visibility or scrolling: " + e.getMessage());
            }
        }

        throw new RuntimeException("Element not found after scrolling");
    }
}