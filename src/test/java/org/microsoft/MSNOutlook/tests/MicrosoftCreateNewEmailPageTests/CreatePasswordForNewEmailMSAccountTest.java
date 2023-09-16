package org.microsoft.MSNOutlook.tests.MicrosoftCreateNewEmailPageTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertTrue;
// edit code
public class CreatePasswordForNewEmailMSAccountTest extends RequiredConditions {
    @Test(description = "Verifies a possibility of a password creation for a new email account with domain @outlook. ")
    @Parameters("browser")
    public void verifyThatOneCanInputPasswordForANewEmailNameCreatedWithOutlookDomain() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage().createNewAccountViaLink()
                .signUp(account);
        boolean profileFormOfProfilePageIsDisplayed = new CreateAPasswordPage()
                .createAPassword(account).profileNamePaneIsDisplayed();
        String errorPasswordMessage = "Error password message is displayed";

        assertTrue(errorPasswordMessage,profileFormOfProfilePageIsDisplayed );
    }
}