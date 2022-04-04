# MSNOutlookEmail-TestProject
The Test project is created for a training purpose. 
Test cases represented in the project verify Microsoft Email Service's functionality (https://outlook.live.com/owa/): 
> Creation new account,
> Sign in existing account.

Running tests with one single browser chrome:

mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test

mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking1.xml clean test

mvn -Dbrowser=chrome -Denvironment=stage -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking2.xml clean test

mvn -Dbrowser=chrome -Denvironment=stage -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking2-2.xml clean test

mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking3.xml clean test

mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking4.xml clean test 

mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking5.xml clean test


SHOULD BE CLARIFIED AND CORRECTED:
CrossBrowser Testing: Multiple Browser Testing in a selenium TestNG project.
Run TestNG xml files from you command line:

mvn -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserSmoke.xml clean test

mvn -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserNosmoking1.xml clean test

mvn -Denvironment=stage -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserNosmoking2.xml clean test

mvn -Denvironment=stage -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserNosmoking2-2.xml clean test

mvn -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserNosmoking3.xml clean test

mvn -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-CrossBrowserNosmoking4.xml clean test 

mvn -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng- CrossBrowserNosmoking5.xml clean test
