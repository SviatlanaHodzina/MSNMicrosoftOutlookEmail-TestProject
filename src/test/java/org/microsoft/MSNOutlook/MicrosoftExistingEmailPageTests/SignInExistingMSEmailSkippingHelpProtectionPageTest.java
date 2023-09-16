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
// Thus, the method skipAccountProtectionStep() is unavailable and not applicable for this test case.
// For testing this step use another testdata.
public class SignInExistingMSEmailSkippingHelpProtectionPageTest extends RequiredConditions {

    @Test(description = "Verifies if you are asked to stay signed in after skipping Help Protection Page while sign in existing MS Email")
    @Parameters("browser")
    public void verifyYouAreAskedToStaySignedInAfterSkippingHelpProtectionStepWithSignInMSEmail() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new MicrosoftPage()
                .openPage();
        String actualStaySignedInOfferPageTitle = new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account)
                // the method skipAccountProtectionStep() is enabled from the first sign in the account
                .skipAccountProtectionStep()//It doesn't appear after several times defined by the developer and after setting any of the protection options.
                .getTitleStaySignedInOfferPage();
        String expectedStaySignedInOfferPageTitle = resourceBundle.getString("Stay Signed In Offer Page Title");

        Assert.assertEquals(actualStaySignedInOfferPageTitle, expectedStaySignedInOfferPageTitle);
    }
}