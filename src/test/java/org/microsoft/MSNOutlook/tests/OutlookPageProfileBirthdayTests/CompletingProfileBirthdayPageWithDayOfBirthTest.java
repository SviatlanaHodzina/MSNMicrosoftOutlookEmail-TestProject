package org.microsoft.MSNOutlook.tests.OutlookPageProfileBirthdayTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.pages.ProfileBirthdayPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithDayOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Day of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithDayOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
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