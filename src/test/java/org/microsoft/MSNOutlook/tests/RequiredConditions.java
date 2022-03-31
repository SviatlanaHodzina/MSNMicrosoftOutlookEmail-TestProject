package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.driver.WebDriverConnector;
import org.microsoft.MSNOutlook.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class RequiredConditions {
    public static WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = WebDriverConnector.getDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopDriver(){
//        WebDriverConnector.closeDriver();
//    }
}