# MSNOutlookEmail-TestProject
JOB REQUEST for Automated and Manual testing.

Updated description 17 September 2023

The Testing Project is under revision due to Microsoft changes.
Testing is in the process

// Test cases represented in the project verify Microsoft Email Service's functionality of
Outlook and Microsoft:
P.S. https://outlook.live.com/owa/ modified into
https://www.microsoft.com/en-us/microsoft-365/outlook/email-and-calendar-software-microsoft-outlook
https://www.microsoft.com/en-us
 
Cross browser testing will be added later.

> Creation new accounts

> Sign in existing accounts

// Running tests with Firefox via cmd:

//CorrectMicrosoftAccountEmailTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking1.xml clean test

//IncorrectMSOutlookAccountAlertDisplayTest (Outlook email account)
mvn -Dbrowser=firefox -Denvironment=dev2 -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking2.xml clean test

//IncorrectInputEmailAccountAlertTextTest (Microsoft email account)
mvn -Dbrowser=firefox -Denvironment=dev2 -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking2-2.xml clean test

//CreateNewEmailNameWithOutlookTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking3.xml clean test

//BusyEmailCaseWhenCreatingNewEmailWithOutlookTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking3-3.xml clean test

//CreatePasswordForNewEmailAccountTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking4.xml clean test

//DisplayedPasswordTextInCreateAPasswordPageTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking7.xml clean test

//RobotCheckPageExistenceTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking16.xml clean test

//StayingSignedInOnYesAnswerToOfferToStaySignInTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking17.xml clean test

//SignOutOnNoAnswerToOfferToStaySignInTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking18.xml clean test

//BusyEmailCaseWhenCreatingNewEmailWithOutlookTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking17Outlook.xml clean test

//EmailInfoCheckBoxInCreateAPasswordPageTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking18Outlook.xml clean test

//EmailInfoCheckBoxIsSelectedByDefaultInCreateAPasswordPageTest
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking19Outlook.xml clean test

//SignInMSOutlookLiveAccountViaCorrectEmailTest
mvn -Dbrowser=firefox -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking20Outlook.xml clean test


// For running other tests replace browsername firefox with the corresponding browser name, environment dev - replace with qa or stage 
// testng-nosmoking.xml with the corresponding .xml suite name file
