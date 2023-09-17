package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class SignedInCurrentMSAccountPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    final String MICROSOFT_PAGE = "https://www.microsoft.com/en-us";

    static final String MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH = "//html/body[@class='page basicpage']";
    public static final String ACCOUNT_BUTTON_ELEMENT_XPATH = "//button[@id='mectrl_main_trigger']";
    private final String SIGN_OUT_BUTTON_ELEMENT_XPATH = "//a[@id='mectrl_body_signOut']";
    private final String ACCOUNT_PICTURE_PLACE_ELEMENT_XPATH = "//a[@id='mectrl_currentAccount_picture']";
    static final String ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH = "//div[@id='mectrl_main_body']";
    private final String SIGNED_IN_CURRENT_MS_ACCOUNT_FIRST_AND_LAST_NAME_PAGE_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_primary']";
    static final String SIGNED_IN_CURRENT_MS_ACCOUNT_NAME_PAGE_ELEMENT_XPATH = "//div[@id='mectrl_currentAccount_secondary']";
    private final String ACCOUNT_CURRENT_VIEW_LINK_ELEMENT_XPATH = "//a[@id='mectrl_viewAccount']";
    private final String ACCOUNT_LIST_ELEMENT_XPATH = "//ul[@class='mectrl_accountList']";

    public SignedInCurrentMSAccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public SignedInCurrentMSAccountPage openPage() {
        driver.navigate().to(MICROSOFT_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAIN_MICROSOFT_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Page " + MICROSOFT_PAGE + " is loaded");
        return this;
    }

    public String checkCurrentSignedInAccountName() throws InterruptedException {
        WebElement accountButtonElement = driver.findElement(By.xpath(ACCOUNT_BUTTON_ELEMENT_XPATH));
        accountButtonElement.click();
        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(SIGNED_IN_CURRENT_MS_ACCOUNT_NAME_PAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }
}