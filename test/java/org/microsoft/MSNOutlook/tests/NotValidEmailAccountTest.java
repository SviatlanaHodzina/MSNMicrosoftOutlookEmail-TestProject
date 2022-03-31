package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotValidEmailAccountTest extends RequiredConditions {

// The test does work, tested on existed email with wrong domain: 29 March 2022
    @Test (description = "Verifies that the input existing email account is not valid")
    public static void verifyThatTheInputEmailAccountIsNotValid(){
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLiveComPage()
                .openPage()
                .signIn();

        boolean displayedErrorMessage = new LogInPage().signInWithExistingEmail(account).alertMSAccountMessageIsDisplayed();
        String validMicrosoftAccountIfTestFails = "Microsoft account does exist, there is no error message here. Enter password";

        Assert.assertTrue(displayedErrorMessage, validMicrosoftAccountIfTestFails);
    }
}