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

import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class BreakPasswordsPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String BREAK_PASSWORDS_PAGE_CONTAINER_ELEMENT_XPATH = "//div[@class='upsellAuthPageContainer PageContainer']";
    private final String SKIP_BREAK_PASSWORDS_OFFER_LINK_ELEMENT_XPATH = BREAK_PASSWORDS_PAGE_CONTAINER_ELEMENT_XPATH + "//a[@class='secondary-text']";
    private final String TITLE_BREAK_PASSWORDS_PAGE_ELEMENT_XPATH = "//div[@id='setupAppTitle']";
    private final String SETUP_APPLICATION_DESCRIPTION_ELEMENT_XPATH = "//div[@id='setupAppDesc']";
    private final String BREAK_FREE_FROM_PASSWORDS_BUTTON_ELEMENT_XPATH = "//input[@id='iNext']";
    private final String AUTHENTICATOR_APP_PRESENTATION_ELEMENT_XPATH = "//img[@id='authenticatorUpsellImg']"; // testing img


    @FindBy(how = How.XPATH, using = "//input[@id='iNext']")
    private WebElement nextButtonOnProtectionHelpPageElement;

    public BreakPasswordsPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public BreakPasswordsPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(TITLE_MS_ACCOUNT_PROTECTION_HELP_PAGE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(TITLE_BREAK_PASSWORDS_PAGE_ELEMENT_XPATH)));
        logger.info("Here you can break free from passwords.");
        return this;
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath(TITLE_BREAK_PASSWORDS_PAGE_ELEMENT_XPATH)).getAttribute("outerText");
    }

    public String getSetupAppDescription() {
        return driver.findElement(By.xpath(SETUP_APPLICATION_DESCRIPTION_ELEMENT_XPATH)).getAttribute("outertext");
    }

    public String getTextOfBreakFreeFromPasswordsButton() {
        return driver.findElement(By.xpath(BREAK_FREE_FROM_PASSWORDS_BUTTON_ELEMENT_XPATH)).getAttribute("outerText");
    }

    public String getTextOfSkipBreakPasswordsOfferLink() {
        return driver.findElement(By.xpath(SKIP_BREAK_PASSWORDS_OFFER_LINK_ELEMENT_XPATH)).getAttribute("defaultValue");
    }

    public String getAuthenticatorAppPresentationImage() {
        return driver.findElement(By.xpath(AUTHENTICATOR_APP_PRESENTATION_ELEMENT_XPATH)).getAttribute("defaultValue");
    }

    //    there is a function's back-draw: it should ask you if you are sure of the decision made
    public SignedInAlternativeMSAccountPage skipBreakPasswordsOffer() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(SKIP_BREAK_PASSWORDS_OFFER_LINK_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(TITLE_BREAK_PASSWORDS_PAGE_ELEMENT_XPATH)));
        return new SignedInAlternativeMSAccountPage();
    }

    public SignedInAlternativeMSAccountPage breakPasswordsOffer() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(BREAK_FREE_FROM_PASSWORDS_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(TITLE_BREAK_PASSWORDS_PAGE_ELEMENT_XPATH)));
        return new SignedInAlternativeMSAccountPage();
    }
}