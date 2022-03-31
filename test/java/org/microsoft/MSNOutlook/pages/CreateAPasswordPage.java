package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CreateAPasswordPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String CREATE_PASSWORD_PAGE_CONTENT_ELEMENT_XPATH = "//div[@id='pageContent']";
    private final String ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH = "//div[@id='PasswordDesc']";
    private final String PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH = "//input[@id='PasswordInput']";
    private final String AVAILABLE_EMAIL_ACCOUNT_ELEMENT_XPATH = "//div[@id='maincontent']";
    private final String NEXT_BUTTON_ELEMENT_XPATH = "//input[@id='iSignupAction']";

    public CreateAPasswordPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CreateAPasswordPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(CREATE_PASSWORD_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("'Create password' Page");
        return this;
    }

    public boolean passwordPageContentIsDisplayed() {
        return driver.findElement(By.xpath(CREATE_PASSWORD_PAGE_CONTENT_ELEMENT_XPATH)).isDisplayed();
    }

    public ProfileNamePage createAPassword(MSAccount msAccount) {
        WebElement passwordPlaceholder = driver.findElement(By.xpath(PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(passwordPlaceholder)).click();
        passwordPlaceholder.sendKeys(msAccount.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(NEXT_BUTTON_ELEMENT_XPATH))).click();
        logger.info("We need just a little more info to set up your account. Enter Your First name and Surname");
        return new ProfileNamePage();
    }

    public String getAvailableEmailAccountDisplayed() {
        return driver.findElement(By.xpath(AVAILABLE_EMAIL_ACCOUNT_ELEMENT_XPATH)).getAttribute("outerText");
    }

    //    This element for the method below is unable to locate
    public boolean enterThePasswordMessageIsDisplayed() {
        return driver.findElement(By.xpath(ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH)).isDisplayed();
    }

    //    This element for the method below is unable to locate
    public String enterThePasswordMessage() {
        return driver.findElement(By.xpath(ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public String createPasswordMessageInPlaceholder() {
        return driver.findElement(By.xpath(PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH)).getAttribute("Placeholder");
    }

    public boolean availableEmailAccountIsDisplayed() {
        return driver.findElement(By.xpath(AVAILABLE_EMAIL_ACCOUNT_ELEMENT_XPATH)).isDisplayed();
    }
}