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
import java.util.List;

public class VerifyingIdentityPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    private final String GET_CODE_BUTTON_ELEMENT_XPATH = "//*[@id='iSelectProofAction']";
    private final String PHONE_NUMBER_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='proofInput1']";
    private final String CODE_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='']";// to check in perspective
    private final String WE_NEED_TO_VERIFY_YOUR_IDENTITY_ELEMENT_XPATH = "//*[@id='iSelectProofTitle']";
    private final String EMAIL_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='iProofList']//input[@value='0']";
    private final String SMS_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='iProofList']//input[@value='1']";
    private final String SMS_OPTION_METHOD_MESSAGE_ELEMENT_XPATH = "//label[@id='proofEntryDesc1']";
    private final String MESSAGE_QUESTION_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//*[@id='idOptsSectionTxt']";
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//*[@id='iShowCostlyProofsLink']";
    private final String RESET_PASSWORD_BUTTON_ELEMENT_XPATH = "//*[@id='']";// to check in perspective
    private final String CANCEL_BUTTON_IN_RESET_PASSWORD_PAGE_ELEMENT_XPATH = "//*[@id='iSelectProofCancel']";

    @FindBy(how = How.XPATH, using = "//div[@id='iProofList']//input[@name='proofOption']")
    private List<WebElement> optionsToGetSecurityCodeElementList;

    @FindBy(how = How.XPATH, using = "//a[@id='idA_IL_ForgotPassword0']")
    private WebElement resetPasswordLinkElement;

    public VerifyingIdentityPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public VerifyingIdentityPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(WE_NEED_TO_VERIFY_YOUR_IDENTITY_ELEMENT_XPATH)));
        logger.info(getMessageQuestionToGetSecCodeToResetPassword());
        return this;
    }

    public String getTitleResetPasswordPage() {
        return driver.findElement(By.xpath(WE_NEED_TO_VERIFY_YOUR_IDENTITY_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getMessageQuestionToGetSecCodeToResetPassword() {
        return driver.findElement(By.xpath(MESSAGE_QUESTION_TO_GET_SECURITY_CODE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getEmailOptionTextMessageToGetSecCode() {
        return driver.findElement(By.xpath(EMAIL_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getSMSOptionTextMessageToGetSecCode() {
        return driver.findElement(By.xpath(SMS_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getPhoneNumberVerificationMessage() {
        return driver.findElement(By.xpath(SMS_OPTION_METHOD_MESSAGE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public VerifyingIdentityPage selectEmailOptionMethod() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(EMAIL_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH))).click();
        return this;
    }

    public boolean emailRadioButtonToGetSecurityCodeIsSelected() {
        return driver.findElement(By.xpath(EMAIL_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH)).isSelected();
    }

    public VerifyingIdentityPage selectSMSOptionMethod() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(optionsToGetSecurityCodeElementList));
        for (WebElement option : optionsToGetSecurityCodeElementList) {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.elementToBeClickable
                            (By.xpath(SMS_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH))).click();
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated
                            (By.xpath(SMS_OPTION_METHOD_MESSAGE_ELEMENT_XPATH)));
        }
        logger.info(getSMSOptionTextMessageToGetSecCode() + '\n' + getPhoneNumberVerificationMessage());
        return this;
    }

    public boolean smsOptionToGetSecurityCodeIsSelected() {
        return driver.findElement(By.xpath(SMS_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH)).isSelected();
    }

    public ResetPasswordPage verifyPhoneNumber(MSAccount msAccount) {
        WebElement phoneNumberPlaceholder = driver.findElement(By.xpath(PHONE_NUMBER_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(PHONE_NUMBER_PLACEHOLDER_ELEMENT_XPATH))).click();
        phoneNumberPlaceholder.sendKeys(msAccount.getPhoneNumber());
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GET_CODE_BUTTON_ELEMENT_XPATH))).click();
        return new ResetPasswordPage();
    }
     public String codeToResetPassword = "";// to check in perspective

    public ResetPasswordPage verifyEmailToGetCode() {
        selectEmailOptionMethod();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GET_CODE_BUTTON_ELEMENT_XPATH))).click();
        WebElement codePlaceholder = driver.findElement(By.xpath(CODE_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(CODE_PLACEHOLDER_ELEMENT_XPATH))).click();
        codePlaceholder.sendKeys(codeToResetPassword);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(RESET_PASSWORD_BUTTON_ELEMENT_XPATH))).click();
        logger.info("Password is reset. enter a new one");
        return new ResetPasswordPage();
    }
}