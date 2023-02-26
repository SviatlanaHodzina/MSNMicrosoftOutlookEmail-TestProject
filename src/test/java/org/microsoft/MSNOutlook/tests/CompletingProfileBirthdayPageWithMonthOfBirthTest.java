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
import java.time.Month;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

public class CompletingProfileBirthdayPageWithMonthOfBirthTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with certain Month of Birth.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithMonthOfBirth() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Months", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
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
}