package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static org.microsoft.MSNOutlook.pages.RobotCheckPage.*;
import static org.openqa.selenium.By.xpath;

// to be continued
public class AudioCheckPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String AUDIO_CHECK_PAGE_CONTENT_ELEMENT_XPATH = "//form[@class='sc-99cwso-0 sc-11w6f91-0 iuZKCR eWRcSj audio-game box screen']";
    private final String AUDIO_CHECK_INSTRUCTION_TITLE_ELEMENT_XPATH = "//h2[@class='sc-1io4bok-0 Medxe heading text']";
    private final String AUDIO_CHECK_QUESTION_ELEMENT_XPATH = "//p[@class='sc-1io4bok-0 hGeSfN text']/p[1]";//edit the path taking into account text parts with attribute b
    private final String AUDIO_CHECK_INSTRUCTION_ELEMENT_XPATH = "//p[@class='sc-1io4bok-0 hGeSfN text']/p[2]";
//    private String audioContainerReference = "https://client-api.arkoselabs.com/v2/1.5.5/enforcement.fbfc14b0d793c6ef8359e0e4b4a91f67.html#B7D8911C-5CC8-A9A3-35B0-554ACEE604DA";
//    private final String AUDIO_CHECK_PAGE_CONTENT_ELEMENT_XPATH = "//iframe[@src='audioContainerReference']";//
//   add elements' xpath


    public AudioCheckPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public AudioCheckPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(AUDIO_CHECK_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Puzzle Page");
        return this;
    }
}