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

import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_ENTER_PASSWORD_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

// to be continued
public class ResetPasswordPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    public static final String TITLE_RESET_PASSWORD_ELEMENT_XPATH = "//div[@id='iResetPwdHipTitle']";
    private final String RESET_PASSWORD_DESCRIPTION_TEXT_ELEMENT_XPATH = "//*[@id='iExplanationText']";
    private final String ACCOUNT_FOR_RECOVERY_PLACEHOLDER_ELEMENT_XPATH = "//input@id='iSigninName']";
    private final String ALERT_ERROR_ACCOUNT_ELEMENT_XPATH = "//div[@id='pMemberNameErr']";

    @FindBy(how = How.XPATH, using = "//input[@id='resetPwdHipAction']")
    private WebElement nextButtonOnResetPasswordPageElement;

    @FindBy(how = How.XPATH, using = "//input[@id='resetPwdHipCancel']")
    private WebElement cancelButtonOnResetPasswordPageElement;


    public ResetPasswordPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ResetPasswordPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_RESET_PASSWORD_ELEMENT_XPATH)));
        logger.info("Reset Password Page: Recover you account.");
        return this;
    }

    public String getResetPasswordDescriptionText() {
        return driver.findElement(By.xpath(RESET_PASSWORD_DESCRIPTION_TEXT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getResetPasswordPageTitle() {
        return driver.findElement(By.xpath(RESET_PASSWORD_DESCRIPTION_TEXT_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public CodeVerificationPage signInWithCorrectEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement accountForRecoveryPlaceholderElement = driver.findElement(By.xpath(ACCOUNT_FOR_RECOVERY_PLACEHOLDER_ELEMENT_XPATH));
        accountForRecoveryPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonOnResetPasswordPageElement)).click();
        logger.info("Provide verification code.");
        return new CodeVerificationPage();
    }

    public ResetPasswordPage signInWithIncorrectEmail(MSAccount msAccount) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH)));
        WebElement signInPlaceholderElement = driver.findElement(By.xpath(ACCOUNT_FOR_RECOVERY_PLACEHOLDER_ELEMENT_XPATH));
        signInPlaceholderElement.sendKeys(msAccount.getEmailName().concat("@").concat(msAccount.getDomain()));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(nextButtonOnResetPasswordPageElement)).click();
        logger.info("Try entering your Microsoft account again. We don't recognize this one.");
        return new ResetPasswordPage();
    }

    public String getInputMSAccountForRecovery() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_FOR_RECOVERY_PLACEHOLDER_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(ACCOUNT_FOR_RECOVERY_PLACEHOLDER_ELEMENT_XPATH)).getAttribute("value");
    }


    public boolean alertErrorAccountMessageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALERT_ERROR_ACCOUNT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(ALERT_ERROR_ACCOUNT_ELEMENT_XPATH)).isDisplayed();
    }

    public String getAlertErrorAccountMessage(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALERT_ERROR_ACCOUNT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(ALERT_ERROR_ACCOUNT_ELEMENT_XPATH)).getAttribute("textContent");
    }

}