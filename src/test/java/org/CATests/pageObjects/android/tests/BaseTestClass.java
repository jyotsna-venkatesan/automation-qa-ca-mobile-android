package org.CATests.pageObjects.android.tests;

import io.appium.java_client.android.AndroidDriver;
import org.CATests.pageObjects.android.tests.transport.*;
import org.CATests.utils.*;
import org.DATests.pageObjects.android.tests.DABaseTestClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// testNG imports
import org.testng.annotations.*;

// import the pages to automate it


public class BaseTestClass {

    private static final Logger logger = LogManager.getLogger(BaseTestClass.class);
    static AndroidDriver driver;
    private ConfigLoader configLoader;
    private static final String EXCEL_FILE_PATH = "src/main/resources/testdata.xlsx";
    private ExtentReports extent;
    private ExtentTest test;
    public static String globalOrderID;

    @BeforeSuite
    public void setUpExtent() {
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }

    @DataProvider(name = "testData")
    public Object[][] testData() {
        int totalRows = ExcelReader.getTotalRows(EXCEL_FILE_PATH);
        Object[][] data = new Object[totalRows][];

        for (int i = 1; i <= totalRows; i++) {
            data[i - 1] = new Object[]{ExcelReader.getTestData(EXCEL_FILE_PATH, i)};
        }
        return data;
    }

    @BeforeMethod
    @Parameters("rowNumber")
    public void setup(@Optional("1") int rowNumber) throws MalformedURLException {
        Map<String, String> testData = ExcelReader.getTestData(EXCEL_FILE_PATH, rowNumber);
        ConfigUpdater.updateConfig(testData);

        // Initialize the class-level configLoader
        configLoader = new ConfigLoader();
        configLoader.reload();

        // Create a new test node in the report
        test = extent.createTest("Test Case: " + rowNumber);
    }

    private void updateConfig(Map<String, String> testData) {
        ConfigUpdater.updateConfig(testData);
    }


    @Test(dataProvider = "testData")
    public void runTests(Map<String, String> testData) throws MalformedURLException {
        // Update config with the test data
        updateConfig(testData);

        // Open the app
        openMobileApp();

        try {

            // Execute tests in sequence for the given testData
            System.out.println("Starting testHomePage");
            testHomePage(testData);
            System.out.println("Completed testHomePage");

            System.out.println("Starting testSideBarPage");
            testSideBarPage(testData);
            System.out.println("Completed testSideBarPage");

            System.out.println("Starting testLoginPage");
            testLoginPage(testData);
            System.out.println("Completed testLoginPage");

            System.out.println("Starting testAddressPage");
            testAddressPage(testData);
            System.out.println("Completed testAddressPage");

            System.out.println("Starting testTimeAndVehiclePage");
            testTimeAndVehiclePage(testData);
            System.out.println("Completed testTimeAndVehiclePage");

            System.out.println("Starting testOrderDetailsPage");
            testOrderDetailsPage(testData);
            System.out.println("Completed testOrderDetailsPage");

            System.out.println("Starting testOrderSummaryPage");
            testOrderSummaryPage(testData);
            System.out.println("Completed testOrderSummaryPage");

            System.out.println("Starting testPlacedOrderPage");
            testPlacedOrderPage(testData);  // Ensure this method is called
            System.out.println("Completed testPlacedOrderPage");

            // Reload properties to ensure the latest value is read
            configLoader.reload();
            String cancelFlag = configLoader.getProperty("CANCEL_FLAG");
            System.out.println("The cancel flag is " + cancelFlag);

            // Check the Cancel_Flag before running DA tests
            if (cancelFlag.equalsIgnoreCase("false")) {
                // Run tests from org.DATests.pageObjects.android.tests.BaseTestClass
                DABaseTestClass daBaseTest = new DABaseTestClass(driver, extent, test);
                daBaseTest.runTestsWithOrderID(GlobalState.globalOrderID);
            }

            // Mark the test as passed
            test.pass("Test passed successfully!");

        } catch (Exception e) {
            // Mark the test as failed
            test.fail("Test failed: " + e.getMessage());
        } finally {
            // Close the app and switch back to the previous app
            closeDriverApp();
        }
    }

    private void openMobileApp() throws MalformedURLException {
        // set the capabilities for the application on android
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:deviceName", "Galaxy M34 5G");
        cap.setCapability("appium:udid", "RFCW91F97TF");
        cap.setCapability("appium:platformName", "Android");
        cap.setCapability("appium:platformVersion", "14");
        cap.setCapability("appium:automationName", "uiAutomator2");
        cap.setCapability("appium:newCommandTimeout", 600); // Set timeout to 600 seconds (10 minutes)
        cap.setCapability("appium:appPackage", "hk.gogovan.GoGoVanClient2.staging");
        cap.setCapability("appium:appActivity", "hk.gogovan.clientapp.RootActivity");


        URL url = new URL("http://127.0.0.1:4723/");
        driver = new AndroidDriver(url, cap);

        System.out.println("Application started!");

        try {
            // Wait for 3 seconds before starting the test
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeDriverApp() {
        if (driver != null) {
            try {
                // Send the driver app to the background
                driver.runAppInBackground(Duration.ofSeconds(-1));

                // Wait for a short period to ensure the app is in the background
                Thread.sleep(2000);

                // Bring the customer app to the foreground
                driver.activateApp("hk.gogovan.GoGoVanClient2.staging");
                System.out.println("Switched back to the customer app.");
            } catch (NoSuchSessionException e) {
                System.err.println("Session is terminated or not started: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error while switching apps: " + e.getMessage());
            } finally {
                try {
                    // Quit the driver to end the session
                    driver.quit();
                    System.out.println("Driver session ended.");
                } catch (Exception e) {
                    System.err.println("Error while quitting the driver: " + e.getMessage());
                }
            }
        }
    }

    private void testHomePage(Map<String, String> testData) {
        HomePageTest homePageTest = new HomePageTest(driver);
        homePageTest.testAutomateTheHomePage();
    }

    private void testSideBarPage(Map<String, String> testData) {
        SideBarTest sideBarPageTest = new SideBarTest(driver);
        sideBarPageTest.testAutomateTheSideBarPage();
    }

    private void testLoginPage(Map<String, String> testData) {
        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.testAutomateTheLoginPage();
    }

    private void testAddressPage(Map<String, String> testData) {
        AddressPageTest addressPageTest = new AddressPageTest(driver);
        addressPageTest.testAutomateTheAddressPage();
    }

    private void testTimeAndVehiclePage(Map<String, String> testData) {
        TimeAndVehiclePageTest timeAndVehiclePageTest = new TimeAndVehiclePageTest(driver);
        timeAndVehiclePageTest.testAutomateTheTimeAndVehiclePage();
    }

    private void testOrderDetailsPage(Map<String, String> testData) {
        OrderDetailsPageTest orderDetailsPageTest = new OrderDetailsPageTest(driver);
        orderDetailsPageTest.testAutomateTheOrderDetailsPage();
    }

    private void testOrderSummaryPage(Map<String, String> testData) {
        OrderSummaryPageTest orderSummaryPageTest = new OrderSummaryPageTest(driver);
        orderSummaryPageTest.testAutomateTheOrderSummaryPage();
    }

    private void testPlacedOrderPage(Map<String, String> testData) {
        PlacedOrderPageTest placedOrderPageTest = new PlacedOrderPageTest(driver);
        placedOrderPageTest.testAutomateThePlacedOrderPage();
    }

    private void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed.");
        }
    }
}