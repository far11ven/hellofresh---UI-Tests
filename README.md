# UI Automation Tests - Task#1
This project contains Automation framework which contains all the Tests realted To UI Automation task.

**Project Summary:**

This framework uses Selenium-Cucumber-JUnit (Java) and includes Page Object Model. 

- For Reporting Extent Reports are used and for logging Log4J
- All page elements and their actions are stored in ```src/main/java```, under package ```"com.hellofresh.pages"```
- TestData for Registration is stored in ```"RegistrationTestData.xlsx"``` under ```src/test/resource```
- ```"config.properties"``` contains all the configuration settings
- ```"com.hellofresh.utils"``` contains utility functions used in project
- ```"com.hellofresh.stepDefinition"``` contains all the step definitions


**Task Mapping:**

1) Task#1 : SuccessfulUserSignUp [Positive Scenario].feature
2) Task#2 : SuccessfulUserLogin [Positive Scenario].feature
3) Task#3 : SuccessfulCheckoutFlow [Positive Scenario].feature

**Extra Scenarios:**
- LoginWithWrongCredentials [Negitive Scenario].feature
- SuccessfulUserLogin [Negitive Scenario].feature

## How To Run:

 **Method#1**
 - Goto ```src/test/java > com.hellofresh.testRunner```
 - Right click on ```"CucumberTestRunnerReporter.java"``` > ```Run As``` > ```JUnit Test```
 
 
 **Method#2**
 - Open ```"Command Prompt"```
 - Goto ```Project directory```
 - type following command : 
 
 			> mvn install
 			> mvn test -Dbrowser="chrome" -Dcucumber.options="./src/test/resources/features/*" -Dcucumber.options="--tags @all"
 			
 			```-Dbrowser can take values "chrome" or "firefox"```
 			```-Dcucumber.options="--tags @all" specify "@all" to run all tests or a particular tag like "@m1u1" to run single test```
 			```-Dcucumber.options specifies path to features folder where all .feature files are stored```
 		
## Logs: 

Logs are stored under respective date folder under "logs" directory (Created after initial run)

## Reports: 
Reports can be found at path ```target\cucumber-reports\report.html``` and screenshots at ```target\cucumber-reports\screenshots```

**Successful Report:**

![alt text](https://raw.githubusercontent.com/far11ven/hellofresh-images/master/images/UI%20Test%20Report%20-Successful.png)


**With failure:**

![alt text](https://github.com/far11ven/hellofresh-images/blob/master/images/UI%20Test%20Report%20-with%20Failure.png)
