package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.driver.WebDriverConnector;
import org.microsoft.MSNOutlook.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

@Listeners({TestListener.class})
public class RequiredConditions {
    public WebDriver driver;

    @Parameters("browser")
    public void setUp(String browser) throws MalformedURLException {
        driver = WebDriverConnector.getDriver("browser");
    }
    public void stopDriver(){
        WebDriverConnector.closeDriver();
    }
}
