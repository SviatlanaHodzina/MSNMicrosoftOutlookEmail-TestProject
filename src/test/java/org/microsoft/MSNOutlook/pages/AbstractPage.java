package org.microsoft.MSNOutlook.pages;

import org.microsoft.MSNOutlook.driver.WebDriverConnector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public abstract class AbstractPage {
    protected static WebDriver driver;

    public final int WAIT_TIMEOUT_SECONDS = 50;
    public final int WAIT_TIMEOUT_SECONDS_EXTENDED= 500;
    protected abstract AbstractPage openPage();

    protected AbstractPage() throws MalformedURLException {
        driver = WebDriverConnector.getDriver("browser");
        PageFactory.initElements(driver,this);
    }
}