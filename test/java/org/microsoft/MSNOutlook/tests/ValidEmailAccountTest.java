package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class ValidEmailAccountTest extends RequiredConditions {

    @Test(description = "Verifies if the input existing email account is valid")
    public void verifyThatTheInputEmailAccountIsValid() {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);
        new OutlookLiveComPage()
                .openPage()
                .signIn();
        String actualMessageForValidEmailAccount = new LogInPage().signInWithExistingEmail(account)
                .getTitleEnterPasswordMessage();
        String expectedMessageForValidEmailAccount = resourceBundle.getString("Enter password");

        Assert.assertEquals(actualMessageForValidEmailAccount, expectedMessageForValidEmailAccount);
    }
}