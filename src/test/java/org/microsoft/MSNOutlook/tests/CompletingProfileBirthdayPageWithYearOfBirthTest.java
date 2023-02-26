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

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithYearOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Year of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithYearOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        new CreateAPasswordPage()
                .createAPassword(account).completeProfileNameForm(account);
        String actualBirthYear = new ProfileBirthdayPage()
                .selectBirthYear(account).getSelectedBirthYear();
        String expectedBirthYear = account.getYearOfBirth();

        String alertMessageIfTestFails = "Year of Birth is not correct. Choose the year from the range.";

        assertEquals(alertMessageIfTestFails, expectedBirthYear, actualBirthYear);
    }
}