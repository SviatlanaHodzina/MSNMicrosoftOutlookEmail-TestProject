package org.microsoft.MSNOutlook.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConnector {
    private static WebDriver driver;

    public static synchronized WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "msedge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                case "chrome": {
                    WebDriverManager.chromedriver().driverVersion("99.0.4844.51").setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}