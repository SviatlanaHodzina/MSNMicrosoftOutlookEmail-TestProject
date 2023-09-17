package org.microsoft.MSNOutlook.tests.OutlookPageProfileBirthdayTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.pages.ProfileBirthdayPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithYearOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Year of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithYearOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
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