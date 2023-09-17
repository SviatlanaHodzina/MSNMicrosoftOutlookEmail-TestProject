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

import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_ENTER_PASSWORD_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class RecoveringMSAccountDialoguePage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    // Take the Microsoft logo verifying methods to the "utils" folder
    // "alt": attribute for displayed logo text
    public static final String LOGO_MICROSOFT_COMPANY_TITLE_ELEMENT_XPATH = "//img[@class='logo']";

    // images verifying is providing with other methods
    // "Logo_Image_Microsoft": key for the bundle name "References"
    // [ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);
    // resourceBundle.getString("Enter password");]
    public static final String LOGO_IMAGE_MICROSOFT_COMPANY_ELEMENT_XPATH = "//img[@src='%s']";

    public static final String RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_CONTENT_ELEMENT_XPATH = "//form[@id='pageDialogForm_0']";
    public static final String TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH = "//div[@id='recoveryPlusTitle']";
    public static final String MS_ACCOUNT_FOR_RECOVERING_REQUEST_ELEMENT_XPATH = "//div[@id='iWlidHeading']";
    public static final String MS_ACCOUNT_FOR_RECOVERING_OPTIONS_TEXT_ELEMENT_XPATH = "//div[@id='iWlidSection']/label";
    public static final String MS_ACCOUNT_PLACEHOLDER_ELEMENT_XPATH = "//input[@id='AccountNameInput']";
    public static final String NOTE_COMMENT_UNDER_MS_ACCOUNT_PLACEHOLDER_ELEMENT_XPATH = "//div[@id='iCM2SVNote']";
    public static final String CONTACT_REQUEST_TITLE_ELEMENT_XPATH = "//div[@id='iCMailHeading']";
    public static final String CONTACT_EMAIL_REQUEST_DESCRIPTION_ELEMENT_XPATH = "//div[@id='iCMailDescription']";

    // This text might be redundant
    public static final String EMAIL_CONTACT_TEXT_ELEMENT_XPATH = "//div[@id='iCMailSection']/label";

    public static final String PLACEHOLDER_CONTACT_EMAIL_ELEMENT_XPATH = "//input[@id='iCMailInput']";
    public static final String COMMENT_UNDER_PLACEHOLDER_CONTACT_EMAIL_ELEMENT_XPATH = "//div[@id='iCMailSection']/div[@class='text-caption']";

    // This link for creating new account with Outlook.com is given incorrect

    public static final String LINK_CREATE_NEW_EMAIL_WITH_OUTLOOK_COM_ELEMENT_XPATH = "//div[@id='iCMailSection']//a";
    public static final String CAPTCHA_VERIFICATION_TEXT_ELEMENT_XPATH = "//label[@id='wlspispHipInstructionContainer']";
    public static final String NEW_CAPTCHA_VERIFICATION_BUTTON_ELEMENT_XPATH = "//a[@id='wlspispHIPNew42f258726114412ca3fcc7b160ec3323']";
    public static final String AUDIO_VERIFICATION_BUTTON_ELEMENT_XPATH = "//a[@id='wlspispHIPToA71d5f7c463584a5ca8ed948023b03265']";

    // there have to be used captcha skipping method for automation testing process
    public static final String IMAGE_CAPTCHA_VERIFICATION_ELEMENT_XPATH = "//img[@id='wlspispHIPBimg0b13796d085134f1c96e0c4766d05947f0']";
    public static final String PLACEHOLDER_FOR_THE_INPUT_OF_DISPLAYED_ON_CAPTCHA_IMAGE_ELEMENT_XPATH = "//input[@id='wlspispSolutionElement0f667171c59d4231b6d57ad29deb0dcb']";
    public static final String SIGN_IN_LINK_ON_RECOVER_DIALOGUE_PAGE_ELEMENT_XPATH = "//a[@id='c_csigninsignout']";

    public static final String RECOVER_MICROSOFT_ACCOUNT_DIALOGUE_ELEMENT_XPATH = "//form[@id='pageDialogForm_0']";

    @FindBy(how = How.XPATH, using = "//input[@id='recoveryPlusLandingAction']")
    private WebElement nextButtonOnRecoveringMSAccountDialoguePageElement;

    //cancel button is absent
    @FindBy(how = How.XPATH, using = "")
    private WebElement cancelButtonOnRecoveringMSAccountDialoguePageElement;

       public RecoveringMSAccountDialoguePage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public RecoveringMSAccountDialoguePage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Recover your account");
        return this;
    }

    public String getTitleOnRecoverAccountDialoguePage() {
        return driver.findElement(By.xpath(TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getAccountForRecoveringRequestText() {
        return driver.findElement(By.xpath(MS_ACCOUNT_FOR_RECOVERING_REQUEST_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getAccountForRecoveringOptionsText() {
        return driver.findElement(By.xpath(MS_ACCOUNT_FOR_RECOVERING_OPTIONS_TEXT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getNoteCommentUnderAccountPlaceholder() {
        return driver.findElement(By.xpath(NOTE_COMMENT_UNDER_MS_ACCOUNT_PLACEHOLDER_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getContactRequestTitle() {
        return driver.findElement(By.xpath(CONTACT_REQUEST_TITLE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getContactEmailRequestDescription() {
        return driver.findElement(By.xpath(CONTACT_EMAIL_REQUEST_DESCRIPTION_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getCommentUnderPlaceholderContactEmail() {
        return driver.findElement(By.xpath(COMMENT_UNDER_PLACEHOLDER_CONTACT_EMAIL_ELEMENT_XPATH)).getAttribute("textContent");
    }
    //code to be continued
}