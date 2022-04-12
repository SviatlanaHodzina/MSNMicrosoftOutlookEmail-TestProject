package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MicrosoftServicesAgreementPage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String MICROSOFT_SERVICES_AGREEMENT_PAGE_BODY_ELEMENT_XPATH = "//body[@class='CSPvNext']";
    private final String MICROSOFT_SERVICES_AGREEMENT_PAGE_TITLE_ELEMENT_XPATH = "//div[@id='divJsEnabled']";

    public MicrosoftServicesAgreementPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MicrosoftServicesAgreementPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_BODY_ELEMENT_XPATH)));
        logger.info("You are navigated to the 'MICROSOFT SERVICES AGREEMENT PAGE'");
        return this;
    }

    public String getMicrosoftServicesAgreementPageTitle(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_TITLE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(MICROSOFT_SERVICES_AGREEMENT_PAGE_TITLE_ELEMENT_XPATH))
                .getAttribute("textContent");
    }
}