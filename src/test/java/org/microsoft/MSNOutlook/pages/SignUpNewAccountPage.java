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

import static java.time.Duration.ofSeconds;
import static org.microsoft.MSNOutlook.pages.OutlookLivePage.MAIN_PAGE_MICROSOFT_CONTENT_ELEMENT_XPATH;

public class SignUpNewAccountPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String CREATE_OUTLOOK_ACCOUNT_PAGE_CONTENT_ELEMENT_XPATH = "//div[@class='middle app']";
    private final String DOMAIN_BOX_LIST_CONTAINER_ELEMENT_XPATH = "//*[@class='liveDomainBox col-xs-10']";
    private final String OUTLOOK_OPTION_DOMAIN_ELEMENT_XPATH = "//select[@id='LiveDomainBoxList']//option[@value='%s']";
    private final String HOTMAIL_OPTION_DOMAIN_ELEMENT_XPATH = "//select[@id='LiveDomainBoxList']//option[@value='%s']";
    private final String THIS_EMAIL_ADDRESS_IS_BUSY_MESSAGE_ELEMENT_XPATH = "//div[@id='MemberNameError']";
    private final String SELECT_ANOTHER_AVAILABLE_EMAIL_ADDRESS_LINK_ELEMENT_XPATH = "//a[@id='suggLink']";

    public SignUpNewAccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='CredentialsPageTitle']")
    private WebElement createAccountTitleElement;

    @FindBy(how = How.XPATH, using = "//input[@id='iSignupAction']")
    private WebElement signUpNextButtonElement;

    @FindBy(how = How.XPATH, using = "//input[@id='MemberName']")
    private WebElement newEmailPlaceHolderElement;

    @FindBy(how = How.XPATH, using = DOMAIN_BOX_LIST_CONTAINER_ELEMENT_XPATH)
    private WebElement domainBoxListContainerElement;

    @FindBy(how = How.XPATH, using = "//div[@id='MemberNameError']")
    private WebElement allertMessageMemberNameErrorElement;

    @FindBy(how = How.XPATH, using = "//select[@id='LiveDomainBoxList']//option")
    private List<WebElement> domainOptionListElement;

    @FindBy(how = How.XPATH, using = "//input[@id='iSuggCancel']")
    private WebElement backButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@id='Suggestions']//div/a")
    private List<WebElement> suggestionsMemberNameListElement;

    public SignUpNewAccountPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_OUTLOOK_ACCOUNT_PAGE_CONTENT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated((By) createAccountTitleElement));
        logger.info(driver.findElement((By) createAccountTitleElement).getAttribute("textContent"));
        return this;
    }

    public SignUpNewAccountPage inputNewEmailName(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MAIN_PAGE_MICROSOFT_CONTENT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (newEmailPlaceHolderElement)).click();
        newEmailPlaceHolderElement.sendKeys(msAccount.getEmailName());
        logger.info("New Email account name is:" + newEmailPlaceHolderElement.getAttribute("value"));
        return this;
    }

    public SignUpNewAccountPage selectDomainName() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(DOMAIN_BOX_LIST_CONTAINER_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(domainOptionListElement));
        for (WebElement option : domainOptionListElement) {
            domainOptionListElement.get(0).click();
            new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(domainOptionListElement.get(0))).click();
        }
        logger.info("Selected domain name is " + newEmailPlaceHolderElement.getAttribute("value"));
        return this;
    }

    public CreateAPasswordPage signUp(MSAccount msAccount) throws MalformedURLException {
        inputNewEmailName(msAccount);
        selectDomainName();
        signUpNextButtonElement.click();
        logger.info("You are in a 'Create a Password' Page");
        return new CreateAPasswordPage();
    }

    public boolean thisEmailAddressIsBusy() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(THIS_EMAIL_ADDRESS_IS_BUSY_MESSAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(THIS_EMAIL_ADDRESS_IS_BUSY_MESSAGE_ELEMENT_XPATH)).isDisplayed();
    }

    public String getTheMessageThatThisEmailAddressIsBusy() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(THIS_EMAIL_ADDRESS_IS_BUSY_MESSAGE_ELEMENT_XPATH)));
        String message = driver.findElement(By.xpath(THIS_EMAIL_ADDRESS_IS_BUSY_MESSAGE_ELEMENT_XPATH)).getAttribute("textContent");
        logger.info(message);
        return message;
    }

    public SignUpNewAccountPage selectOneOfSuggestedAvailableEmailAddresses(int indexOfAvailableEmail) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(SELECT_ANOTHER_AVAILABLE_EMAIL_ADDRESS_LINK_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(suggestionsMemberNameListElement));
        suggestionsMemberNameListElement.forEach(email -> suggestionsMemberNameListElement.get(indexOfAvailableEmail).click());
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated((By) newEmailPlaceHolderElement));
        logger.info("Selected available email address is " + newEmailPlaceHolderElement.getText());
        return this;
    }

    public int getCountOfSuggestedEmails() {
        logger.info("Count of suggested available emails is " + suggestionsMemberNameListElement.size());
        return suggestionsMemberNameListElement.size();
    }

    public String getOneOfSuggestedEmailAddresses(int indexOfAvailableEmail) {
        suggestionsMemberNameListElement.forEach(email -> suggestionsMemberNameListElement.get(indexOfAvailableEmail).click());
        logger.info("The selected available email address is " + suggestionsMemberNameListElement.get(indexOfAvailableEmail).toString());
        return suggestionsMemberNameListElement.get(indexOfAvailableEmail).toString();
    }
}