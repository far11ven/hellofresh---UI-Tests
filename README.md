##UI Automation Tests - Task#1

This project contains Automation framework which contains all the Tests realted To UI Automation task.

**How To Run:**

Method#1
 - Goto src/test/java > com.hellofresh.testRunner
 - Right click on "CucumberTestRunnerReporter.java" > Run As > JUnit Test
 
 
 Method#2
 - Open "Command Prompt"
 - Goto Poject directory
 
 type following command : 
 			> mvn test -Dbrowser="chrome" -Dcucumber.options="./src/test/resources/features/*" -Dcucumber.options="--tags @all"
 			```
 			-Dbrowser can take values "chrome" or "firefox"
 			-Dcucumber.options="--tags @all" specify "@all" to run all tests or a particular tag like "@m1u1" to run single test
 			-Dcucumber.options specifies path to features folder where all .feature files are stored
 			```


Reports: Reports can be found at path target\cucumber-reports\report.html

Succesful Report:

![alt text](https://raw.githubusercontent.com/far11ven/hellofresh-images/master/images/UI%20Test%20Report%20-Successful.png)

With failure -

![alt text](https://github.com/far11ven/hellofresh-images/blob/master/images/UI%20Test%20Report%20-with%20Failure.png)