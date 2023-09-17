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
import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInAlternativeMSAccountPage.MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInAlternativeMSAccountPage.getSecurityCodeInInboxMessageReceived;
import static org.microsoft.MSNOutlook.util.ResourceBundleManagerClass.getCodeVerificationPageTitle;
import static org.openqa.selenium.By.xpath;

//Attention!: 1) there isn't developed a function for the alternative email existence verification for the security code to be sent
//           2) additionally an issue appeared today 09 March 2023: identity account to be recovered is not displayed on the page
//           3) CODE_VERIFICATION_TEXT_DESCRIPTION should be changed

// Is it supposed to the pagination approach use?

// edit test codes
public class CodeVerificationPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String IDENTITY_ELEMENT_XPATH = "//div[@id='identityPageBanner']//div[@class='identity']";
    private final String TITLE_PAGE_ELEMENT_XPATH = "//div[@id='iPageTitle']";
    private final String TITLE_CODE_VERIFICATION_PAGE_ELEMENT_XPATH = "//div[contains (@id,'iPageTitle') and contains (text(), '%s')]";
    private final String CODE_VERIFICATION_TEXT_DESCRIPTION_ELEMENT_XPATH = "//div[@id='iEnterSubhead']";
    private final String EMAIL_ADDRESS_TEXT_FOR_CODE_VERIFICATION_ELEMENT_XPATH = CODE_VERIFICATION_TEXT_DESCRIPTION_ELEMENT_XPATH + "/span[@class='dirltr_inline']";
    private final String LINK_I_DONT_HAVE_A_CODE_ELEMENT_XPATH = "//a[@id='iShowSendCode']";
    private final String PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH = "//input[@id='iOttText']";
    private final String ERROR_ALERT_MESSAGE_ELEMENT_XPATH = "//div[@id='iVerificationErr']";
    private final String INFORMATION_REQUIREMENT_TEXT_ELEMENT_XPATH = "//div[@id='EmailError']";

    @FindBy(how = How.XPATH, using = "")
    private WebElement securityCodePlaceholderElement;

    @FindBy(how = How.XPATH, using = "//input[@id='iNext']")
    private WebElement nextButtonOnCodeVerificationPageElement;

    @FindBy(how = How.XPATH, using = "//input[@id='idSIButton9']")
    private WebElement signInButtonOnCodeVerificationPageElement;

    public CodeVerificationPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CodeVerificationPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(format(TITLE_CODE_VERIFICATION_PAGE_ELEMENT_XPATH, getCodeVerificationPageTitle()))));
        logger.info("Code verification Page.");
        return this;
    }

    // There is no identity account displayed for recovery: the account display is excluded
    public String getDisplayedIdentity() {
        return driver.findElement(By.xpath(IDENTITY_ELEMENT_XPATH)).getAttribute("title");
    }

    public String getCodeVerificationTextDescription() {
        return driver.findElement(By.xpath(CODE_VERIFICATION_TEXT_DESCRIPTION_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getEmailAddressTheCodeWasSent() {
        return driver.findElement(By.xpath(EMAIL_ADDRESS_TEXT_FOR_CODE_VERIFICATION_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getErrorAlertMessage() {
        return driver.findElement(By.xpath(ERROR_ALERT_MESSAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public CodeVerificationPage inputAlternativeEmailAddress(String alternativeEmailAddress) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH))).click();
        WebElement placeholderEmail = driver.findElement(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH));
        placeholderEmail.sendKeys(alternativeEmailAddress);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonOnCodeVerificationPageElement)).click();
        return new CodeVerificationPage();
    }

    public SignedInCurrentMSAccountPage enterTheReceivedSecurityCode() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(securityCodePlaceholderElement)).click();
        securityCodePlaceholderElement.sendKeys(getSecurityCodeInInboxMessageReceived());
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new SignedInCurrentMSAccountPage();
    }

    public SignedInCurrentMSAccountPage signInButtonClick() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(securityCodePlaceholderElement)).click();
        securityCodePlaceholderElement.sendKeys(getSecurityCodeInInboxMessageReceived());
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new SignedInCurrentMSAccountPage();
    }


}