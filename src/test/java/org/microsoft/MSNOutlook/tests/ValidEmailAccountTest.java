package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidEmailAccountTest extends RequiredConditions {

// The test does work, tested on existed email: 29 March 2022
    @Test(description = "Verifies if the input existing email account is valid")
    public void verifyThatTheInputEmailAccountIsValid() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLiveComPage()
                .openPage()
                .signIn();
        String actualMessageForValidEmailAccount = new LogInPage().signInWithExistingEmail(account)
                .getEnterPasswordMessage();
        String expectedMessageForValidEmailAccount = "Enter password";// include it in lang locale resources bundle

        Assert.assertEquals(actualMessageForValidEmailAccount, expectedMessageForValidEmailAccount);
    }
}