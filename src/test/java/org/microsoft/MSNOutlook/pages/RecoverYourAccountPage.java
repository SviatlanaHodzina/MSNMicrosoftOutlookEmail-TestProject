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

import java.net.MalformedURLException;
import java.time.Duration;

import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.RecoveringMSAccountDialoguePage.TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.ResetPasswordPage.TITLE_RESET_PASSWORD_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_ENTER_PASSWORD_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_SIGN_IN_PAGE_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class RecoverYourAccountPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    // Take the Microsoft logo verifying methods to the "utils" folder
    // "alt": attribute for displayed logo text
    public static final String LOGO_MICROSOFT_COMPANY_TITLE_ELEMENT_XPATH = "//img[@class='logo']";

    // images verifying is providing with other methods
    // "Logo_Image_Microsoft": key for the bundle name "References"
    // [ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);
    // resourceBundle.getString("Enter password");]
    public static final String LOGO_IMAGE_MICROSOFT_COMPANY_ELEMENT_XPATH = "//img[@class='logo']";
    public static final String LOGO_IMAGE_MICROSOFT_SRC_ADDRESS = "https://acctcdn.msauth.net/images/microsoft_logo_7lyNn7YkjJOP0NwZNw6QvQ2.svg";

    public static final String TITLE_RECOVER_YOUR_ACCOUNT_PAGE_ELEMENT_XPATH = "//*[@id='iResetPwdHipTitle']";
    public static final String RECOVER_ACCOUNT_DESCRIPTION_PAGE_ELEMENT_XPATH = "//div[@id='iExplanationText']";
    private final String PLACEHOLDER_ACCOUNT_TO_RECOVER_ELEMENT_XPATH = "//input[@id='iSigninName']";
    private final String NEXT_BUTTON_TO_RESET_PASSWORD_ELEMENT_XPATH = "//input[@id='resetPwdHipAction']";
    private final String CANCEL_BUTTON_TO_RESET_PASSWORD_ELEMENT_XPATH = "//input[@id='resetPwdHipCancel']";

    @FindBy(how = How.XPATH, using = NEXT_BUTTON_TO_RESET_PASSWORD_ELEMENT_XPATH)
    private WebElement nextButtonToRecoverPasswordElement;

    @FindBy(how = How.XPATH, using = CANCEL_BUTTON_TO_RESET_PASSWORD_ELEMENT_XPATH)
    private WebElement cancelButtonToRecoverPasswordElement;


    public RecoverYourAccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public RecoverYourAccountPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RECOVER_ACCOUNT_DESCRIPTION_PAGE_ELEMENT_XPATH)));
        logger.info("Enter Email, phone number or Skype name to recover account");
        return this;
    }

    public String getTitleRecoverAccountPage() {
        return driver.findElement(By.xpath(TITLE_RECOVER_YOUR_ACCOUNT_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getRecoverAccountDescription() {
        return driver.findElement(By.xpath(RECOVER_ACCOUNT_DESCRIPTION_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public RecoverYourAccountPage enterAccountEmailToRecover(MSAccount msAccount) throws MalformedURLException {
        WebElement enterAccountInformationElement = driver.findElement(By.xpath(PLACEHOLDER_ACCOUNT_TO_RECOVER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until((ExpectedConditions.elementToBeClickable(enterAccountInformationElement))).click();
        enterAccountInformationElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        logger.info("Email account does exist.");
        return new RecoverYourAccountPage();
    }

    public VerifyingIdentityPage nextButtonClickToRecoverAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonToRecoverPasswordElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TITLE_RECOVER_YOUR_ACCOUNT_PAGE_ELEMENT_XPATH)));
        logger.info("Verify Identity Page.");
        return new VerifyingIdentityPage();
    }

    public SignInMicrosoftPage cancelButtonClickToRecoverAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(cancelButtonToRecoverPasswordElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TITLE_RECOVER_YOUR_ACCOUNT_PAGE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)));
        logger.info("You are returned to Sign in Page.");
        return new SignInMicrosoftPage();
    }
}