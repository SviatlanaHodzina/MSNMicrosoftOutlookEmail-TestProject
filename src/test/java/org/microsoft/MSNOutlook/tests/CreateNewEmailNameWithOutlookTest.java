package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CreateNewEmailNameWithOutlookTest extends RequiredConditions {

    @Test(description = "Verifies a possibility of a new email's name creation with domain @outlook.")
    public void verifyThatOneCanCreateANewEmailNameWithOutlookDomain() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        String actualAvailableEmailNameAccountOutlook = new CreateAPasswordPage().getAvailableEmailAccountDisplayed();

        String expectedAvailableEmailNameAccountOutlook = account.getEmailName().concat("@")
                .concat(account.getDomain());

        boolean availableEmailNameAccountOutlookIsDisplayed = actualAvailableEmailNameAccountOutlook
                .contains(expectedAvailableEmailNameAccountOutlook);

        String alertMessageIfTestFails = "WebDriver couldn't get the text attribute of the Element " +
                "or Created Email Name does not match the one displayed on the top of Password Page";

        assertTrue(alertMessageIfTestFails, availableEmailNameAccountOutlookIsDisplayed);
    }
}
