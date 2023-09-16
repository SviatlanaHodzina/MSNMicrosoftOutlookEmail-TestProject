package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInMicrosoftPage;
import org.microsoft.MSNOutlook.pages.MicrosoftPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;
// Used email testdata passes the Help Protection Page to the account page with a prior 'Stay in?' question
// with error of ignoring 'no' answer leading to be stayed signed in.
// For testing this step use another testdata
public class SignInExistingMSEmailLeadingToHelpProtectionPageTest extends RequiredConditions {

    @Test(description = "Verifies if sign in existing MS Email leads to Help Protection Page")
    @Parameters("browser")
    public void verifyThatSignInExistingMSEmailLeadsToHelpProtectionPage() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new MicrosoftPage()
                .openPage();
        String actualHelpProtectionAccountPageTitle = new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account)
                .getTitleOnProtectionHelpPage();
        String expectedHelpProtectionAccountPageTitle = resourceBundle.getString("Protection Help Page Title");

        Assert.assertEquals(actualHelpProtectionAccountPageTitle, expectedHelpProtectionAccountPageTitle);
    }
}