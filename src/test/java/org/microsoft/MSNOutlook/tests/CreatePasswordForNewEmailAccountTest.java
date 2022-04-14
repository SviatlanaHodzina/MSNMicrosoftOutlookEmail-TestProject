package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.tests.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CreatePasswordForNewEmailAccountTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of a password creation for a new email account with domain @outlook. ")
    public void verifyThatOneCanInputPasswordForANewEmailNameCreatedWithOutlookDomain() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        boolean profileFormOfProfilePageIsDisplayed = new CreateAPasswordPage()
                .createAPassword(account).profileNamePaneIsDisplayed();
        String errorPasswordMessage = "Error password message is displayed";

        assertTrue(errorPasswordMessage,profileFormOfProfilePageIsDisplayed );
    }
}
