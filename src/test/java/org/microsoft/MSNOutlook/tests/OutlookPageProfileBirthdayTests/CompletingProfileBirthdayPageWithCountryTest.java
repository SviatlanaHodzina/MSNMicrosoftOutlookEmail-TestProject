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
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;
// revised 15 September 2023
// there have been made changes on the origin site 14 September 2023
public class CompletingProfileBirthdayPageWithCountryTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with a Country/Region.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithCountry() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("CountriesAndRegions", Locale.US);

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        new CreateAPasswordPage()
                .createAPassword(account).completeProfileNameForm(account);
        String actualSelectedCountryValue = new ProfileBirthdayPage()
                .selectCountryOrRegion(account).getSelectedCountryOrRegion();
        String actualSelectedCountryName = resourceBundle.getString(actualSelectedCountryValue);

        String expectedSelectedCountryName = resourceBundle.getString("US");

        String alertMessageIfTestFails = "There is no such country. Select one from the drop down menu.";

        assertEquals(alertMessageIfTestFails, expectedSelectedCountryName, actualSelectedCountryName);
    }
}