package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CreatePasswordForNewEmailAccountTest extends RequiredConditions {

//    The trst does not work: unable to locate element of password's placeholder // to be solved
    @Test(description = "Verifies a possibility of a password creation for a new email account with domain @outlook. ")
    public void verifyThatOneCanInputPasswordForANewEmailNameCreatedWithOutlookDomain() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        String actualNextProfilePageTitleWithCreatedPassword = new CreateAPasswordPage().createAPassword(account).getProfilePageTitle();
        String expectedNextProfilePageTitleWithCreatedPassword = "What's your name?";

        assertEquals(actualNextProfilePageTitleWithCreatedPassword, expectedNextProfilePageTitleWithCreatedPassword);
    }
}
