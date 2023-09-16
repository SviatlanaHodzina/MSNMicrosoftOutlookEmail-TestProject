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
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithMonthOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Month of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithMonthOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Months", Locale.US);

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        new CreateAPasswordPage()
                .createAPassword(account).completeProfileNameForm(account);
        String actualBirthMonthValue = new ProfileBirthdayPage()
                .selectBirthMonth(account.getMonthOfBirth()).getSelectedBirthMonth();
        String actualMonthName = resourceBundle.getString(actualBirthMonthValue);
        String expectedBirthMonthName = resourceBundle.getString(account.getMonthOfBirth());

        String alertMessageIfTestFails = "Selected Birth Month name does not correspond with the expected one.";

        assertEquals(alertMessageIfTestFails, expectedBirthMonthName, actualMonthName);
    }

    public static class CompletingProfileNameFormTest extends RequiredConditions {
        @Test(description = "Verifies a possibility of completing your Profile Form with First name and Surname" +
                " for a new email account with domain @outlook.")
        @Parameters("browser")
        public void verifyThatOneCanCompleteProfileFormWithOutlookDomain() throws MalformedURLException {
            MSAccount account = AccountCompiler.withCredentialFromProperty();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

            new OutlookLivePage()
                    .openPage()
                    .signIn();
            new LogInOutlookLivePage().createNewAccountViaLink()
                    .signUp(account);

            String profileBirthdayFormTitle = new CreateAPasswordPage()
                    .createAPassword(account).completeProfileNameForm(account).getProfileBirthdayPageTitle();

            String expectedProfileNameFormTitle = resourceBundle.getString("'What is Your Birthday?' profile form title");

            String alertMessageIfTestFails = "You didn't complete the form with First name or Surname";

            assertEquals(alertMessageIfTestFails, expectedProfileNameFormTitle, profileBirthdayFormTitle);
        }
    }
}