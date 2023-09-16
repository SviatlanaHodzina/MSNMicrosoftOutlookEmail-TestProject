package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_SIGN_IN_PAGE_ELEMENT_XPATH;

public class VerifyingIdentityPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    private final String PHONE_NUMBER_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='proofInput1']";
    private final String CODE_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='']";// to check in perspective
    private final String TITLE_VERIFY_IDENTITY_ELEMENT_XPATH = "//*[@id='iSelectProofTitle']";
    private final String OPTIONS_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='iProofList']";
    private final String EMAIL_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='iProofList']//input[@value='0']";
    private final String SMS_RADIO_BUTTON_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='iProofList']//input[@value='1']";// this option description is absent, the xpath is the same
    private final String SMS_OPTION_METHOD_MESSAGE_ELEMENT_XPATH = "//label[@id='proofEntryDesc1']";// this option description is absent, the xpath is the same
    private final String MESSAGE_QUESTION_TO_GET_SECURITY_CODE_ELEMENT_XPATH = "//div[@id='idOptsSectionTxt']";
    private final String RECOVERY_OPTIONS_RADIO_BUTTON_LIST_ELEMENT_XPATH = "//div[@id='iProofList']//input[@name='proofOption']";
    private final String RECOVERY_OPTIONS_TEXT_HINT_LIST_ELEMENT_XPATH = "//div[@id='iProofList']//span"; // spans are not recommended for usage in xpath
    private final String RADIO_BUTTON_RECOVERY_ACCOUNT_OPTION_ELEMENT_XPATH = "//div[@id='iProofList']//input[@id='proofOption0']";
    private final String RECOVERY_ACCOUNT_OPTION_TEXT_HINT_ELEMENT_XPATH = "//span[@id='textproofOption0']";
    private final String RECOVERY_OPTION_SELECTED_DESCRIPTION_ELEMENT_XPATH = "//label[@id='proofEntryDesc0']";
    private final String PLACEHOLDER_RECOVERY_ACCOUNT_OPTION_ELEMENT_XPATH = "//input[@id='proofInput0']";
    private final String PLACEHOLDER_RECOVERY_OPTION_SELECTED_DOMAIN_ELEMENT_XPATH = "//div[@class='dirltr input-group input-max-width']/label";
    private final String DONT_HAVE_ANY_RECOVERY_ACCOUNT_OPTIONS_LINK_ELEMENT_XPATH = "//a[@id='iSelectProofSkip']";
    private final String I_HAVE_A_CODE_LINK_ELEMENT_XPATH = "//a[@id='iSelectProofAlternate']";
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//*[@id='iShowCostlyProofsLink']";
    private final String GET_CODE_BUTTON_ELEMENT_XPATH = "//*[@id='iSelectProofAction']";
    private final String CANCEL_RECOVERY_BUTTON_ELEMENT_XPATH = "//input[@id='iSelectProofCancel']";
    private final String REQUESTED_TOO_MANY_CODES_MESSAGE_ELEMENT_XPATH = "//*[@id='iSelectProofError']"; //for the method with several code sending times before
    private final String RESET_PASSWORD_BUTTON_ELEMENT_XPATH = "//*[@id='']";// to check in perspective
    private final String CANCEL_BUTTON_IN_RESET_PASSWORD_PAGE_ELEMENT_XPATH = "//*[@id='iSelectProofCancel']";

    @FindBy(how = How.XPATH, using = RECOVERY_OPTIONS_RADIO_BUTTON_LIST_ELEMENT_XPATH)
    private List<WebElement> radioButtonOptionsElementList;

    @FindBy(how = How.XPATH, using = RECOVERY_OPTIONS_TEXT_HINT_LIST_ELEMENT_XPATH)
    private List<WebElement> recoveryOptionsTextHintElementList;

    @FindBy(how = How.XPATH, using = OPTIONS_TO_GET_SECURITY_CODE_ELEMENT_XPATH)
    private List<WebElement> optionsToGetSecurityCodeElementList;

    public VerifyingIdentityPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public VerifyingIdentityPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_VERIFY_IDENTITY_ELEMENT_XPATH)));
        logger.info(getMessageQuestionToGetSecCodeToResetPassword());
        return this;
    }

    public String getTitleVerifyingIdentityPage() {
        return driver.findElement(By.xpath(TITLE_VERIFY_IDENTITY_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getRecoveryOptionTextHintPage() {
        return driver.findElement(By.xpath(RECOVERY_OPTIONS_TEXT_HINT_LIST_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getMessageQuestionToGetSecCodeToResetPassword() {
        return driver.findElement(By.xpath(MESSAGE_QUESTION_TO_GET_SECURITY_CODE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getMessageAlertAfterRequestedManyCodesForRecovery() {
        return driver.findElement(By.xpath(REQUESTED_TOO_MANY_CODES_MESSAGE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public VerifyingIdentityPage selectRecoveryOptionEmailToSendCode() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(recoveryOptionsTextHintElementList));
        for (WebElement radioButtonOption : radioButtonOptionsElementList) {
            radioButtonOptionsElementList.get(0).click();
        }
        logger.info("Enter alternative email address where to send a verification code.");
        return this;
    }

//
//    public VerifyingIdentityPage selectRecoveryOptionEmailToSendCode() {
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.visibilityOfAllElements(recoveryOptionsTextHintElementList));
//        for (WebElement radioButtonOption : radioButtonOptionsElementList) {
//            for (WebElement recoveryOptionTextHint : recoveryOptionsTextHintElementList) {
//                if (recoveryOptionTextHint.getAttribute("textContent").startsWith("so")) {
//                    radioButtonOption.click();
//                }
//            }
//        }
//        logger.info("Enter alternative email address where to send a verification code.");
//        return this;
//    }

    public VerifyingIdentityPage enterRecoveryOptionEmail(String alternativeEmailName, String alternativeEmailDomain) throws MalformedURLException {
        WebElement enterAlternativeEmailForRecoveryElement = driver.findElement(By.xpath(PLACEHOLDER_RECOVERY_ACCOUNT_OPTION_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(enterAlternativeEmailForRecoveryElement)).click();
        enterAlternativeEmailForRecoveryElement.sendKeys(alternativeEmailName);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.attributeToBe(By.xpath(PLACEHOLDER_RECOVERY_OPTION_SELECTED_DOMAIN_ELEMENT_XPATH),
                        "textContent", (alternativeEmailDomain)));
        return new VerifyingIdentityPage();
    }


    public CodeVerificationPage getCodeButtonClickToRecoverAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GET_CODE_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TITLE_VERIFY_IDENTITY_ELEMENT_XPATH)));
        logger.info("Verify Identity Page.");
        return new CodeVerificationPage();
    }

    public SignInMicrosoftPage cancelButtonClickToRecoverAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(CANCEL_RECOVERY_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TITLE_VERIFY_IDENTITY_ELEMENT_XPATH)));
        logger.info("You should be returned to Sign in Page.");
        return new SignInMicrosoftPage();
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
                .until(ExpectedConditions.visibilityOfAllElements(optionsToGetSecurityCodeElementList));
        for (WebElement option : optionsToGetSecurityCodeElementList) {
            optionsToGetSecurityCodeElementList.get(0).click();
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

    public ResetPasswordPage verifyPhoneNumber(MSAccount msAccount) throws MalformedURLException {
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

    public ResetPasswordPage verifyEmailToGetCode() throws MalformedURLException {
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

    public void openNewTabViaSelenium() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    }

    public void turnToPreviousTabViaSelenium() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
        driver.switchTo().defaultContent();
    }

    public void openNewTabByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
        logger.info("New tab is opened");
    }

    public void openNewWindowByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("N").keyUp(Keys.CONTROL).build().perform();
        logger.info("New window is opened");
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