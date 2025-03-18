***NSE India Selenium Java Test Automation***

*************************************************************************************
Note: As this is the Government website, Automation is blocked not working properly. 
With some options it working only with chromium based drivers(chrome, edge)
*************************************************************************************

****Framework and Languages****
-------------------------------
The project developed on Page Object Model with the following
1. java 11 as the programming language
2. Selenium WebDriver as the webdriver automation framework
3. TestNG as the testing framwork
4. Maven as build automation
5. Extent Reports as test reporting strategy
6. Log4j for logging

****Project Stucture****
-------------------------
├───.mvn
├───.settings
├───logs
├───reports
├───screenshots
├───src
│   ├───main
│   │   └───java
│   │       └───com
│   │           └───qa
│   │               └───NSEIndia
│   │                   ├───constants
│   │                   ├───error
│   │                   ├───exceptions
│   │                   ├───Factory
│   │                   ├───listners
│   │                   ├───logger
│   │                   ├───pages
│   │                   └───utilities
│   └───test
│       ├───java
│       │   └───com
│       │       └───qa
│       │           └───NSEIndia
│       │               ├───base
│       │               └───tests
│       └───resources
│           ├───config
│           ├───testdata
│           └───testrunnners
├───target
│   ├───classes
│   │   ├───com
│   │   │   └───qa
│   │   │       └───NSEIndia
│   │   │           ├───constants
│   │   │           ├───error
│   │   │           ├───exceptions
│   │   │           ├───Factory
│   │   │           ├───listners
│   │   │           ├───logger
│   │   │           ├───pages
│   │   │           └───utilities
│   │   └───META-INF
│   │       └───maven
│   │           └───NSEIndia
│   │               └───NSEIndia
│   ├───generated-sources
│   │   └───annotations
│   ├───generated-test-sources
│   │   └───test-annotations
│   ├───maven-status
│   │   └───maven-compiler-plugin
│   │       ├───compile
│   │       │   └───default-compile
│   │       └───testCompile
│   │           └───default-testCompile
│   ├───surefire-reports
│   │   ├───junitreports
│   │   ├───NSEIndia App Test Suite
│   │   └───old
│   │       └───NSEIndia App Test Suite
│   └───test-classes
│       ├───com
│       │   └───qa
│       │       └───NSEIndia
│       │           ├───base
│       │           └───tests
│       ├───config
│       └───testrunnners
└───test-output
    ├───Default suite
    ├───junitreports
    ├───NSEIndia App Test Suite
    └───old
        ├───Default suite
        └───NSEIndia App Test Suite
 
 ****Highlevel Project Explanation****
 --------------------------------------
 Factory (DriverMaager.java) : has the implementation of the thread local concept and initialization of drivers and properties and getscreenshot method
 constants ( AppConstants.java) : it is the interface and has file paths, actual values of the application
 errors ( AppError.java) : it is the interface and has the errors that occurs whilee executing the tests
 exceptions : use to handle the exceptions occured while executing the tests
 listeners : it has the template of extent reports which implements ITestListener and has the code to generate the reports onStart, onFinish, onTestStart, onTestSuccess, onTestFailure etc
 logger (Log.java) : has the methods related to logging like info, error, warn, debug
 pages : it has all the bussiness pages related to the application where private By locators and the bussiness logic is written
 Utilities : utilities related to the webelement are maintained in this package and time utilities for explict wait
 Test : testNG framework/Annotations is utilized in this testpackage to verify/Assert the bussiness logic written in page classes
 Base : has browser setup and teardown methods so that child test classes extends baseTest classes
 Tests : testN assertions are implemented to verify the bussiness logic written on the page classes
 resources : it has configuration files (i.e., config.properties file and url and browser) and runner files(.xml files to run the tests)
 
 ****Java files for the project and Test Execution****
 ------------------------------------------------------
 HomePage.java ==> has the methods for getting the title (getHomePageTitle method) of www.nseindia.com, checks if search box exists (checkSearchBoxExist method), search for the product by taking company as the parameter and it will return compayInfoPages
 
 HomePageTests.java ==> used to Assert the methods of HomePage.java
 
 CompanyInfoPage.java ==> selectProduct method will get the current value, last 52week high and low values for the company selected and it will return the list with high and low values
 
CompanyInfoPageTests.java ==> has 2 methods
	1. selectCompanyTest takes the CompanyName ad the parameter and is driven by dataprovider companyData(any number of companies can be passed in the dataprovider)
	2. selectProductWithAssert ==> will verify fetched last 52week high and low values by with values given in the AppConstants
	
****Test Execution and results****
-----------------------------------
In testrunners we have .xml files to execute the test
	1. parallelTest.xml will execute the tests in parallel on chrome, edge and firefox browsers
	2. testChrome.xml will execute the tests on chrome browser
	3. testEdge.xml will execute the tests on edge browser
	4. path of extent report is ./NSEIndia/reports/TestExecutionReport.html
	5. path of logs is ./NSEIndia/logs/NSEIndiaTest.log