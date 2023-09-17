package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class PrivacyAndCookiesStatementPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String PRIVACY_AND_COOKIES_STATEMENT_PAGE_CONTENT_ELEMENT_XPATH = "/html/body";

    public PrivacyAndCookiesStatementPage () throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public PrivacyAndCookiesStatementPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(PRIVACY_AND_COOKIES_STATEMENT_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("You are navigated to 'PRIVACY AND COOKIES STATEMENT PAGE'");
        return this;
    }
    public String getPrivacyAndCookiesStatementPageContent() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(PRIVACY_AND_COOKIES_STATEMENT_PAGE_CONTENT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(PRIVACY_AND_COOKIES_STATEMENT_PAGE_CONTENT_ELEMENT_XPATH))
                .getAttribute("textContent");
    }
}