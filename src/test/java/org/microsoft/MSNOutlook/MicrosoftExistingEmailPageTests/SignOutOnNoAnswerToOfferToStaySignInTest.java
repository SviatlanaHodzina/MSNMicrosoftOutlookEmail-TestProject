package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInMicrosoftPage;
import org.microsoft.MSNOutlook.pages.MicrosoftPage;
import org.microsoft.MSNOutlook.pages.SignedInCurrentMSAccountPage;
import org.microsoft.MSNOutlook.pages.StaySignedInOfferPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
// Test-case failed: 14 April 2023 update, 31 August 2023 repeated
// This function doesn't work. Answer "No" on Stay signed in offer results in staying signed in.
// There was no response on my microsoft report of this function fail.
// TODO: register testcases on github AND Send an email to Microsoft for reporting the function fail.
public class SignOutOnNoAnswerToOfferToStaySignInTest extends RequiredConditions {

    @Test(description = "Verifies if a user is signed out with the answer No to the offer to stay signed in.")
    @Parameters("browser")
    public void verifyUserIsSignedOutOnNoAnswerToOfferToStaySignIn() throws MalformedURLException, InterruptedException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account);
        new StaySignedInOfferPage()
                .noStaySignedIn();
        String actualCurrentSignedInAccount = new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();
        String expectedCurrentSignedInAccount = account.getEmailName().concat("@").concat(account.getDomain());
        String errorMessage = "The user couldn't manage to sign out. Button 'No' doesn't sign out the account.";
        Assert.assertNotEquals(actualCurrentSignedInAccount, expectedCurrentSignedInAccount, errorMessage);
    }
}