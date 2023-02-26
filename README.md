# MSNOutlookEmail-TestProject

The Project is under revisal due to Microsoft changes

Test cases represented in the project verify Microsoft Email Service's functionality (https://outlook.live.com/owa/): 
> Creation new account,
> Sign in existing account.

Running tests with Chromebrowser via cmd:

mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test
mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking1.xml clean test

testng-smoke.xml - replace with the corresponding .xml suite file
