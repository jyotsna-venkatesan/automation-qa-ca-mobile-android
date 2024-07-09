package org.DATests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.CATests.pageObjects.android.JavaScriptHelper;
import org.openqa.selenium.NoSuchElementException;
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

    // set up driver for all the page classes
    public AbstractPageClass(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    // Method to check if an element is visible and click it if it is
    public boolean clickIfVisible(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (visibleElement != null) {
                visibleElement.click();
                System.out.println("Clicked on the element: " + element.toString());
                return true;
            }
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            System.err.println("Error while trying to click the element: " + e.getMessage());
        }
        return false;
    }
}
