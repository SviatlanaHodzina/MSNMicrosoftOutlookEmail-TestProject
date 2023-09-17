package org.microsoft.MSNOutlook.tests.MicrosoftCreateNewEmailPageTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInMicrosoftPage;
import org.microsoft.MSNOutlook.pages.MicrosoftPage;
import org.microsoft.MSNOutlook.pages.SignUpNewAccountPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertTrue;

// edit code
public class BusyEmailCaseWhenCreatingNewMSEmailTest extends RequiredConditions {

    @Test(description = "Verifies that in the process of creating new email name " +
            "with domain @outlook the input email name is busy by someone else")
    @Parameters("browser")
    public void verifyThatTheInputNewEmailNameForCreationWithOutlookDomainIsBusy() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new MicrosoftPage()
                .openPage()
                .signInMicrosoftAccount();
        new LogInMicrosoftPage().createNewAccountViaLink()
                .signUp(account);
        boolean thisEmailAddressIsBusy = new SignUpNewAccountPage().thisEmailAddressIsBusy();

        String alertMessageIfTestFails = "This Email Name is available for creation";

        assertTrue(alertMessageIfTestFails, thisEmailAddressIsBusy);
    }
}