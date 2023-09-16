package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class MicrosoftServicesAgreementPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String MICROSOFT_SERVICES_AGREEMENT_PAGE_CONTENT_ELEMENT_XPATH = "/html/body";

    public MicrosoftServicesAgreementPage() throws MalformedURLException {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MicrosoftServicesAgreementPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("You are navigated to the 'MICROSOFT SERVICES AGREEMENT PAGE'");
        return this;
    }

    public String getMicrosoftServicesAgreementPageContent(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_CONTENT_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_CONTENT_ELEMENT_XPATH))
                .getAttribute("textContent");
    }
}