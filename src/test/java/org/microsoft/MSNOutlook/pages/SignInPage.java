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

    private final String VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH = "//div[@id ='idDiv_SAOTCS_Title']";
    private final String PASSWORD_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='passwd']";
    private final String PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH = "//div[@id ='passwordError']";
    private final String USER_NAME_ERROR_ELEMENT_XPATH = "//div[@id='usernameError']";
    private final String MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH = "//div[@role='alert']";
    private final String DISPLAYED_IDENTITY_ELEMENT_XPATH = "//div[@id ='displayName']";
    private final String EMAIL_DISPLAYED_WITH_VALID_PASSWORD_ELEMENT_XPATH = "//div[@class='table-row']";// move to the appropriate Page class
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//a[@id='idA_SAOTCS_ShowMoreProofs']";// move to the appropriate Page class
    private final String FORGOT_PASSWORD_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_ForgotPassword']";// move to the appropriate Page class
    private final String ENTER_PASSWORD_ELEMENT_XPATH = "//div[@role='heading']";
    private final String TITLE_TEXT_ELEMENT_XPATH = "//div[contains(@role,'heading') and contains(text(),'')]";
    private final String USERNAME_ERROR_ELEMENT_XPATH = "//div[@id='usernameError']";

    String enterPasswordMessage = driver.findElement(By.xpath(ENTER_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");

    @FindBy(how = How.XPATH, using = VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH)
    private WebElement verifyYourIdentityElement;

    @FindBy(how = How.XPATH, using = PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH )
    private WebElement passwordErrorMessageElement;

    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_LINK_ELEMENT_XPATH)// move to the appropriate Page class
    private WebElement forgotPasswordLinkElement;

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement signInButtonElement;

    public SignInPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_TEXT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ENTER_PASSWORD_ELEMENT_XPATH)));
        logger.info(enterPasswordMessage);
        return this;
    }

    public String getTitleTextMessageInSignInBox(){
        return driver.findElement(By.xpath(TITLE_TEXT_ELEMENT_XPATH)).getText();
    }

    public String getNotValidEmailAccount() {
        return driver.findElement(By.xpath(USERNAME_ERROR_ELEMENT_XPATH)).getAttribute("textContent");
    }

//    in Accessibility tree it says: "ignored". Thus, it is not surefire method for checking in test case
    public String getDisplayedIdentityName() {
        return driver.findElement(By.xpath(DISPLAYED_IDENTITY_ELEMENT_XPATH)).getAttribute("title");
    }

//    This is more reliable method for checking valid email test case
    public String getEnterPasswordMessage() {
        return driver.findElement(By.xpath(ENTER_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");
    }

// alternative method for checking displayed alert message due to randomly "ignored" status in Accessibility
    public boolean alertMSAccountMessageIsDisplayed() {
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).isDisplayed();
    }

//    this method for checking displayed alert message may have randomly "ignored" status in Accessibility
    public boolean usernameErrorMessageIsDisplayed() {
        return driver.findElement(By.xpath(USER_NAME_ERROR_ELEMENT_XPATH)).isDisplayed();
    }

    public VerifyingIdentityPage enterPassword(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(ENTER_PASSWORD_ELEMENT_XPATH)));
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