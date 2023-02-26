The Project is under revisal due to Microsoft changes

# MSNOutlookEmail-TestProject

//Test cases represented in the project verify Microsoft Email Service's functionality (https://outlook.live.com/owa/):

// > Creation new account,
// > Sign in existing account.

// Running tests with Chromebrowser via cmd:

// mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test

// mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-nosmoking1.xml clean test

// For running other tests replace browsername chrome with the corresponding name: msedge or firefox, environment dev - replace with qa or stage and 
// testng-smoke.xml with the corresponding .xml suite name file
