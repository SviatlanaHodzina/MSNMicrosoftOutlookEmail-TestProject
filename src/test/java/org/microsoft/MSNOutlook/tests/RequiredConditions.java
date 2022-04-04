package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.driver.WebDriverConnector;
import org.microsoft.MSNOutlook.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({TestListener.class})
public class RequiredConditions {
    public static WebDriver driver;

    @BeforeMethod()
    @Parameters("browser")
    public void setUp(String browser) {
        driver = WebDriverConnector.getDriver(browser);
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopDriver(){
//        WebDriverConnector.closeDriver();
//    }
}