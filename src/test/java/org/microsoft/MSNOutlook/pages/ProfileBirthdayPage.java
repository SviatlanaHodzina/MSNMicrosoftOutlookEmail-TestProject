package org.microsoft.MSNOutlook.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.microsoft.MSNOutlook.model.MSAccount;
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

import static java.time.Duration.ofSeconds;
import static org.microsoft.MSNOutlook.tests.RequiredConditions.driver;
import static org.openqa.selenium.By.xpath;

public class ProfileBirthdayPage extends AbstractPage {
    Logger logger = LogManager.getRootLogger();

    private final String PROFILE_BIRTHDAY_PAGE_CONTENT_ELEMENT_XPATH = "//*[@id='inner']";
    private final String PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH = "//*[@id='iPageTitle']";
    private final String PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH = "//*[@id='ProfileAccrual']";
    private final String MENU_BUTTON_COUNTRY_OR_REGION_ELEMENT_XPATH = "//*[@name='Country']";
    private final String BIRTH_DAY_MENU_BUTTON_ELEMENT_XPATH = "//*[@name='BirthDay']";
    private final String BIRTH_MONTH_MENU_BUTTON_ELEMENT_XPATH = "//*[@name='BirthMonth']";
    private final String BIRTH_YEAR_MENU_BUTTON_ELEMENT_XPATH = "//*[@name='BirthYear']";
    private final String NEXT_BUTTON_PROFILE_BIRTHDAY_PAGE_ELEMENT_XPATH = "//*[@id='iSignupAction']";

    @FindBy(how = How.XPATH, using = "//*[@name='Country']//option")
    private List<WebElement> countryOrRegionOptionListElement;

    @FindBy(how = How.XPATH, using = "//select[@name='BirthDay']//option")
    private List<WebElement> birthDayOptionListElement;

    @FindBy(how = How.XPATH, using = "//select[@name='BirthMonth']//option")
    private List<WebElement> birthMonthOptionListElement;

    @FindBy(how = How.XPATH, using = BIRTH_YEAR_MENU_BUTTON_ELEMENT_XPATH)
    private WebElement birthYearPlaceholderElement;

    public ProfileBirthdayPage() throws MalformedURLException {
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
        return driver.findElement(By.xpath(PROFILE_BIRTHDAY_PAGE_TITLE_ELEMENT_XPATH)).getAttribute("textContent");
    }

    public ProfileBirthdayPage selectCountryOrRegion(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(MENU_BUTTON_COUNTRY_OR_REGION_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(countryOrRegionOptionListElement));
        for (WebElement countryOrRegionOption : countryOrRegionOptionListElement) {
            if (countryOrRegionOption.getAttribute("value").matches(msAccount.getCountry())) {
                countryOrRegionOptionListElement.get(Integer.parseInt(countryOrRegionOption.getAttribute("index"))).click();
            }
        }
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_BUTTON_COUNTRY_OR_REGION_ELEMENT_XPATH))).click();
        logger.info("Country is " + driver.findElement(By.xpath(MENU_BUTTON_COUNTRY_OR_REGION_ELEMENT_XPATH)).getAttribute("value"));
        return this;
    }

    public String getSelectedCountryOrRegion() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(MENU_BUTTON_COUNTRY_OR_REGION_ELEMENT_XPATH)).getAttribute("value");
    }

    public ProfileBirthdayPage selectBirthDay(String dayOfBirth) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BIRTH_DAY_MENU_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(birthDayOptionListElement));
        for (WebElement day : birthDayOptionListElement) {
            birthDayOptionListElement.get(Integer.parseInt(dayOfBirth)).click();
        }
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BIRTH_DAY_MENU_BUTTON_ELEMENT_XPATH))).click();
        logger.info("Day of Birth is " +
                driver.findElement(By.xpath(BIRTH_DAY_MENU_BUTTON_ELEMENT_XPATH)).getAttribute("value"));
        return this;
    }

    public String getSelectedBirthDay() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(BIRTH_DAY_MENU_BUTTON_ELEMENT_XPATH)).getAttribute("value");
    }

    public ProfileBirthdayPage selectBirthMonth(String monthOfBirth) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BIRTH_MONTH_MENU_BUTTON_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(birthMonthOptionListElement));
        for (WebElement month : birthMonthOptionListElement) {
            birthMonthOptionListElement.get(Integer.parseInt(monthOfBirth)).click();
        }
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BIRTH_MONTH_MENU_BUTTON_ELEMENT_XPATH))).click();
        logger.info("Month of Birth is " +
                driver.findElement(By.xpath(BIRTH_MONTH_MENU_BUTTON_ELEMENT_XPATH)).getAttribute("value"));
        return this;
    }

    public String getSelectedBirthMonth() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(BIRTH_MONTH_MENU_BUTTON_ELEMENT_XPATH)).getAttribute("value");
    }

    public ProfileBirthdayPage selectBirthYear(MSAccount msAccount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (birthYearPlaceholderElement)).click();
        birthYearPlaceholderElement.sendKeys(msAccount.getYearOfBirth());
        logger.info("Year of Birth is ");
        return this;
    }
    public String getSelectedBirthYear() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        return driver.findElement(By.xpath(BIRTH_YEAR_MENU_BUTTON_ELEMENT_XPATH)).getAttribute("value");
    }

    public RobotCheckPage completeProfileBirthdayForm(MSAccount msAccount) throws MalformedURLException {
        selectCountryOrRegion(msAccount);
        selectBirthDay(msAccount.getDayOfBirth());
        selectBirthMonth(msAccount.getMonthOfBirth());
        selectBirthYear(msAccount);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(NEXT_BUTTON_PROFILE_BIRTHDAY_PAGE_ELEMENT_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PROFILE_NAME_PANE_PAGE_ELEMENT_XPATH)));
        logger.info("Profile Birthday & Country form is completed.");
        return new RobotCheckPage();
    }
}
