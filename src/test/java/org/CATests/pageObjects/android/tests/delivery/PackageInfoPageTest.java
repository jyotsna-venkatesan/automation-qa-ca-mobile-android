package org.CATests.pageObjects.android.tests.delivery;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.delivery.PackageInfoPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PackageInfoPageTest {

    private AndroidDriver driver;

    public PackageInfoPageTest(AndroidDriver driver) {
        this.driver = driver;
    }

    // function to automate the package info page
    @Test
    public void testAutomateThePackageInfoPage() {
        PackageInfoPage packageInfoPage = new PackageInfoPage(driver);

        System.out.println("-- Package Info Page --");

        // select package content type
        boolean isContentType = packageInfoPage.choosePackageContent();
        assertTrue(isContentType, "Failed to select the content type.");
        System.out.println("Select Package Content Type: Success");

        // select package content size
        boolean isSize = packageInfoPage.selectSize();
        assertTrue(isSize, "Failed to select size.");
        System.out.println("Select size: Success");

        // select package content size
        boolean isWeight = packageInfoPage.selectWeight();
        assertTrue(isWeight, "Failed to select weight.");
        System.out.println("Select weight: Success");

        // select payment method
        boolean isPaymentMethodSelected = packageInfoPage.selectPaymentMethod();
        assertTrue(isPaymentMethodSelected, "Failed to select payment method.");
        System.out.println("Select payment method: Success");

        // click next
        boolean isNextClicked = packageInfoPage.clickNext();
        assertTrue(isNextClicked, "Failed to click next.");
        System.out.println("Click next: Success");

        // select tip
        boolean isTipClicked = packageInfoPage.selectTip();
        assertTrue(isTipClicked, "Failed to select tip.");
        System.out.println("Select tip: Success");

        // place order
        boolean isOrderPlaced = packageInfoPage.placeOrder();
        assertTrue(isOrderPlaced, "Failed to place order.");
        System.out.println("Place Order: Success");
    }
}
