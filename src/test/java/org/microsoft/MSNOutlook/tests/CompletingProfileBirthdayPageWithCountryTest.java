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

public class CompletingProfileBirthdayPageWithCountryTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of completing your Profile form with a Country/Region.")
    @Parameters("browser")
    public void verifyThatOneCanCompleteProfileFormWithCountry() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("CountriesAndRegions", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
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