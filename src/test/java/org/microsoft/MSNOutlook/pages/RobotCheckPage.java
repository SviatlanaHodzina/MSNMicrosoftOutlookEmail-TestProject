package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.openqa.selenium.By.xpath;

// to be continue
public class RobotCheckPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String ROBOT_CHECK_PAGE_TITLE_ELEMENT_XPATH = "//*[@id='home_children_body']";
    private final String PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH = "//*[@id='iPageTitle']";

    public RobotCheckPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public RobotCheckPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_TITLE_ELEMENT_XPATH)));
        logger.info("Now we are going to check if you are not a robot");
        return this;
    }


    public RobotCheckPage goToOuterFrame() {
        int outerFrameSize = driver.findElements(By.tagName("IFRAME")).size();
        logger.info("Total number of outer iframes is: " + outerFrameSize);
        driver.switchTo().frame(0);
        logger.info("You are switched to the required outer iframe.");
        return this;
    }

    public RobotCheckPage goToInnerFrame() {
        goToOuterFrame();
        driver.switchTo().frame(0);
        logger.info("You are switched to the required inner iframe.");
        String title = driver.findElement(xpath(ROBOT_CHECK_PAGE_TITLE_ELEMENT_XPATH)).getAttribute("textContent");
        logger.info("Title of the page is: " + title);
        return this;
    }

    public RobotCheckPage goToIframe() {
        goToOuterFrame();
        int innerFrameSize = driver.findElements(By.tagName("IFRAME")).size();
        logger.info("Number of inner iframes inside the outer one: " + innerFrameSize);
        if (innerFrameSize > 0)
            goToInnerFrame();
        else
            logger.info("You are switched to the required frame.");
        return this;
    }

    public RobotCheckPage quitIframe() {
        driver.switchTo().defaultContent();
        return this;
    }

    public String getRobotCheckPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)));
        goToIframe();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_TITLE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(ROBOT_CHECK_PAGE_TITLE_ELEMENT_XPATH)).getAttribute("textContent").trim();
    }
}