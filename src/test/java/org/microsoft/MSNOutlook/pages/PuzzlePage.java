package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class PuzzlePage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String PUZZLE_PAGE_CONTENT_ELEMENT_XPATH = "//div[@class='sc-99cwso-0 sc-11w6f91-0 gMEQEa eWRcSj match-game box screen']";
    private final String PUZZLE_PAGE_INSTRUCTION_ELEMENTS_LIST_XPATH = "//h2[@class='sc-1io4bok-0 juZVah text']//strong";
    private final String BOX_CHALLENGE_CONTAINER_PUZZLE_ELEMENT_XPATH = "//div[@class='sc-99cwso-0 sc-1t9on73-0 gMEQEa cOLSza box challenge-container']";
    private final String KEY_FRAME_PUZZLE_ELEMENT_XPATH = "//div[@class='']";
    private final String ANSWER_FRAME_PUZZLE_ELEMENT_XPATH = "//div[@class='']";
    private final String BUTTON_SUBMIT_ON_PUZZLE_PAGE_ELEMENT_XPATH = "//button[@class='']";
//    private final String PUZZLE_PAGE_CONTENT_ELEMENT_XPATH = "//form[@id='HipEnforcementForm']//div[@id='hipEnforcementContainer'][1]";// the same page content locator for 3 pages

    @FindBy(how = How.XPATH, using = PUZZLE_PAGE_INSTRUCTION_ELEMENTS_LIST_XPATH)
    private List<WebElement> puzzleSolutionInstructionTextListElement;

    public PuzzlePage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public PuzzlePage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.
                        invisibilityOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH)));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PUZZLE_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Puzzle Page");
        return this;
    }

    //    Because of  WebElement Button 'Next' on RobotCheckPage - to pass to this PuzzlePage -is hidden and unavailable,
    //    this method is for the case when this PuzzlePage is available for passing to.
    public String getPuzzleSolutionInstructionText() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.
                        invisibilityOfElementLocated(By.xpath(ROBOT_CHECK_PAGE_CONTENT_ELEMENT_XPATH)));
        goToIframe();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(puzzleSolutionInstructionTextListElement));
        String puzzleSolutionInstructionText = "";
        for (WebElement instructionTextPart : puzzleSolutionInstructionTextListElement) {
            String partText = instructionTextPart.getAttribute("textContent");
            puzzleSolutionInstructionText = puzzleSolutionInstructionText.concat(partText.concat(partText));
            quitIframe();
        }
        return puzzleSolutionInstructionText;
    }


    public PuzzlePage goToOuterFrame() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (xpath(ROBOT_CHECK_PAGE_TITLE_FOR_VISUAL_HIP_ELEMENT_XPATH)));
        int outerFrameSize = driver.findElements(By.tagName("iframe")).size();
        logger.info("Total number of outer iframes is: " + outerFrameSize);
        if (outerFrameSize > 0)
            driver.switchTo().frame(0);
        logger.info("You are switched to the required outer iframe.");
        return this;
    }

    public PuzzlePage goToInnerFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
        logger.info("You are switched to the required inner iframe.");
        return this;
    }

    public PuzzlePage goToIframe() {
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

    public PuzzlePage quitIframe() {
        driver.switchTo().defaultContent();
        return this;
    }
}