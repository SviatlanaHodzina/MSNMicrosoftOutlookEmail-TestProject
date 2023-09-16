package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.microsoft.MSNOutlook.pages.ProfileBirthdayPage.PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH;
import static org.openqa.selenium.By.xpath;

// to be continue
public class RobotCheckPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();
    public static final String ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH = "//div[@class='sc-99cwso-0 sc-11w6f91-0 fcBZbp eWRcSj home box screen']";
    public static final String ROBOT_CHECK_PAGE_TITLE_FOR_VISUAL_HIP_ELEMENT_XPATH = "//div[@class='text-title forVisualHip']";
    private final String PROFILE_BIRTHDATE_COUNTRY_PANEL_ELEMENT_XPATH = "//*[@id='BirthDateCountryAccrualInputPane']";
    private final String PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH = "//*[@id='iPageTitle']";
    private final String AUDIO_CHECK_ELEMENT_XPATH = "//button[@aria-label='Audio']";
    private final String ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH = "//p[@class='sc-1io4bok-0 KalLU sc-d5trka-0 text']";//element is hidden
    private final String BUTTON_NEXT_FOR_PUZZLE_SOLUTION_ELEMENT_XPATH = "//button[@class='sc-nkuzb1-0 sc-d5trka-0 eZxMRy button']";//element is hidden
    private final String OUTER_IFRAME_ELEMENT_XPATH = "//div[@id='hipEnforcementContainer']/iframe";
    private final String CSS_PATH_OF_OUTER_FRAME_ELEMENT_CSS_SELECTOR = "#enforcementFrame";
    //private String robotCheckContainerReference = "https://iframe.arkoselabs.com/B7D8911C-5CC8-A9A3-35B0-554ACEE604DA/index.html?mkt=en&data=1CyM49x2CQ%2Ff9gad.XOo6Mf0zqpPWpGKZmGY%2Fc0IxryhSl7c7BBthQHvjNfxlsaaqX07twi%2BDRu4cTNGJfCkPhPXhK7DhtOFGLeW3JPnSpyk0eu1e5m0Ic2j7z7QdLUIXoN0vbVXxtg5yhLtOpZ%2FAuqLgf03dcoFWiuwY9m34zG8%3D";
    //public static final String ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH = "//iframe[@src='robotCheckContainerReference']";// presents on 3 pages

    public RobotCheckPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public RobotCheckPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (xpath(PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Now we are going to check if you are not a robot");
        return this;
    }
// revise the code
    public String getRobotCheckPageOuterFrameTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (xpath(PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH)));
        String outerFrameTitle = ((JavascriptExecutor) driver)
                .executeScript("document.querySelector(arguments[0],':before').getPropertyValue('content');",
                        CSS_PATH_OF_OUTER_FRAME_ELEMENT_CSS_SELECTOR).toString();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                        (xpath(OUTER_IFRAME_ELEMENT_XPATH)));
        logger.info("The RobotCheckOPage's outer frame title is " + outerFrameTitle);
        return outerFrameTitle;
    }

    public RobotCheckPage goToOuterFrame() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (xpath(PROFILE_BIRTHDATE_COUNTRY_PANEL_ELEMENT_XPATH)));
        int outerFrameSize = driver.findElements(By.tagName("iframe")).size();
        logger.info("Total number of outer iframes is: " + outerFrameSize);
        if (outerFrameSize > 0)
        driver.switchTo().frame(0);
        logger.info("You are switched to the required outer iframe.");
        return this;
    }

    public RobotCheckPage goToInnerFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
        logger.info("You are switched to the required inner iframe.");
        return this;
    }

    public RobotCheckPage goToIframe() {
        goToOuterFrame();
        goToInnerFrame(0);
        int innerFrameSize = driver.findElements(By.tagName("iframe")).size();
        logger.info("Number of inner iframes inside the outer one: " + innerFrameSize);
        if (innerFrameSize > 0)
            goToInnerFrame(0);
        else
            logger.info("You are switched to the required frame.");
        return this;
    }

    public RobotCheckPage quitIframe() {
        driver.switchTo().defaultContent();
        return this;
    }

    public Integer getNumberOfFramesOnThePage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)));
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
        return numberOfFrames;
    }

//    WebElement for Title text for Puzzle Solution instruction on RobotCheckPage with
//    xpath ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH is hidden
    public String getRobotCheckPageTitle() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)));
        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH)));
        goToIframe();
        String robotCheckPageTitle = driver.findElement(By.xpath(ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH))
                .getAttribute("textContent").trim();
        quitIframe();
        return robotCheckPageTitle;
    }

//    WebElement for Next Button for Puzzle Solution on RobotCheckPage with
//    xpath ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH is hidden;
//    Find the method for making the element to be visible;
    public PuzzlePage nextButtonClickOnRobotCheckPage() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_TITLE_FOR_VISUAL_HIP_ELEMENT_XPATH)));
        goToIframe();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_NEXT_FOR_PUZZLE_SOLUTION_ELEMENT_XPATH)));
        quitIframe();
        return new PuzzlePage();
    }

    public AudioCheckPage audioButtonClickOnRobotCheckPage() throws MalformedURLException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_TITLE_FOR_VISUAL_HIP_ELEMENT_XPATH)));
        goToIframe();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(AUDIO_CHECK_ELEMENT_XPATH)));
        quitIframe();
        return new AudioCheckPage();
    }
}