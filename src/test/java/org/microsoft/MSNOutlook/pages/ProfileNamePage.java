package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileNamePage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String PROFILE_NAME_PAGE_CONTENT_ELEMENT_XPATH = "//div[@id='pageContent']";
    private final String PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH = "//*[@id='ProfileAccrual']";
    private final String PROFILE_NAME_PAGE_MAIN_CONTENT_ELEMENT_XPATH = "//div[@id='maincontent']";
    private final String PROFILE_NAME_PAGE_TITLE_ELEMENT_XPATH = "//div[@id='iPageTitle']";
    private final String ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH = "//div[@id='PasswordDesc']";

    public ProfileNamePage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ProfileNamePage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PROFILE_NAME_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info(driver.findElement(By.xpath(PROFILE_NAME_PAGE_MAIN_CONTENT_ELEMENT_XPATH)).getAttribute("outerText"));
        return this;
    }

    public String getProfilePageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(PROFILE_NAME_PAGE_TITLE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public boolean profileNamePaneIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)).isDisplayed();
    }
}