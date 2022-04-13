package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.microsoft.MSNOutlook.tests.RequiredConditions.driver;

public class ProfileBirthdayPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH = "//*[@id='inner']";
    private final String PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH = "//*[@id='iPageTitle']";
    private final String PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH = "//*[@id='ProfileAccrual']";

    public ProfileBirthdayPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProfileBirthdayPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Complete Profile Birthday Form");
        return this;
    }

    public String getProfileBirthdayPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH )).getAttribute("textContent");
    }
}