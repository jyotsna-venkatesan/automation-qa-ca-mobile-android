package org.CATests.pageObjects.android.transport;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class TimeAndVehiclePage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // buttons:
    // date and time button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvDateTime\"]")
    private WebElement buttonDateTime;

    // the specific date that we want
    private WebElement buttonSelectedDate;

    // the first next date
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[1]/android.widget.LinearLayout/*[3]")
    private WebElement firstNextDate;

    // the second next date
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[1]/android.widget.LinearLayout/*[4]")
    private WebElement secondNextDate;

    // the next date if scroll is needed
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[1]/android.widget.LinearLayout/*[5]")
    private WebElement nextDateIfScroll;

    // the first next time
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[2]/android.widget.LinearLayout/*[3]")
    private WebElement firstNextTime;

    // the second next time
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[2]/android.widget.LinearLayout/*[4]")
    private WebElement secondNextTime;

    // the next time if scroll is needed
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"])[2]/android.widget.LinearLayout/*[5]")
    private WebElement nextTimeIfScroll;

    // the ok button
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BOTTOM_SHEET_BOTTOM_BTN\"]")
    private WebElement buttonOK;

    // the hourly rental button
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Hourly rental\"]")
    private WebElement buttonHourlyRentalTime;

    // the first hourly rental next time
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"]/android.widget.LinearLayout/*[3]")
    private WebElement firstNextHourlyRentalTime;

    // the second hourly rental next time
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"]/android.widget.LinearLayout/*[4]")
    private WebElement secondNextHourlyRentalTime;

    // the next hourly rental time if scroll is needed
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/scrollView\"]/android.widget.LinearLayout/*[5]")
    private WebElement nextHourlyRentalTimeIfScroll;

    // button for van
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvVehicleTypeName\" and @text=\"Van\"]")
    private WebElement buttonVan;

    // button for premium van
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvVehicleTypeName\" and @text=\"Premium Van\"]")
    private WebElement buttonPremiumVan;

    // button for 5.5t truck
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tvVehicleTypeName\" and @text=\"5.5t Truck\"]")
    private WebElement button55Truck;

    // button for 9t truck
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"ggv__time_vehicle__bottom_sheet__medal__truck_9t\"]/android.view.ViewGroup")
    private WebElement button9Truck;

    // the next button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/nextButton\"]")
    private WebElement buttonNext;


    // set up the driver for this page
    public TimeAndVehiclePage(AndroidDriver driver) {
        super(driver);
        configLoader = new ConfigLoader();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // methods:
    // click on the date and time button
    public boolean clickDateAndTime() {
        try {
            String selectedTime = configLoader.getProperty("TIME").replaceAll("[^a-zA-Z0-9: ]", "").trim();
            if ("ASAP".equalsIgnoreCase(selectedTime)) {
                return true;
            }
            WebElement buttonDateTimeVisible = waitForVisibility(buttonDateTime);
            buttonDateTimeVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the date and time button: " + e.getMessage());
            return false;
        }
    }

    // select the date
    public boolean clickSelectedDate() {
        try{
            String selectedTime = configLoader.getProperty("TIME").replaceAll("[^a-zA-Z0-9: ]", "").trim();
            if ("ASAP".equalsIgnoreCase(selectedTime)) {
                return true;
            }

            // wait for the dates to show up, i can't find a workaround for this
            Thread.sleep(300);

            // calculate the number of days between the current day and the day of the order
            String endDateStr = configLoader.getProperty("DATE_OF_ORDER");
            String startDateStr = configLoader.getProperty("CURRENT_DATE");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            int currentYear = LocalDate.now().getYear();
            if (startDateStr.equals("Today")) {
                startDateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else if (startDateStr.equals("Tomorrow")) {
                startDateStr = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else {
                startDateStr = startDateStr.replaceAll(".*?(\\d{1,2} \\w{3}).*", "$1") + " " + currentYear;
            }
            if (endDateStr.equals("Today")) {
                endDateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else if (endDateStr.equals("Tomorrow")) {
                endDateStr = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else {
                endDateStr = endDateStr.replaceAll(".*?(\\d{1,2} \\w{3}).*", "$1") + " " + currentYear;
            }
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            if (daysBetween == 0){ // same day order, no need to change
                return true;
            }

            for (int i = 0; i < daysBetween; i++){
                if (i == 0){
                    WebElement firstNextDateVisible = waitForVisibility(firstNextDate);
                    firstNextDate.click();
                }
                if (i == 1){
                    WebElement secondNextDateVisible = waitForVisibility(secondNextDate);
                    secondNextDate.click();
                }
                else{
                    WebElement nextDateIfScrollVisible = waitForVisibility(nextDateIfScroll);
                    nextDateIfScrollVisible.click();
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error clicking the button of the selected date: " + e.getMessage());
            return false;
        }
    }

    // click the selected time
    public boolean clickSelectedTime() {
        try {
            String selectedTime = configLoader.getProperty("TIME").replaceAll("[^a-zA-Z0-9: ]", "").trim();
            if ("ASAP".equalsIgnoreCase(selectedTime)) {
                return true;
            }

            int flag = 0;
            for (int i = 0; i < 120; i++) { // 120 because that is the maximum number of elements for this
                WebElement timeElement;
                if (i == 0) {
                    timeElement = waitForVisibility(firstNextTime);
                } else if (i == 1) {
                    timeElement = waitForVisibility(secondNextTime);
                } else {
                    timeElement = waitForVisibility(nextTimeIfScroll);
                }

                String timeText = timeElement.getText().replaceAll("[^a-zA-Z0-9: ]", "").trim();
                timeElement.click();

                if (timeText.equalsIgnoreCase(selectedTime)) {
                    // Click again to confirm selection
                    waitForVisibility(timeElement).click();
                    flag = 1;
                    break;
                }
            }

            return flag == 1;

        } catch (Exception e) {
            System.out.println("Error clicking the button of the selected date: " + e.getMessage());
            return false;
        }
    }

    // click ok for pick-up time for the date
    public boolean clickOKDate() {
        try {
            String selectedTime = configLoader.getProperty("TIME").replaceAll("[^a-zA-Z0-9: ]", "").trim();
            if ("ASAP".equalsIgnoreCase(selectedTime)) {
                return true;
            }
            buttonOK.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the ok button for pick-up time: " + e.getMessage());
            return false;
        }
    }

    // click ok for pick-up time for the rental
    public boolean clickOKRental() {
        try {
            String hourlyRentalTime = configLoader.getProperty("HOURLY_RENTAL");
            if (hourlyRentalTime.equalsIgnoreCase("No hourly rental")) {
                return true;
            }
            buttonOK.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the ok button for rental: " + e.getMessage());
            return false;
        }
    }

    // click on the hourly rental button
    public boolean clickHourlyRental() {
        try {
            String hourlyRentalTime = configLoader.getProperty("HOURLY_RENTAL");
            if (hourlyRentalTime.equalsIgnoreCase("No hourly rental")) {
                return true;
            }
            WebElement buttonHourlyRentalVisible = waitForVisibility(buttonHourlyRentalTime);
            buttonHourlyRentalVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the hourly rental button: " + e.getMessage());
            return false;
        }
    }

    // click the selected hourly rental time
    public boolean clickSelectedHourlyRentalTime(){
        try {
            String hourlyRentalTime = configLoader.getProperty("HOURLY_RENTAL");
            if (hourlyRentalTime.equalsIgnoreCase("No hourly rental")) {
                return true;
            }
            int flag = 0;

            for (int i = 0; i < 18; i++) { // 18 because that is the maximum number of elements for this
                if (i == 0) {
                    WebElement firstNextHourlyRentalTimeVisible = waitForVisibility(firstNextHourlyRentalTime);
                    String j = firstNextHourlyRentalTimeVisible.getText();
                    firstNextHourlyRentalTimeVisible.click();
                    if (j.equalsIgnoreCase(hourlyRentalTime)) {
                        WebElement firstNextHourlyRentalTimeVisible2 = waitForVisibility(firstNextHourlyRentalTime);
                        firstNextHourlyRentalTimeVisible2.click();
                        flag = 1;
                        break;
                    }
                }
                else if (i == 1) {
                    WebElement secondNextHourlyRentalTimeVisible = waitForVisibility(secondNextHourlyRentalTime);
                    String j = secondNextHourlyRentalTimeVisible.getText();
                    secondNextHourlyRentalTimeVisible.click();
                    if (j.equalsIgnoreCase(hourlyRentalTime)) {
                        WebElement secondNextHourlyRentalTimeVisible2 = waitForVisibility(secondNextHourlyRentalTime);
                        secondNextHourlyRentalTimeVisible2.click();
                        flag = 1;
                        break;
                    }
                } else {
                    WebElement nextHourlyRentalTimeIfScrollVisible = waitForVisibility(nextHourlyRentalTimeIfScroll);
                    String j = nextHourlyRentalTimeIfScrollVisible.getText();
                    nextHourlyRentalTimeIfScrollVisible.click();
                    if (j.equalsIgnoreCase(hourlyRentalTime)) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0){
                return false;
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error clicking the button of the hourly rental time: " + e.getMessage());
            return false;
        }
    }

    // select the vehicle type
    public boolean vehicleSelect(){

        try{
            String vehicleType = configLoader.getProperty("VEHICLE");
            if (vehicleType.equalsIgnoreCase("Van")){
                WebElement buttonVanVisible = waitForVisibility(buttonVan);
                buttonVanVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("Premium Van")){
                WebElement buttonPremiumVanVisible = waitForVisibility(buttonPremiumVan);
                buttonPremiumVanVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("5.5t Truck")){
                WebElement button55TruckVisible = waitForVisibility(button55Truck);
                button55TruckVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("9t Truck")){
                WebElement button9TruckVisible = waitForVisibility(button9Truck);
                button9TruckVisible.click();
                return true;
            }
            else{
                System.out.println("Wrong vehicle type");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // click on the next button
    public boolean clickNext() {
        try {
            WebElement buttonNextVisible = waitForVisibility(buttonNext);
            buttonNextVisible.click();
            System.out.println("clicked next");
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
    }

}
