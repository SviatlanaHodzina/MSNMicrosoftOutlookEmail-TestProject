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

public class SignInPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String TITLE_SIGN_IN_PAGE_ELEMENT_XPATH = "//div[@data-viewid='1']//div[@role='heading']";
    private final String TITLE_ENTER_PASSWORD_ELEMENT_XPATH = "//div[@data-viewid='2']//div[@role='heading']";
    private final String VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH = "//div[@id ='idDiv_SAOTCS_Title']";
    private final String PASSWORD_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='passwd']";
    private final String PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH = "//div[@id ='passwordError']";
    private final String DISPLAYED_IDENTITY_ELEMENT_XPATH = "//div[@id ='displayName']";
    private final String EMAIL_DISPLAYED_WITH_VALID_PASSWORD_ELEMENT_XPATH = "//div[@class='table-row']";// move to the appropriate Page class
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//a[@id='idA_SAOTCS_ShowMoreProofs']";// move to the appropriate Page class
    private final String FORGOT_PASSWORD_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_ForgotPassword']";// move to the appropriate Page class
    private final String TITLE_TEXT_ELEMENT_XPATH = "//div[contains(@role,'heading') and contains(text(),'')]";

    @FindBy(how = How.XPATH, using = VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH)
    private WebElement verifyYourIdentityElement;

    @FindBy(how = How.XPATH, using = PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH)
    private WebElement passwordErrorMessageElement;

    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_LINK_ELEMENT_XPATH)// move to the appropriate Page class
    private WebElement forgotPasswordLinkElement;

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement signInButtonElement;

    public SignInPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_TEXT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        logger.info("Enter password");
        return this;
    }

    public String getDisplayedIdentityName() {
        return driver.findElement(By.xpath(DISPLAYED_IDENTITY_ELEMENT_XPATH)).getAttribute("title");
    }

    public String getTitleEnterPasswordMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public VerifyingIdentityPage enterPassword(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement passwordPlaceholderElement = driver.findElement(xpath(PASSWORD_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(passwordPlaceholderElement)).click();
        passwordPlaceholderElement.sendKeys(msAccount.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonElement)).click();

        if (driver.findElement(By.xpath(EMAIL_DISPLAYED_WITH_VALID_PASSWORD_ELEMENT_XPATH)).isDisplayed()) {
            logger.info(verifyYourIdentityElement.getAttribute("textContent") + "Follow link: 'I have a code' or " +
                    "'Show more verification methods'");
        } else {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated
                            (xpath(PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH)));
            logger.info(passwordErrorMessageElement.getAttribute("textContent"));
        }
        return new VerifyingIdentityPage();
    }
}