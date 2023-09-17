package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.microsoft.MSNOutlook.pages.MicrosoftPage.SIGN_IN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.SignInOutlookPage.TITLE_TEXT_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class LogInMicrosoftPage extends AbstractPage {
    public final Logger logger = LogManager.getRootLogger();

    private final String SIGN_IN_PAGE_CONTENT_ELEMENT_XPATH = "//div[@data-viewid='1']";
    private final String BUTTON_SIGNED_IN_ACCOUNT_ELEMENT_XPATH = "//button[@id='mectrl_main_trigger']";
    private final String ACCOUNT_DETAILS_ELEMENT_XPATH = "//div[@id='mectrl_main_body']";
    private final String ACCOUNT_NAME_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_primary']";
    private final String ACCOUNT_EMAIL_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_secondary']";
    private final String MY_ACCOUNT_LINK_ELEMENT_XPATH = "//a[@id='mectrl_viewAccount']";
    private final String MY_PROFILE_LINK_ELEMENT_XPATH = "//a[@id='mectrl_ShellAboutMe']";
    private final String SIGN_IN_WITH_DIFFERENT_ACCOUNT_ELEMENT_XPATH = "//a[@id='mectrl_signInItem']";
    private final String SIGN_OUT_ELEMENT_XPATH = "//a[@id='mectrl_body_signOut']";
    private final String LOGO_TITLE_PAGE_ELEMENT_XPATH = "//img[@alt='Microsoft']";
    private final String TITLE_SIGN_IN_PAGE_ELEMENT_XPATH = "//div[@data-viewid='1']//div[@role='heading']";
    private final String CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH = "//a[@id='signup']";
    public static final String LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH = "//a[@id='mectrl_main_trigger']";

    @FindBy(how = How.XPATH, using = "//div[@id='lightbox']")
    private WebElement logInPageDataContainerElement;

    public LogInMicrosoftPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LogInMicrosoftPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated((By) logInPageDataContainerElement));
        logger.info(getTitleSignInOfLoginInPage());
        return this;
    }

    public String getTitleLogoOfLoginInPage() {
        return driver.findElement(xpath(LOGO_TITLE_PAGE_ELEMENT_XPATH)).getAttribute("alt");
    }

    public String getTitleSignInOfLoginInPage() {
        return driver.findElement(xpath(TITLE_SIGN_IN_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }


    // here it's required extended WAIT_TIMEOUT_SECONDS for the element LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH
    // to be considered either visible or clickable : verified 24 Febr. 2023
    // logger log4j registers wrong message of invisibility or non-clickable sign in WebElement: report 25 Febr. 2023
    public SignInMicrosoftPage signInYourMicrosoftAccount() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH)))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath(SIGN_IN_MICROSOFT_ACCOUNT_ELEMENT_XPATH)));
        logger.info("Sign in with your Email, phone or Skype");
        return new SignInMicrosoftPage();
    }

    // there is an error: Confirmation of your SignOut decision is required
    // with a message as "Are you sure you want to sign out?"
    public MicrosoftPage signOut() throws MalformedURLException {
        WebElement loggedInAccountElement = driver.findElement(By.xpath(BUTTON_SIGNED_IN_ACCOUNT_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(loggedInAccountElement))
                .click();
        WebElement signOutElement = driver.findElement(By.xpath(SIGN_OUT_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signOutElement))
                .click();
        // here it is required Accountant's "SignOut decision" Confirmation WebElement with answer options
        logger.info("You have just signed out");
        return new MicrosoftPage();
    }

    public SignUpNewAccountPage createNewAccountViaLink() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(CREATE_ONE_NEW_ACCOUNT_LINK_ELEMENT_XPATH))).click();
        logger.info("Now create account and sign up.");
        return new SignUpNewAccountPage();
    }

    public void openNewTabByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
        logger.info("New tab is opened");
    }

    public void handlePageDownByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).keyUp(Keys.CONTROL).build().perform();
    }

    public void handlePageUpByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).keyUp(Keys.CONTROL).build().perform();
    }


    public void handleWindowTabForwardByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
    }

    public void handleWindowTabBackByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.LEFT_SHIFT).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
    }
}