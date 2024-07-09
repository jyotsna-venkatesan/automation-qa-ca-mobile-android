package org.CATests.pageObjects.android.delivery;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.CATests.pageObjects.android.AbstractPageClass;
import org.CATests.utils.ConfigLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimePage extends AbstractPageClass {

    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // buttons
    // date and time button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_pick_up_date_time\"]")
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
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/submitButton\"]")
    private WebElement buttonOK;

    // the dropp off time buttons
    // instant delivery
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_title\" and @text=\"Instant delivery\"]")
    private WebElement buttonInstant;

    // by 4 hour
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/ll_holder\"])[2]/android.widget.LinearLayout")
    private WebElement button4hour;

    // by 6pm
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_title\" and @text=\"By 6 PM (same-day)\"]")
    private WebElement button6;

    // 6-10pm
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/tv_title\" and @text=\"6-10 PM (same-day)\"]")
    private WebElement button6To10;

    // the button for next
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"hk.gogovan.GoGoVanClient2.staging:id/btn_next\"]")
    private WebElement buttonNext;


    // set up the driver for this page
    public TimePage(AndroidDriver driver){
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
            System.out.println("Clicked on the button for date and time");
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

            System.out.println("Selected the date");
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

            System.out.println("Selected the time");
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
            System.out.println("Clicked on OK");
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the ok button for pick-up time: " + e.getMessage());
            return false;
        }
    }

    // select drop off time
    public boolean clickDropOffTime() {
        try {
            String dropTime = configLoader.getProperty("DROP_TIME");
            if(dropTime.equals("Instant")){
                WebElement buttonInstantVisible = waitForVisibility(buttonInstant);
                buttonInstantVisible.click();
                return true;
            }
            else if(dropTime.equals("4 hour")){
                WebElement button4hourVisible = waitForVisibility(button4hour);
                button4hourVisible.click();
                return true;
            }
            else if(dropTime.equals("6pm")){
                WebElement button6Visible = waitForVisibility(button6);
                button6Visible.click();
                return true;
            }
            else if(dropTime.equals("6-10pm")){
                WebElement button6To10Visible = waitForVisibility(button6To10);
                button6To10Visible.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting drop time: " + e.getMessage());
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


}
