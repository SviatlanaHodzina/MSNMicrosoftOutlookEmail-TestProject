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
import java.util.List;

import static java.time.Duration.ofSeconds;

public class SignedInAlternativeMSAccountPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String MICROSOFT_PAGE = "https://www.microsoft.com/en-us";

    static final String MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH = "//html/body[@class='page basicpage']";
    public static final String ALTERNATIVE_ACCOUNT_BUTTON_ELEMENT_XPATH = "//button[@id='O365_MainLink_Me']";
    public static final String NAVIGATING_HEADER_ALTERNATIVE_ACCOUNT_BUTTON_ELEMENT_XPATH = "//div[@id='O365_NavHeader']";
    private final String SIGN_OUT_BUTTON_ELEMENT_XPATH = "//a[@id='mectrl_body_signOut']";
    private final String ALTERNATIVE_ACCOUNT_PICTURE_PLACE_ELEMENT_XPATH = "//a[@id='mectrl_currentAccount_picture']";
    static final String ALTERNATIVE_ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH = "//div[@id='mectrl_main_body']";
    private final String SIGNED_IN_CURRENT_ALT_MS_ACCOUNT_FIRST_AND_LAST_NAME_PAGE_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_primary']";
    static final String SIGNED_IN_CURRENT_ALT_MS_ACCOUNT_NAME_PAGE_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_secondary']";
    private final String ACCOUNT_CURRENT_VIEW_LINK_ELEMENT_XPATH = "//a[@id='mectrl_viewAccount']";
    private final String REMEMBERED_ACCOUNTS_LIST_ELEMENT_XPATH = "//ul[@class='mectrl_accountList']/li//a";
    private final String INBOX_TABLE_MESSAGE_LISTBOX_ELEMENT_XPATH = "//div[ contains(@role,'complementary') and contains (@aria-label,'Message list')]";
    private final String CHECKBOX_ELEMENTS_XPATH = "//div[@role='checkbox']";
    private final String CHECKBOX_SELECT_ALL_MESSAGES_ELEMENT_XPATH = "//div[@title='Select all messages']";
    private final String CHECKBOX_PRESENTATION_IMAGE_ELEMENT_XPATH = "//div[@role='presentation']//img";
    private final String INBOX_MESSAGE_LISTBOX_ELEMENT_XPATH = "//div[@role='listbox']";
    private static final String INBOX_MESSAGE_OPTION_ELEMENT_XPATH = "//div[@role='option']";
    private final String INBOX_GROUP_MESSAGE_HEADER_ELEMENT_XPATH = INBOX_MESSAGE_LISTBOX_ELEMENT_XPATH + "//div[@role='heading']";
    private final String INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH = INBOX_MESSAGE_OPTION_ELEMENT_XPATH + CHECKBOX_ELEMENTS_XPATH;
    private final String SECURITY_CODE_IN_MESSAGE_RECEIVED_ELEMENT_XPATH = "//td[@id='x_i4']/span";
    private final String READING_PANE_CONTAINER_MESSAGE_RECEIVED_ELEMENT_XPATH = "//*[@id='ReadingPaneContainerId']";
    private final String READING_PANE_TITLE_MESSAGE_ELEMENT_XPATH = "//div[@class='full RCfNE allowTextSelection']";
    private final String READING_PANE_SENDER_MESSAGE_ELEMENT_XPATH = "//div[@class='AvaBt']";
    private final String READING_PANE_RECIPIENT_MESSAGE_ELEMENT_XPATH = "//ul[@class='Uy68G']";
    private final String READING_PANE_DATETIME_MESSAGE_ELEMENT_XPATH = "//div[@class='AL_OM l8Tnu I1wdR']";
    private final String READING_PANE_MESSAGE_CONTENT_ELEMENT_XPATH = "//div[@class='PlainText']";
    private final String READING_PANE_MESSAGE_CONTENT_PARTS_ELEMENT_XPATH = "//div[@class='PlainText']/br";//aria-hidden ="true" : make this attribute false and get its textContent
    private final String TITLE_SECURITY_CODE_MESSAGE_RECEIVED_ELEMENT_XPATH = READING_PANE_CONTAINER_MESSAGE_RECEIVED_ELEMENT_XPATH + "//div[@class='UXx3I']";
    private static final String SECURITY_CODE_RECEIVED_ELEMENT_XPATH = "//td[@id='x_i4']/span"; // this security code is for verifying alternative Email at the protection account step
    private static final String TOP_MENU_ELEMENT_XPATH = "//div[@role='menubar']";
    private static final String FOOTAGE_ELEMENT_XPATH = "//div[@class='th6py full']";
    private static final String REPLY_BUTTON_ON_TOP_ELEMENT_XPATH = TOP_MENU_ELEMENT_XPATH + "//button[@aria-label='Reply']";
    private static final String REPLY_ALL_BUTTON_ON_TOP_ELEMENT_XPATH = TOP_MENU_ELEMENT_XPATH + "//button[@aria-label='Reply all']";
    private static final String FORWARD_BUTTON_ON_TOP_ELEMENT_XPATH = TOP_MENU_ELEMENT_XPATH + "//button[@aria-label='Forward']";
    private static final String MORE_ACTION_MENU_BUTTON_ON_TOP_ELEMENT_XPATH = TOP_MENU_ELEMENT_XPATH + "//button[@id='read_ellipses_menu']";
    private static final String FORWARD_BUTTON_ON_FOOT_ELEMENT_XPATH = FOOTAGE_ELEMENT_XPATH + "//button[@aria-label='Forward']";
    private static final String REPLY_BUTTON_ON_FOOT_ELEMENT_XPATH = FOOTAGE_ELEMENT_XPATH + "//button[@aria-label='Reply']";

    @FindBy(how = How.XPATH, using = SECURITY_CODE_RECEIVED_ELEMENT_XPATH)
    private WebElement securityCodeReceivedElement;

    @FindBy(how = How.XPATH, using = INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH)
    private List<WebElement> inboxMessageOptionListElement;

    @FindBy(how = How.XPATH, using = READING_PANE_MESSAGE_CONTENT_PARTS_ELEMENT_XPATH)
    private List<WebElement> messageContentPartsListElement;

    public SignedInAlternativeMSAccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignedInAlternativeMSAccountPage openPage() {
        driver.navigate().to(MICROSOFT_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Page " + MICROSOFT_PAGE + " is loaded");
        return this;
    }

    public String checkCurrentSignedInAlternativeAccountName() {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ALTERNATIVE_ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALTERNATIVE_ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(SIGNED_IN_CURRENT_ALT_MS_ACCOUNT_NAME_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignedInAlternativeMSAccountPage selectInboxMSAccountSecurityCodeMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INBOX_MESSAGE_LISTBOX_ELEMENT_XPATH)));
        for (WebElement inboxMessageOption : inboxMessageOptionListElement) {
            if (inboxMessageOption.getAttribute("textContent").contains("security code")) {
                new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH))).click();
            }
        }
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(READING_PANE_CONTAINER_MESSAGE_RECEIVED_ELEMENT_XPATH)));
        logger.info("You've selected the message with security code received");
        return this;
    }

    //    this security code getting method is for verifying alternative Email at the protection account step
    public static String getSecurityCodeInInboxMessageReceived() {
        return driver.findElement(By.xpath(SECURITY_CODE_RECEIVED_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public SignedInAlternativeMSAccountPage selectTheRecentReceivedMessage() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(inboxMessageOptionListElement));
        for (WebElement inboxMessage : inboxMessageOptionListElement) {
            inboxMessageOptionListElement.get(0).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(READING_PANE_CONTAINER_MESSAGE_RECEIVED_ELEMENT_XPATH)));
        return new SignedInAlternativeMSAccountPage();
    }

    //    extract the message's datetime data.
    //    cast it to the datetime displayed in reading pane message.
    //    code the method for ensuring the equal datetimes on the side menu and in reading pane
    //    P.S. on side menu it lacks the date data
    public String getMessageContentOfTheRecentReceivedMessageOnSideMenu() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(inboxMessageOptionListElement));
        for (WebElement inboxMessage : inboxMessageOptionListElement) {
            inboxMessageOptionListElement.get(0).click();
        }
        return inboxMessageOptionListElement.get(0).getAttribute("ariaLabel");
    }

    public boolean theRecentReceivedMessageIsSelected() throws MalformedURLException {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.attributeToBe(By.xpath(INBOX_MESSAGE_OPTION_ELEMENT_XPATH), "ariaSelected", "true"));
    }

    public SignedInAlternativeMSAccountPage tickTheRecentReceivedMessage() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(inboxMessageOptionListElement));
        for (WebElement inboxMessage : inboxMessageOptionListElement) {
            inboxMessageOptionListElement.get(0).click();
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                            (By.xpath(INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH))).get(0).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.attributeToBe(By.xpath(INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH), "ariaChecked", "true"));
        return new SignedInAlternativeMSAccountPage();
    }

    public boolean theRecentReceivedMessageIsTicked() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.attributeToBe(By.xpath(INBOX_MESSAGE_OPTION_CHECKBOX_ELEMENT_XPATH), "ariaChecked", "true"));
    }

    //    this code getting method is for recovering main account's password.
    //    modify the method by extracting the code given in the message.
    public static String getTheCodeToRecoverPassword() {
        return driver.findElement(By.xpath(INBOX_MESSAGE_OPTION_ELEMENT_XPATH)).getAttribute("ariaLabel");
    }


    public void openNewTabViaSelenium() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    }

    public void turnToPreviousTabViaSelenium() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
        driver.switchTo().defaultContent();
    }

    public void openNewTabByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
        logger.info("New tab is opened");
    }

    public void openNewWindowByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("N").keyUp(Keys.CONTROL).build().perform();
        logger.info("New window is opened");
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

    //    doesn't work (?)
    public void handleWindowTurnBackByAction() {
        Actions act = new Actions(driver);
        act.keyDown(Keys.ALT).sendKeys(Keys.TAB).keyUp(Keys.ALT).build().perform();
    }

//    find out how to copy the element via mouse actions
//    public SignedInAlternativeMSAccountPage copySecurityCode(){
//        new Actions(driver)
//        .moveToElement(securityCodeReceivedElement)
//                .clickAndHold()
//                .keyDown(getSecurityCodeInInboxMessageReceived())
//                .release()
//                .build()
//                .perform();
//
//    }
}