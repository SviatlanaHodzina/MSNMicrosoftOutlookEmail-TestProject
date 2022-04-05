package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class LogInPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    private final String SIGN_IN_PAGE_CONTENT_ELEMENT_XPATH = "//div[@data-viewid='1']";
    private final String LOGO_TITLE_PAGE_ELEMENT_XPATH = "//img[@alt='Microsoft']";
    private final String TITLE_SIGN_IN_PAGE_ELEMENT_XPATH = "//div[@data-viewid='1']//div[@role='heading']";
    private final String CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH = "//a[@id='signup']";
    private final String LOGIN_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='loginfmt']";
    private final String MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH = "//div[@role='alert']//div[@id='usernameError']";

    @FindBy(how = How.XPATH, using = "//input[@value='Next']")
    private WebElement nextButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@id='lightbox']")
    private WebElement logInPageDataContainerElement;

    public LogInPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LogInPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated((By) logInPageDataContainerElement));
        logger.info(getTitleSignInOfLoginInPage());
        return this;
    }

    public String getTitleLogoOfLoginInPage() {
        return driver.findElement(xpath(LOGO_TITLE_PAGE_ELEMENT_XPATH)).getAttribute("alt");
    }

    public String getTitleSignInOfLoginInPage() {
        return driver.findElement(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignInPage signInWithExistingEmail(MSAccount msAccount) {
        WebElement logInPlaceholderElement = driver.findElement(By.xpath(LOGIN_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist. Now enter password");
        return new SignInPage();
    }

    public boolean alertMSAccountMessageIsDisplayed(MSAccount msAccount) {
        WebElement logInPlaceholderElement = driver.findElement(By.xpath(LOGIN_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).isDisplayed();
    }

    public String getAlertMSAccountAbsenceMessage(MSAccount msAccount) {
        WebElement logInPlaceholderElement = driver.findElement(By.xpath(LOGIN_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignUpNewAccountPage goToCreateAccountPage(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH))).click();
        logger.info("Now create account and sign up.");
        return new SignUpNewAccountPage();
    }
}