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

    private final String LOGO_TITLE_PAGE_ELEMENT_XPATH = "//img[@alt='Microsoft']";
    private final String CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH = "//a[@id='signup']";

    @FindBy(how = How.XPATH, using = "//input[@value='Next']")
    private WebElement nextButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@id='lightbox']")
    private WebElement logInPageDataContainerElement;

    @FindBy(how = How.XPATH, using = "//input[@name='loginfmt']")
    private WebElement logInPlaceholderElement;

    public LogInPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LogInPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated((By) logInPageDataContainerElement));
        logger.info("Sign in. Enter your Email, phone or Skype");
        return this;
    }

    public String getTitleOfLoginInPage() {
        return driver.findElement(xpath(LOGO_TITLE_PAGE_ELEMENT_XPATH)).getAttribute("alt");
    }

    public SignInPage signInWithExistingEmail(MSAccount msAccount) {
        driver.findElement((By) logInPlaceholderElement);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account is valid. Now enter password");
        return new SignInPage();
    }

    public SignUpNewAccountPage goToCreateAccountPage(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH))).click();
        logger.info("Now create account and sign up.");
        return new SignUpNewAccountPage();
    }
}