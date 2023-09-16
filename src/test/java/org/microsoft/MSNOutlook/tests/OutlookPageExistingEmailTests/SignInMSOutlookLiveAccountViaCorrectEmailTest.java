package org.microsoft.MSNOutlook.tests.OutlookPageExistingEmailTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

//    method signInWithCorrectOutlookEmail(account) doesn't work, because of
//    failed:waiting for visibility of element located by By.xpath:SIGN_IN_PLACEHOLDER_ELEMENT_XPATH
public class SignInMSOutlookLiveAccountViaCorrectEmailTest extends RequiredConditions {

    @Test(description = "Verifies if one can sign in Microsoft OutlookLive account via existing email")
    @Parameters("browser")
    public void verifyThatOneCanSignInMSOutlookLiveAccountViaExistingEmail() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLivePage()
                .openPage();
        String actualMessageForCorrectMSOutlookLiveAccountEmail = new LogInOutlookLivePage()
                .signInYourOutlookAccount()
                .signInWithCorrectOutlookEmail(account)
                .getTitleEnterPasswordMessage();
        String expectedMessageForCorrectMSOutlookLiveAccountEmail = resourceBundle.getString("Enter password");

        Assert.assertEquals(actualMessageForCorrectMSOutlookLiveAccountEmail, expectedMessageForCorrectMSOutlookLiveAccountEmail);
    }
}