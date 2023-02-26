package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.pages.ProfileBirthdayPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithDayOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Day of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithDayOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        new CreateAPasswordPage()
                .createAPassword(account).completeProfileNameForm(account);
        String actualBirthDay = new ProfileBirthdayPage()
                .selectBirthDay(account.getDayOfBirth()).getSelectedBirthDay();

        String expectedBirthDay = account.getDayOfBirth();

        String alertMessageIfTestFails = "Select another Day of Birth from the drop down menu.";

        assertEquals(alertMessageIfTestFails, expectedBirthDay, actualBirthDay);
    }
}