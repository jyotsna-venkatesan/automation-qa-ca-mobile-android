# QA E2E Test Automation - CA Application 

## How to use
1. Run the main function in the AppTest.java file. Note: this is present in /CATests/src/test/java/AppTest/java
2. If prompted to allow the appliation to use loction by the app, click 'deny' within 7 seconds

## What does this do so far
1. Opens the 'Stag-GoGoX' application on Android 
2. Goes to the transport page
3. Inputs the address for 'Where from'

## Project Structure Explanation 
- src: stores the test and automation files
   -  main: stores everything related to automation
        - pageObjects.android: stores the files that contain the functions and buttons for each page
          - transport: stores the files for transport
          - delivery (to be made): stores the files related to delivery
        - utils: stores the ConfigLoader that inputs the values such as addresses, etc 
        - resources: has a file called config.properties that stores the values that ConfigLoader uses 
   -  test: stores everything related to testing
     - org.CATests.pageObjects.android.tests: stores all the test files
       - transport: stores all the test files related to transport
       - delivery (to be made): stores all the test files related to delivery
- others: BaseTestClass (AbstractPageClass) and HomePageTest (HomePage) these files store the main and test files for the common pages

