package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static java.lang.String.format;
import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_ENTER_PASSWORD_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_SIGN_IN_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.util.ResourceBundleManagerClass.getResourceTitleOnProtectionHelpPage;
import static org.openqa.selenium.By.xpath;

// revise test codes
// This test-case code is implemented for the existing email account not protected yet
// with alternative email or phone number.
// Attention!: There is not developed a function for the selected alternative email address
// existence verification
public class MSAccountProtectionHelpPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    public static final String IDENTITY_ELEMENT_XPATH = "//div[@class='identity']";
    public static final String TITLE_PAGE_ELEMENT_XPATH = "//div[@id='iPageTitle']";
    public static final String TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH = "//div[contains (@id,'iPageTitle') and contains (text(), '%s')]";
    private final String HELP_PROTECTION_TEXT_ELEMENT_XPATH = "//p[@id='idPwdSectioDescrp']";
    private final String LINK_LEARN_MORE_WITHIN_HELP_PROTECTION_TEXT_ELEMENT_XPATH = "//a[@id='iMPBLearnMoreLink']";
    private final String SECURITY_OPTIONS_QUESTION_TEXT_ELEMENT_XPATH = "//div[@id='iProofsContainer']//label";
    public static final String SECURITY_OPTIONS_CONTAINER_ELEMENT_XPATH = "//select[@id='iProofOptions']";
    public static final String EMAIL_SECURITY_OPTION_ELEMENT_XPATH = "//select[@id='iProofOptions']//option[@value='Email']";
    private final String PHONE_SECURITY_OPTION_ELEMENT_XPATH = "//select[@id='iProofOptions']//option[@value='Phone']";
    private final String PHONE_COUNTRY_LIST_OPTION_ELEMENT_XPATH = "//select[@id='DisplayPhoneCountryISO']";
    private final String PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH = "//input[@id='EmailAddress']";
    private final String PLACEHOLDER_PHONE_NUMBER_SECURITY_OPTION_ELEMENT_XPATH = "//input[@id='DisplayPhoneNumber']";//"placeholder" attribute for the text hint in the placeholder
    private final String SECURITY_CODE_SENDING_COMMENT_ELEMENT_XPATH = "//div[@id='iEmailHelp']";
    private final String SKIP_ACCOUNT_PROTECTION_LINK_COMMENT_ELEMENT_XPATH = "//a[@id='iShowSkip']";
    private final String ALERT_MESSAGE_EMAIL_IS_REQUIRED_ELEMENT_XPATH = "//div[@id='EmailError']";
    private final String ALERT_MESSAGE_PHONE_IS_REQUIRED_ELEMENT_XPATH = "//div[@id='PhoneError']";
    private final String INFORMATION_REQUIREMENT_TEXT_ELEMENT_XPATH = "//div[@id='EmailError']";
    public static final String TITLE_STAY_SIGNED_IN_OFFER_ELEMENT_XPATH = "//div[@class='row text-title']";

    @FindBy(how = How.XPATH, using = "//select[@id='DisplayPhoneCountryISO']/option")
    private List<WebElement> phoneCountryListElement;

    @FindBy(how = How.XPATH, using = "//select[@id='iProofOptions']//option")
    private List<WebElement> securityOptionsListElement;

    @FindBy(how = How.XPATH, using = "//input[@id='iNext']")
    private WebElement nextButtonOnProtectionHelpPageElement;

    public MSAccountProtectionHelpPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MSAccountProtectionHelpPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (xpath(format(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH, getResourceTitleOnProtectionHelpPage()))));
        logger.info("Here is Help Account Protection Page. Provide alternative email or phone number. Otherwise you can skip this step for a while.");
        return this;
    }

    public String getDisplayedIdentity() {
        return driver.findElement(By.xpath(IDENTITY_ELEMENT_XPATH)).getAttribute("title");
    }

    public String getTitleOnProtectionHelpPage() {
        return driver.findElement(By.xpath(TITLE_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public MSAccountProtectionHelpPage selectAlternativeEmailAddress() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(SECURITY_OPTIONS_CONTAINER_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(EMAIL_SECURITY_OPTION_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_SECURITY_OPTION_ELEMENT_XPATH)));
        logger.info("The Alternative email is selected as an account protection.");
        return new MSAccountProtectionHelpPage();
    }

    public String getTheSelectedAccountProtectionOption() {
        return driver.findElement(By.xpath(EMAIL_SECURITY_OPTION_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public CodeVerificationPage inputAlternativeEmailAddress(String alternativeEmailAddress) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH))).click();
        WebElement placeholderEmail = driver.findElement(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH));
        placeholderEmail.sendKeys(alternativeEmailAddress);
        logger.info("The Alternative email address is input.");
        return new CodeVerificationPage();
    }

    public String getTheInputAlternativeAccount() {
        return driver.findElement(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH)).getAttribute("value");
    }

    public CodeVerificationPage clickNextButtonOnAccountProtectionPage() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonOnProtectionHelpPageElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH)));
        return new CodeVerificationPage();
    }

    public CodeVerificationPage enterConfirmationCode(MSAccount msAccount) throws MalformedURLException {
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(xpath(SECURITY_OPTIONS_CONTAINER_ELEMENT_XPATH))).click();
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(xpath(EMAIL_SECURITY_OPTION_ELEMENT_XPATH))).click();
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH))).click();
//        WebElement placeholderEmail = driver.findElement(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH));
//        placeholderEmail.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(nextButtonOnProtectionHelpPageElement)).click();
//        logger.info("Enter the sent code for the alternative Email verification.");
        return new CodeVerificationPage();
    }

    public StaySignedInOfferPage skipAccountProtectionStep() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(SKIP_ACCOUNT_PROTECTION_LINK_COMMENT_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath((format(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH, getResourceTitleOnProtectionHelpPage())))));
        return new StaySignedInOfferPage();
    }
}