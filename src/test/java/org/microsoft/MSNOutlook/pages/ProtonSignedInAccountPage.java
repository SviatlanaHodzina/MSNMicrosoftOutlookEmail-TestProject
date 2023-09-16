package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
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

import static org.microsoft.MSNOutlook.pages.LogInMicrosoftPage.LOGIN_MICROSOFT_ACCOUNT_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.MSAccountProtectionHelpPage.TITLE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.RecoveringMSAccountDialoguePage.TITLE_RECOVERING_MS_ACCOUNT_DIALOGUE_PAGE_ELEMENT_XPATH;
import static org.microsoft.MSNOutlook.pages.ResetPasswordPage.TITLE_RESET_PASSWORD_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

public class ProtonSignedInAccountPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    public static final String PROTON_SIGNED_IN_ACCOUNT_CONTENT_PAGE_ELEMENT_XPATH = "//body[@class='is-comfortable']";
    public static final String PROTON_ACCOUNT_BUTTON_ELEMENT_XPATH = "//li[@class='topnav-listItem relative']//button";
    public static final String PROTON_ACCOUNT_DROPDOWN_CONTENT_ELEMENT_XPATH = "//div[@class='dropdown-content']";
    public static final String PROTON_SIGNED_IN_CURRENT_ACCOUNT_NAME_ELEMENT_XPATH = "//div[@class='px1 py0-5']//div[2]/span";

    public ProtonSignedInAccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ProtonSignedInAccountPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROTON_SIGNED_IN_ACCOUNT_CONTENT_PAGE_ELEMENT_XPATH)));
        logger.info("Proton signed in current account Page is loaded");
        return this;
    }

    public String checkCurrentSignedInProtonAccountName() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PROTON_ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROTON_ACCOUNT_DROPDOWN_CONTENT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(PROTON_SIGNED_IN_CURRENT_ACCOUNT_NAME_ELEMENT_XPATH)).getAttribute("textContent");
    }
    public ProtonSignedInAccountPage hideProtonAccountDropdownContentBox() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PROTON_ACCOUNT_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS_EXTENDED))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROTON_ACCOUNT_DROPDOWN_CONTENT_ELEMENT_XPATH)));
        return this;
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