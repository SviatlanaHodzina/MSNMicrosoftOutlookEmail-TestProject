package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class OutlookLiveComPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String OUTLOOK_LIVE_COM_PAGE = "https://outlook.live.com/owa/";
    public final String MAIN_PAGE_CONTENT_ELEMENT_XPATH = "//html/body/header[@class='masthead']";
    public final String SIGN_IN_BUTTON_ELEMENT_XPATH = "//div[@class='masthead-shell']//a[contains(text(),'%s')]";

    public String signIn = "Sign in";//move to resource bundle message_lang.properties

    public OutlookLiveComPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public OutlookLiveComPage openPage() {
        driver.navigate().to(OUTLOOK_LIVE_COM_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Page https://outlook.live.com/owa/ is loaded");
        return this;
    }

    public LogInPage signIn(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (xpath(String.format(SIGN_IN_BUTTON_ELEMENT_XPATH, signIn)))).click();
        logger.info("Sign in. Enter your Email, phone or Skype");
        return new LogInPage();
    }
}