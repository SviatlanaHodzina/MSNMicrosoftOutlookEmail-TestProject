package org.microsoft.MSNOutlook.tests.OutlookPageExistingEmailTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.pages.SignInOutlookPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CorrectOutlookLiveAccountEmailTest extends RequiredConditions {

    @Test(description = "Verifies if the input account email is correct")
    @Parameters("browser")
    public void verifyThatTheInputEmailAccountIsCorrect() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLivePage()
                .openPage()
                .signIn();
        String actualMessageForCorrectEmailAccount = new SignInOutlookPage().signInWithCorrectOutlookEmail(account)
                .getTitleEnterPasswordMessage();
        String expectedMessageForCorrectEmailAccount = resourceBundle.getString("Enter password");

        Assert.assertEquals(actualMessageForCorrectEmailAccount, expectedMessageForCorrectEmailAccount);
    }
}