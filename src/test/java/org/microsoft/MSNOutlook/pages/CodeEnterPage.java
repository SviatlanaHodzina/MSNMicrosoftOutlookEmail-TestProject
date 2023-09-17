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

import static java.lang.String.format;
import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInAlternativeMSAccountPage.MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignedInAlternativeMSAccountPage.getSecurityCodeInInboxMessageReceived;
import static org.microsoft.MSNOutlook.util.ResourceBundleManagerClass.getCodeVerificationPageTitle;
import static org.openqa.selenium.By.xpath;

//Attention!: there isn't developed a function for the alternative email existence verification for the security code to be sent

// Is it supposed to the pagination approach use?

// edit test codes
public class CodeEnterPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String ACCOUNT_ELEMENT_XPATH = "//div[@id='displayName']";
    private final String TITLE_CODE_ENTER_PAGE_ELEMENT_XPATH = "//div[@id='loginHeader']";
    private final String CODE_VERIFICATION_DESCRIPTION_ELEMENT_XPATH = "//div[@class='text-body']//span[@id='otcDesc']";
    private final String CODE_PLACEHOLDER__ELEMENT_XPATH = "//input[@id='idTxtBx_OTC_Password']";
    private final String ERROR_INPUT_CODE_MESSAGE_ELEMENT_XPATH = "//div[@id='idTd_OTCC_Error_OTC']";
    private final String USE_PASSWORD_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_SwitchToPassword']";
    private final String SIGN_IN_BUTTON_WITH_CODE_ENTERED_ELEMENT_XPATH = "//input[@id='idSIButton9']";

    @FindBy(how = How.XPATH, using = CODE_PLACEHOLDER__ELEMENT_XPATH)
    private WebElement securityCodePlaceholderElement;

    @FindBy(how = How.XPATH, using = SIGN_IN_BUTTON_WITH_CODE_ENTERED_ELEMENT_XPATH)
    private WebElement signInButtonWithCodeEnteredElement;

    public CodeEnterPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CodeEnterPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_CODE_ENTER_PAGE_ELEMENT_XPATH)));
        logger.info("Code Enter Page.");
        return this;
    }

    public String getDisplayedAccountName() {
        return driver.findElement(By.xpath(ACCOUNT_ELEMENT_XPATH)).getAttribute("title");
    }

    public String getTitleCodeEnterPage() {
        return driver.findElement(By.xpath(TITLE_CODE_ENTER_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getErrorInputCodeMessage() {
        return driver.findElement(By.xpath(ERROR_INPUT_CODE_MESSAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public CodeEnterPage enterTheReceivedCode() throws MalformedURLException {
        WebElement codeReceived = driver.findElement(By.xpath(ERROR_INPUT_CODE_MESSAGE_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(codeReceived)).click();
        codeReceived.sendKeys(getSecurityCodeInInboxMessageReceived());
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new CodeEnterPage();
    }

    public SignedInCurrentMSAccountPage signInButtonClick() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonWithCodeEnteredElement)).click();
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        return new SignedInCurrentMSAccountPage();
    }
}