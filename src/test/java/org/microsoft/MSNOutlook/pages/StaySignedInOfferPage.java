package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_SIGN_IN_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInAlternativeMSAccountPage.ALTERNATIVE_ACCOUNT_BUTTON_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInCurrentMSAccountPage.*;
import static org.openqa.selenium.By.xpath;

public class StaySignedInOfferPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String IDENTITY_DISPLAY_ELEMENT_XPATH = "//div[@id='displayName']";
    public static final String TITLE_PAGE_ELEMENT_XPATH = "//div[@role='heading']";
    public static final String OFFER_TO_STAY_SIGN_IN_DESCRIPTION_PAGE_ELEMENT_XPATH = "//div[@id='KmsiDescription']";
    public static final String CHECK_BOX_DONT_SHOW_OFFER_TO_STAY_SIGN_IN_ELEMENT_XPATH = "//input[@id='KmsiCheckboxField']";
    public static final String TEXT_DONT_SHOW_OFFER_TO_STAY_SIGN_IN_ELEMENT_XPATH = "//div[@id='lightbox']//label//span";
    public static final String YES_BUTTON_ON_STAY_SIGN_IN__OFFER_ELEMENT_XPATH = "//input[@id='idSIButton9']";
    public static final String NO_BUTTON_ON_STAY_SIGN_IN__OFFER_ELEMENT_XPATH = "//input[@id='idBtn_Back']";


    @FindBy(how = How.XPATH, using = YES_BUTTON_ON_STAY_SIGN_IN__OFFER_ELEMENT_XPATH)
    private WebElement yesButtonOnStaySignedInOfferPageElement;

    @FindBy(how = How.XPATH, using = NO_BUTTON_ON_STAY_SIGN_IN__OFFER_ELEMENT_XPATH)
    private WebElement noButtonOnStaySignedInOfferPageElement;

    public StaySignedInOfferPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public StaySignedInOfferPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        logger.info("Stay Signed In?");
        return this;
    }

    public String getDisplayedIdentity() {
        return driver.findElement(By.xpath(IDENTITY_DISPLAY_ELEMENT_XPATH)).getAttribute("title");
    }

    public String getTitleStaySignedInOfferPage() {
        return driver.findElement(By.xpath(TITLE_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignedInCurrentMSAccountPage yesStaySignedIn() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(yesButtonOnStaySignedInOfferPageElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new SignedInCurrentMSAccountPage();
    }

    public SignedInAlternativeMSAccountPage yesStaySignedInAlternative() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(yesButtonOnStaySignedInOfferPageElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(ALTERNATIVE_ACCOUNT_BUTTON_ELEMENT_XPATH)));
        return new SignedInAlternativeMSAccountPage();
    }

    //Security issue: it's required to report it on the microsoft project on my Atlassian board.
    // here is bug that keeps you signed in your account with "no" answer for the offer to stay signed it

    public SignedInCurrentMSAccountPage noStaySignedIn() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(noButtonOnStaySignedInOfferPageElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new SignedInCurrentMSAccountPage();
    }

    public String getSignedInAccountName() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH))).click();
        return driver.findElement(By.xpath(SIGNED_IN_CURRENT_MS_ACCOUNT_NAME_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

//    public CodeVerificationPage inputAlternativeEmailAddress(MSAccount msAccount) throws MalformedURLException {
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH)));
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH))).click();
//        WebElement placeholderEmail = driver.findElement(By.xpath(PLACEHOLDER_EMAIL_ADDRESS_SECURITY_OPTION_ELEMENT_XPATH));
//        placeholderEmail.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.elementToBeClickable(nextButtonOnProtectionHelpPageElement)).click();
//        logger.info("Enter the sent code for the alternative Email verification.");
//        return new CodeVerificationPage();
//    }
}