package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotValidMSAccountAlertDisplayTest extends RequiredConditions {

    @Test(description = "Verifies that not valid input MS email account alert is displayed")
    public static void verifyThatNotValidInputMSEmailAccountAlertIsDisplayed() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().signInWithExistingEmail(account);
        boolean displayedErrorMessage =  new LogInPage().alertMSAccountMessageIsDisplayed(account);
        String testFailedMessage = "Microsoft account does exist, there is no error message here. Enter password";

        Assert.assertTrue(displayedErrorMessage, testFailedMessage);
    }
}