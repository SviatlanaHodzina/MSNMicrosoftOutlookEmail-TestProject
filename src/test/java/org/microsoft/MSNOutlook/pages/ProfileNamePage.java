package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileNamePage extends AbstractPage {

    Logger logger = LogManager.getRootLogger();

    private final String PROFILE_NAME_PAGE_CONTENT_ELEMENT_XPATH = "//div[@id='pageContent']";
    private final String PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH = "//*[@id='ProfileAccrual']";
    private final String PROFILE_NAME_PAGE_TITLE_ELEMENT_XPATH = "//div[@id='iPageTitle']";
    private final String ENTER_THE_PASSWORD_MESSAGE_ELEMENT_XPATH = "//div[@id='PasswordDesc']";
    private final String FIRST_NAME_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='FirstName']";
    private final String SURNAME_PLACEHOLDER_ELEMENT_XPATH = "//*[@id='LastName']";
    private final String NEXT_BUTTON_PROFILEPAGE_ELEMENT_XPATH = "//*[@id='iSignupAction']";
    private final String PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH = "//input[@id='PasswordInput']";

    public ProfileNamePage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ProfileNamePage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PROFILE_NAME_PAGE_CONTENT_ELEMENT_XPATH)));
        logger.info("Complete profile name Page with your First name and Surname");
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

    public ProfileNamePage enterYourFirstName(String firstName) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH)));
        WebElement firstNamePlaceholder = driver.findElement(By.xpath(FIRST_NAME_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(firstNamePlaceholder)).click();
        firstNamePlaceholder.sendKeys(firstName);
        logger.info("Entered First name is " + firstNamePlaceholder.getAttribute("value"));
        return this;
    }

    public ProfileNamePage enterYourSurname(String surname) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PASSWORD_PLACEHOLDER_MESSAGE_ELEMENT_XPATH)));
        WebElement surnamePlaceholder = driver.findElement(By.xpath(SURNAME_PLACEHOLDER_ELEMENT_XPATH));
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(surnamePlaceholder)).click();
        surnamePlaceholder.sendKeys(surname);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        logger.info("Entered Surname is " + surnamePlaceholder.getAttribute("value"));
        return this;
    }

    public ProfileBirthdayPage completeProfileNameForm(MSAccount msAccount) {
        enterYourFirstName(msAccount.getFirstName());
        enterYourSurname(msAccount.getSurname());
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(NEXT_BUTTON_PROFILEPAGE_ELEMENT_XPATH))).click();
        logger.info("Complete Profile Birthday Form");
        return new ProfileBirthdayPage();
    }
}
