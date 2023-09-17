package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.Proton;
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

import static org.openqa.selenium.By.xpath;

public class ProtonmailPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String PROTON_PAGE = "https://account.proton.me/login";
    public final String MAIN_PROTON_PAGE_CONTENT_ELEMENT_XPATH = "//body//div[@class='app-root']";
    public static final String PROTON_HEADER_LIST_ELEMENT_XPATH = "";
    public static final String PROTON_HEADER_LOGO_ITEM_IMAGE_ELEMENT_XPATH = "//img[@src='%s']";
    public static final String PROTON_HEADER_BUTTON_LANGUAGE_ITEM_ELEMENT_XPATH = "";
    public static final String PROTON_HEADER_BUTTON_LANGUAGE_IMAGE_ICON_ELEMENT_XPATH = "";
    public static final String PROTON_HEADER_LANGUAGE_DROPDOWN_DIALOG_ELEMENT_XPATH = "";
    public static final String PROTON_HEADER_LANGUAGE_DROPDOWN_CONTENT_LANGUAGE_LIST_ELEMENT_XPATH = "";

    public static final String SIGN_IN_PROTON_BOX_ELEMENT_XPATH = "";
    public static final String TITLE_SIGN_IN_PROTON_PAGE_ELEMENT_XPATH = "";
    public static final String TITLE_ENTER_PROTON_ACCOUNT_DETAILS_ELEMENT_XPATH = "";
    public static final String TITLE_ENTER_PROTON_EMAIL_OR_SURNAME_ELEMENT_XPATH = "//label[@for='username']";// incorrect attribute name 'for'
    public static final String TITLE_ENTER_PROTON_PASSWORD_ELEMENT_XPATH = "//label[@for='password']";

    private final String PLACEHOLDER_PROTON_ENTER_EMAIL_OR_SURNAME_ELEMENT_XPATH = "//input[@id='username']";
    private final String PLACEHOLDER_PROTON__PASSWORD_ELEMENT_XPATH = "//input[@id='password']";
    private final String BUTTON_PROTON_REVEAL_PASSWORD_ELEMENT_XPATH = "";

    private final String CHECKBOX_KEEP_ME_SIGNED_IN_ELEMENT_XPATH = "";
    private final String CHECKBOX_KEEP_ME_SIGNED_IN_TEXT_ELEMENT_XPATH = "";
    private final String CHECKBOX_KEEP_ME_SIGNED_IN_HINT_SIGN_ELEMENT_XPATH = "";
    private final String CHECKBOX_KEEP_ME_SIGNED_IN_DESCRIPTION_ELEMENT_XPATH = "";
    private final String CHECKBOX_KEEP_ME_SIGNED_IN_LEARN_MORE_LINK_ELEMENT_XPATH = "";

    private final String BUTTON_SIGN_IN_PROTON_ELEMENT_XPATH = "//button[@type='submit']";

    private final String CREATE_PROTON_ACCOUNT_NOTE_ELEMENT_XPATH = "";
    private final String CREATE_PROTON_ACCOUNT_LINK_ELEMENT_XPATH = "";
    private final String TROUBLE_SIGNING_IN_LINK_ELEMENT_XPATH = "";

    private final String PROTON_FEATURES_ICONS_CONSOLE_LIST_ELEMENT_XPATH = "";
    private final String PROTON_MAIL_ICON_ELEMENT_XPATH = "";
    private final String PROTON_CALENDAR_ICON_ELEMENT_XPATH = "";
    private final String PROTON_DRIVE_ICON_ELEMENT_XPATH = "";
    private final String PROTON_VPN_ICON_ELEMENT_XPATH = "";

    private final String PROTON_FOOTER_LIST_ELEMENT_XPATH = "";
    private final String PROTON_FOOTER_TERMS_LINK_ELEMENT_XPATH = "";
    private final String PROTON_FOOTER_PRIVACY_POLICY_LINK_ELEMENT_XPATH = "";
    private final String PROTON_FOOTER_VERSION_ELEMENT_XPATH = "";

    private final String BUTTON_NEED_HELP_ELEMENT_XPATH = "";
    private final String BUTTON_NEED_HELP_DROPDOWN_DIALOG_ELEMENT_XPATH = "";
    private final String BUTTON_NEED_HELP_DROPDOWN_CONTENT_LIST_ELEMENT_XPATH = "";
    private final String BUTTON_NEED_HELP_DROPDOWN_COMMON_SIGN_IN_ISSUES_ITEM_ELEMENT_XPATH = "";
    private final String BUTTON_NEED_HELP_DROPDOWN_REPORT_A_PROBLEM_ITEM_ELEMENT_XPATH = "";


    @FindBy(how = How.XPATH, using = BUTTON_SIGN_IN_PROTON_ELEMENT_XPATH)
    private WebElement signInButtonProtonElement;

    public ProtonmailPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ProtonmailPage openPage() {
        driver.navigate().to(PROTON_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_PROTON_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Proton Page is loaded");
        return this;
    }

    public String getTitleEnterProtonEmailOrSurnameMessage() {
        return driver.findElement(By.xpath(TITLE_ENTER_PROTON_EMAIL_OR_SURNAME_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String getTitleEnterProtonPasswordMessage() {
        return driver.findElement(By.xpath(TITLE_ENTER_PROTON_PASSWORD_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public ProtonmailPage enterCorrectProtonEmailOrSurname(String protonAccountEmail) throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(PLACEHOLDER_PROTON_ENTER_EMAIL_OR_SURNAME_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(PLACEHOLDER_PROTON_ENTER_EMAIL_OR_SURNAME_ELEMENT_XPATH))).sendKeys(protonAccountEmail);
        logger.info("Email or Surname is entered.");
        return new ProtonmailPage();
    }


    public ProtonmailPage enterProtonCorrectPassword(String protonAccountPassword) throws MalformedURLException {
        WebElement passwordPlaceholderProtonElement = driver.findElement(xpath(PLACEHOLDER_PROTON__PASSWORD_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(xpath(PLACEHOLDER_PROTON__PASSWORD_ELEMENT_XPATH))).click();
        passwordPlaceholderProtonElement.sendKeys(protonAccountPassword);
        logger.info("The password is entered.");
        return new ProtonmailPage();
    }

    public ProtonSignedInAccountPage signInProtonAccountButtonClick() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(signInButtonProtonElement)).click();
        return new ProtonSignedInAccountPage();
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