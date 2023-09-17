package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class MicrosoftPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String MICROSOFT_PAGE = "https://www.microsoft.com/en-us";

    public final String MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH = "//html/body[@class='page basicpage']";
    public static final String SIGN_IN_MICROSOFT_ACCOUNT_ELEMENT_XPATH = "//a[@id='mectrl_main_trigger']"; // or "//a[aria-label='Sign in to your account']"

    public MicrosoftPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MicrosoftPage openPage() {
        driver.navigate().to(MICROSOFT_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Page " + MICROSOFT_PAGE + " is loaded");
        return this;
    }

    public LogInMicrosoftPage signInMicrosoftAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(SIGN_IN_MICROSOFT_ACCOUNT_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(SIGN_IN_MICROSOFT_ACCOUNT_ELEMENT_XPATH)));
        logger.info("Sign in. Enter your Email, phone or Skype");
        return new LogInMicrosoftPage();
    }

    public void openNewTabByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
        logger.info("New tab is opened");
    }

    public void handlePageDownByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).keyUp(Keys.CONTROL).build().perform();
    }

    public void handlePageUpByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).keyUp(Keys.CONTROL).build().perform();
    }


    public void handleWindowTabForwardByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
    }

    public void handleWindowTabBackByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.LEFT_SHIFT).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
    }
}