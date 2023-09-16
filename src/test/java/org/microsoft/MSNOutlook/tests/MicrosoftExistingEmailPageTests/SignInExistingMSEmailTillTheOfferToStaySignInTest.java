package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInMicrosoftPage;
import org.microsoft.MSNOutlook.pages.MicrosoftPage;
import org.microsoft.MSNOutlook.pages.StaySignedInOfferPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

// test-case passed 14 April 2023
public class SignInExistingMSEmailTillTheOfferToStaySignInTest extends RequiredConditions {

    @Test(description = "Verifies if you are asked to stay signed in after skipping Help Protection Page while sign in existing MS Email")
    @Parameters("browser")
    public void verifyYouAreAskedToStaySignedInAfterSkippingHelpProtectionStepWithSignInMSEmail() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account);
        String actualStaySignedInOfferPageTitle = new StaySignedInOfferPage().getTitleStaySignedInOfferPage();
        String expectedStaySignedInOfferPageTitle = resourceBundle.getString("Stay Signed In Offer Page Title");

        Assert.assertEquals(actualStaySignedInOfferPageTitle, expectedStaySignedInOfferPageTitle);
    }
}