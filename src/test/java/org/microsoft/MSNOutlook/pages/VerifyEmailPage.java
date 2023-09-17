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

import static org.microsoft.MSNOutlook.pages.SignInMicrosoftPage.TITLE_ENTER_PASSWORD_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class VerifyEmailPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    public static final String TITLE_EMAIL_VERIFICATION_PAGE_ELEMENT_XPATH = "//div[@id='proofConfirmationTitle']";
    private final String PROOF_CONFIRTMATION_DESCRIPTION_ELEMENT_XPATH = "//div[@id='proofConfirmationDesc']";
    private final String ALTERNATIVE_EMAIL_PLACEHOLDER_ELEMENT_XPATH = "//input[@id='proofConfirmationText']";
    private final String PASSWORD_USE_LINK_ELEMENT_XPATH = "//a[@id='idA_PWD_SwitchToPassword']";
    private final String I_HAVE_A_CODE_LINK_ELEMENT_XPATH = "//a[@id='proofConfirmationToggle']";
    private final String SEND_CODE_BUTTON_ELEMENT_XPATH = "//input[@id='idSIButton9']";
    private final String EMAIL_CODE_TO_ALTERNATIVE_EMAIL_LINK_ELEMENT_XPATH = "//a[@id='otcLoginLink']";

    @FindBy(how = How.XPATH, using = SEND_CODE_BUTTON_ELEMENT_XPATH)
    private WebElement sendCodeButtonElement;

    public VerifyEmailPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public VerifyEmailPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_EMAIL_VERIFICATION_PAGE_ELEMENT_XPATH)));
        logger.info("Verify your email for sending a verification code");
        return this;
    }

    public String getTitleVerifyEmailPage() {
        return driver.findElement(By.xpath(TITLE_EMAIL_VERIFICATION_PAGE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getProofConfirmationDescription() {
        return driver.findElement(By.xpath(PROOF_CONFIRTMATION_DESCRIPTION_ELEMENT_XPATH))
                .getAttribute("textContent");
    }

    public String getInputAlternativeEmailForCode() {
        return driver.findElement(By.xpath(ALTERNATIVE_EMAIL_PLACEHOLDER_ELEMENT_XPATH))
                .getAttribute("value");
    }

    public VerifyEmailPage inputAlternativeEmailForCodeToSend(String alternativeEmailAddress) throws MalformedURLException {
        WebElement alternativeEmailPlaceholderElement = driver.findElement(By.xpath(ALTERNATIVE_EMAIL_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(alternativeEmailPlaceholderElement)).click();
        alternativeEmailPlaceholderElement.sendKeys(alternativeEmailAddress);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(sendCodeButtonElement));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ALTERNATIVE_EMAIL_PLACEHOLDER_ELEMENT_XPATH)));
        return new VerifyEmailPage();
    }

    public CodeEnterPage sendCodeToAlternativeEmail() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable((xpath(EMAIL_CODE_TO_ALTERNATIVE_EMAIL_LINK_ELEMENT_XPATH)))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated((xpath(TITLE_ENTER_PASSWORD_ELEMENT_XPATH))));
        logger.info("Verify your email via following the given instruction.");
        return new CodeEnterPage();
    }
}