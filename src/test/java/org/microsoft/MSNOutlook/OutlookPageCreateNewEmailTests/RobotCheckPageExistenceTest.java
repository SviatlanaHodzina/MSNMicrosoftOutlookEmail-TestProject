package org.microsoft.MSNOutlook.tests.OutlookPageCreateNewEmailTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

//    Learn how to Disable Captcha in the Test Environment;
//    WebElement from the method getRobotCheckPageTitle() for Title text
//               for Puzzle Solution instruction on RobotCheckPage
//               with xpath ROBOT_CHECK_PAGE_TITLE_FOR_PUZZLE_ELEMENT_XPATH is hidden
public class RobotCheckPageExistenceTest extends RequiredConditions {
    @Test(description = "Verifies if there is a robot checking page.")
    @Parameters("browser")
    public void verifyIfThereIsARobotCheckingPage() throws MalformedURLException, InterruptedException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLivePage()
                .openPage()
                .createNewOutlookAccount()
                .signUp(account);
        new CreateAPasswordPage()
                .createAPassword(account).completeProfileNameForm(account);
        new ProfileBirthdayPage().completeProfileBirthdayForm(account);

//        String actualRobotCheckPageOuterFrameTitle = new RobotCheckPage().getRobotCheckPageOuterFrameTitle();
        String actualRobotCheckPageTitle = new RobotCheckPage().getRobotCheckPageTitle();
        String expectedRobotCheckPageTitle = resourceBundle.getString("Robot Check Page Message Title");
//        String expectedRobotCheckPageOuterFrameTitle = "Making sure you're human";
        String alertMessageIfTestFails = "There is unexpected message for checking if you are not robot.";
        assertEquals(alertMessageIfTestFails, expectedRobotCheckPageTitle,
                actualRobotCheckPageTitle);
    }
}