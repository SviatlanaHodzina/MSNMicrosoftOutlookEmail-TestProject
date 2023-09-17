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

import static org.openqa.selenium.By.xpath;

// Due to recent developer's changes this page was edited 14 September 2023
// OutlookLivePage is integrated into main Microsoft site and the method, opening Outlook, should be integrated into MicrosoftPage
public class OutlookLivePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String OUTLOOK_LIVE_COM_PAGE = "https://www.microsoft.com/en-us/microsoft-365/outlook/email-and-calendar-software-microsoft-outlook";
    public static final String MAIN_PAGE_MICROSOFT_CONTENT_ELEMENT_XPATH = "//body[@class='page basicpage']";
    public static final String SIGN_IN_OUTLOOK_ACCOUNT_ELEMENT_XPATH = "//div[@class='card-body p-4 p-md-5']//a[@class='btn btn-primary   ']";// gaps in the attribute's value design are redundant
    public final String CREATE_FREE_ACCOUNT_BUTTON_ELEMENT_XPATH = "//a[contains(@class,'btn btn-outline-primary-white   ') and contains(@aria-label,'Create a free Microsoft Outlook account')]";

    public OutlookLivePage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public OutlookLivePage openPage() {
        driver.navigate().to(OUTLOOK_LIVE_COM_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_PAGE_MICROSOFT_CONTENT_ELEMENT_XPATH)));
        logger.info("Page www.microsoft.com/en-us/microsoft-365/outlook/email-and-calendar-software-microsoft-outlook is loaded");
        return this;
    }

    public SignUpNewAccountPage createNewOutlookAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_FREE_ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MAIN_PAGE_MICROSOFT_CONTENT_ELEMENT_XPATH)));
        logger.info("Create Account Page is loaded");
        return new SignUpNewAccountPage();
    }

    public LogInOutlookLivePage signIn() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(SIGN_IN_OUTLOOK_ACCOUNT_ELEMENT_XPATH))).click();
        logger.info("Sign in. Enter your Email, phone or Skype");
        return new LogInOutlookLivePage();
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
