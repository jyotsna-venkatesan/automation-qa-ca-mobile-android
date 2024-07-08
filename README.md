# QA E2E Test Automation - CA Application 

## How to use
1. Open the testng.xml file and click run

## Notes before running
1. Ensure that the driver app does not have access to the location of the user
2. Ensure that you have logged into the driver app before starting the test

## What does this do so far
1. Opens the 'Stag-GoGoX' application on Android 
2. Performs tests from an excel file "testdata.xlsx"
   1. Logs into an account
   2. Places a transport order
   3. Opens the driver app
   4. Picks up the order and completes it
   5. Cancels order from the CA if wanted
3. Generates an extent report

## Project Structure Explanation 
- extentReports: stores the extent report generated after the automation tests
- src: stores the test and automation files
   -  main: stores everything related to automation
     - CATests: stores all the files relating to the customer app
          - pageObjects.android: stores the files that contain the functions and buttons for each page
            - transport: stores the files for transport
            - delivery (to be made): stores the files related to delivery
          - utils: stores the ConfigLoader and all the files related to data loading to input the values such as addresses, etc 
          - resources: has a file called config.properties that stores the values that ConfigLoader uses and also has the excel file with the variables
     - DATests: stores all the files relating to the driver app
   -  test: stores everything related to testing
     - org.CATests.pageObjects.android.tests: stores all the customer app test files
       - transport: stores all the test files related to transport
       - delivery (to be made): stores all the test files related to delivery
     - org.DATests.pageObjects.android.tests: stores all the driver app test files
- others: BaseTestClass (AbstractPageClass) and HomePageTest (HomePage) these files store the main and test files for the common pages

## Enhancements to be made
1. Make the code accept "Tomorrow" for the order date
2. Enable the scroll functionality inside the tunnel preference popup
3. Make the code automatically update the current date
4. Add functionality to send note to driver
5. Implement the "apply coupon" functionality
6. Make the implementation faster

