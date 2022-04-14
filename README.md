# MSNOutlookEmail-TestProject
The Test project is created for a training purpose. 
Test cases represented in the project verify Microsoft Email Service's functionality (https://outlook.live.com/owa/): 
> Creation new account,
> Sign in existing account.

Running tests with Chromebrowser via cmd:

testng-smoke.xml - replace with the corresponding .xml suite file

mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test

// this test case should be changed after creating new account for test purpose
mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking1.xml clean test
