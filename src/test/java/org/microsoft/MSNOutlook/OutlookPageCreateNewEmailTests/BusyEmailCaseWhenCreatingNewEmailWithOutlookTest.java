package org.microsoft.MSNOutlook.tests.OutlookPageCreateNewEmailTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertTrue;

public class BusyEmailCaseWhenCreatingNewEmailWithOutlookTest extends RequiredConditions {

    @Test(description = "Verifies that in the process of creating new email name " +
            "with domain @outlook the input email name is busy by someone else")
    @Parameters("browser")
    public void verifyThatTheInputNewEmailNameForCreationWithOutlookDomainIsBusy() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        boolean thisEmailAddressIsBusy = new SignUpNewAccountPage().thisEmailAddressIsBusy();

        String alertMessageIfTestFails = "This Email Name is available for creation";

        assertTrue(alertMessageIfTestFails, thisEmailAddressIsBusy);
    }
}