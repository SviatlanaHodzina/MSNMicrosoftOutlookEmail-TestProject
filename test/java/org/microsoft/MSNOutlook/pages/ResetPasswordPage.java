package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// to be continued
public class ResetPasswordPage extends AbstractPage{
    public final Logger logger = LogManager.getRootLogger();

    private final String CODE_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='']";

    public ResetPasswordPage (){
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ResetPasswordPage openPage(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CODE_PLACEHOLDER_ELEMENT_XPATH)));
        logger.info("Enter code and reset password");
        return this;
    }
}