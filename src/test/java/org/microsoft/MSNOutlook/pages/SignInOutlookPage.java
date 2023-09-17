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

import static org.microsoft.MSNOutlook.pages.OutlookLivePage.SIGN_IN_OUTLOOK_ACCOUNT_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class SignInOutlookPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String TITLE_SIGN_IN_PAGE_ELEMENT_XPATH = "//div[@data-viewid='1']//div[@role='heading']";
    private final String TITLE_ENTER_PASSWORD_ELEMENT_XPATH = "//div[@data-viewid='2']//div[@role='heading']";
    private final String SIGN_IN_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='loginfmt']";
    private final String VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH = "//div[@id ='idDiv_SAOTCS_Title']";
    private final String PASSWORD_PLACEHOLDER_ELEMENT_XPATH = "//input[@name='passwd']";
    private final String PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH = "//div[@id ='passwordError']";
    private final String DISPLAYED_IDENTITY_ELEMENT_XPATH = "//div[@id ='displayName']";
    private final String EMAIL_DISPLAYED_WITH_VALID_PASSWORD_ELEMENT_XPATH = "//div[@class='table-row']";// move to the appropriate Page class
    private final String SHOW_MORE_VERIFICATION_METHODS_LINK_ELEMENT_XPATH = "//a[@id='idA_SAOTCS_ShowMoreProofs']";// move to the appropriate Page class
    private final String FORGOT_PASSWORD_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_ForgotPassword']";// move to the appropriate Page class
    public static final String TITLE_TEXT_ELEMENT_XPATH = "//div[@role='heading']";
    public static final String MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH = "//div[@role='alert']//div[@id='usernameError']";


    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement nextButtonElement;

    @FindBy(how = How.XPATH, using = VERIFY_YOUR_IDENTITY_MESSAGE_ELEMENT_XPATH)
    private WebElement verifyYourIdentityElement;

    @FindBy(how = How.XPATH, using = PASSWORD_ERROR_MESSAGE_ELEMENT_XPATH)
    private WebElement passwordErrorMessageElement;

    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_LINK_ELEMENT_XPATH)// move to the appropriate Page class
    private WebElement forgotPasswordLinkElement;

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement signInButtonElement;

    public SignInOutlookPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInOutlookPage openPage() {
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
//    failed: waiting for visibility of element located by By.xpath:SIGN_IN_PLACEHOLDER_ELEMENT_XPATH
    public SignInOutlookPage signInWithCorrectOutlookEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH)));
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist.");
        return new SignInOutlookPage();
    }

    public SignInOutlookPage signInAlternativeOutlookEmail(String alternativeEmail) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(SIGN_IN_OUTLOOK_ACCOUNT_ELEMENT_XPATH)));
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(alternativeEmail);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist.");
        return new SignInOutlookPage();
    }

//    failed: waiting for element to be clickable: By.xpath: SIGN_IN_PLACEHOLDER_ELEMENT_XPATH
    public SignInOutlookPage signInWithIncorrectOutlookEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH))).click();
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist.");
        return new SignInOutlookPage();
    }

    public boolean alertMSOutlookAccountMessageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).isDisplayed();
    }

    public String getAlertMSAccountAbsenceMessage(MSAccount msAccount) {
        WebElement logInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        return driver.findElement(By.xpath(MICROSOFT_ACCOUNT_DOES_NOT_EXIST_ALERT_ELEMENT_XPATH)).getAttribute("textContent");
    }
    // previous version
    public SignInOutlookPage signInWithExistingEmail(MSAccount msAccount) throws MalformedURLException {
        WebElement logInPlaceholderElement = driver.findElement(By.xpath(SIGN_IN_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(logInPlaceholderElement))
                .click();
        logInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonElement)).click();
        logger.info("Email account does exist. Now enter password");
        return new SignInOutlookPage();
    }

    public String getTitleEnterPasswordMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");
    }

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

//    This method resulted randomly: either it passed to the "Stay signed in?" offer Page either to MS Account protection help Page.
//    This option implies having already protection option for this email address and results in offering to stay signed it.
    public MSAccountProtectionHelpPage enterMSAlternativeEmailPassword(String passwordOfAltEmail) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement passwordPlaceholderElement = driver.findElement(xpath(PASSWORD_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(passwordPlaceholderElement)).click();
        passwordPlaceholderElement.sendKeys(passwordOfAltEmail);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        ((By.xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH))));
        logger.info("The correct password of alternative email is entered.");
        return new MSAccountProtectionHelpPage();
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