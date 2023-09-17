package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.RecoveringMSAccountDialoguePage.TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.ResetPasswordPage.TITLE_RESET_PASSWORD_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.VerifyEmailPage.TITLE_EMAIL_VERIFICATION_PAGE_ELEMENT_XPATH;

import static org.openqa.selenium.By.xpath;

public class SignInMicrosoftPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    // Take the Microsoft logo verifying methods to the "utils" folder
    // "alt": attribute for displayed logo text
    public static final String LOGO_MICROSOFT_COMPANY_TITLE_ELEMENT_XPATH = "//img[@class='logo']";

    // images verifying is providing with other methods
    // "Logo_Image_Microsoft": key for the bundle name "References"
    // [ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);
    // resourceBundle.getString("Enter password");]
    public static final String LOGO_IMAGE_MICROSOFT_COMPANY_ELEMENT_XPATH = "//img[@src='%s']";

    public static final String TITLE_SIGN_IN_PAGE_ELEMENT_XPATH = "//div[@data-viewid='1']//div[@role='heading']";
    public static final String TITLE_ENTER_PASSWORD_ELEMENT_XPATH = "//div[@data-viewid='2']//div[@role='heading']";
    private final String SIGN_IN_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='loginfmt']";
    private final String VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH = "//div[@id ='idDiv_SAOTCS_Title']";
    private final String PASSWORD_TEXT_DESCRIPTION_ELEMENT_XPATH = "//div[@id='passwordDesc']";
    private final String PASSWORD_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='passwd']";
    private final String PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH = "//div[@id ='passwordError']";
    private final String DISPLAYED_IDENTITY_ELEMENT_XPATH = "//div[@id ='displayName']";
    private final String EMAIL_DISPLAYED_WITH_VALID_PASSWORD_ELEMENT_XPATH = "//div[@class='table-row']";// move to the appropriate Page class
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//a[@id='idA_SAOTCS_ShowMoreProofs']";// move to the appropriate Page class
    private final String FORGOT_PASSWORD_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_ForgotPassword']";// move to the appropriate Page class
    private final String SIGN_IN_WITH_DIFFERENT_MS_ACCOUNT_LINK_ELEMENT_XPATH = "//a[@id='i1668']";// move to the appropriate Page class
    public static final String EMAIL_CODE_TO_ALTERNATIVE_EMAIL_LINK_ELEMENT_XPATH = "//a[@id='otcLoginLink']";
    public static final String TITLE_TEXT_ELEMENT_XPATH = "//div[@role='heading']";
    public static final String MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH = "//div[@role='alert']//div[@id='usernameError']";

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement nextButtonElement;

    @FindBy(how = How.XPATH, using = VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH)
    private WebElement verifyYourIdentityElement;

    @FindBy(how = How.XPATH, using = PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH)
    private WebElement passwordErrorMessageElement;

    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_LINK_ELEMENT_XPATH)
// move to the appropriate EnterPasswordPage class
    private WebElement forgotPasswordLinkElement;

    @FindBy(how = How.XPATH, using = SIGN_IN_WITH_DIFFERENT_MS_ACCOUNT_LINK_ELEMENT_XPATH)
// move to the appropriate Page class
    private WebElement signInWithADifferentMSAccountLinkElement;

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement signInButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@id='idA_IL_ForgotPassword0']")
    public static WebElement resetPasswordLinkElement;

    public SignInMicrosoftPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInMicrosoftPage openPage() {
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

    public String getTitleOnSignInPage() {
        return driver.findElement(By.xpath(TITLE_TEXT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignInMicrosoftPage signInWithCorrectEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH)));
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist.");
        return new SignInMicrosoftPage();
    }

    public SignInMicrosoftPage signInWithIncorrectEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH)));
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist.");
        return new SignInMicrosoftPage();
    }

    public boolean alertMSAccountMessageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).isDisplayed();
    }
//    method of getting alert with incorrect emailname
//    public boolean alertMSAccountMessageIsDisplayed(MSAccount msAccount) {
//        WebElement logInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
//                .click();
//        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
//        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).isDisplayed();
//    }

    public String getAlertMSAccountAbsenceMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getTitleEnterPasswordMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getPasswordTextDescriptionMessage() {
        return driver.findElement(By.xpath(PASSWORD_TEXT_DESCRIPTION_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getTitleOnProtectionHelpPage() {
        return driver.findElement(By.xpath(TITLE_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public MSAccountProtectionHelpPage enterMSAccountCorrectPassword(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement passwordPlaceholderElement = driver.findElement(xpath(PASSWORD_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(passwordPlaceholderElement)).click();
        passwordPlaceholderElement.sendKeys(msAccount.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        ((By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH))));
        logger.info("The correct password is entered.");
        return new MSAccountProtectionHelpPage();
    }

    public CodeVerificationPage signInButtonClickWithEnteredPassword() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonElement)).click();
        return new CodeVerificationPage();
    }

    public SignInMicrosoftPage enterMSAccountIncorrectPassword(MSAccount msAccount, String incorrectPassword) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement passwordPlaceholderElement = driver.findElement(xpath(PASSWORD_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(passwordPlaceholderElement)).click();
        passwordPlaceholderElement.sendKeys(incorrectPassword);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath(PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH))));
        logger.info("Your account or password is incorrect. Enter it again, reset it via the given link or Email code to the alternative email via given link");
        return new SignInMicrosoftPage();
    }

    public RecoverYourAccountPage recoverAccountViaForgotPasswordLink() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(FORGOT_PASSWORD_LINK_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        return new RecoverYourAccountPage();
    }

    public ResetPasswordPage resetPassword() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(resetPasswordLinkElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(TITLE_RESET_PASSWORD_ELEMENT_XPATH)));
        return new ResetPasswordPage();
    }


    public RecoveringMSAccountDialoguePage recoverMSAccountWithoutTwoStepVerification() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(forgotPasswordLinkElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH)));
        return new RecoveringMSAccountDialoguePage();
    }

    // The method should be reconsidered, that was written for sign in with Outlook.live
    public VerifyingIdentityPage enterPassword(MSAccount msAccount) throws MalformedURLException {
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