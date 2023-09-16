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

// passed test-case: update 14 April 2023
// false 'test-fail' randomly, as it doesn't register the WebElement found with xpath ACCOUNT_POPPED_UP_BODY_ELEMENT_XPATH
// add providing additional protection option on the Protection Help Page
public class StayingSignedInOnYesAnswerToOfferToStaySignInTest extends RequiredConditions {

    @Test(description = "Verifies if a user is stayed signed in with the answer yes to the offer to stay signed in.")
    @Parameters("browser")
    public void verifyUserIsStayedSignedInOnYesAnswerToOfferToStaySignIn() throws MalformedURLException, InterruptedException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account);
        new StaySignedInOfferPage()
                .yesStaySignedIn();

        String actualCurrentSignedInAccount = new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();
        String expectedCurrentSignedInAccount = account.getEmailName().concat("@").concat(account.getDomain());

        Assert.assertEquals(actualCurrentSignedInAccount, expectedCurrentSignedInAccount);
    }
}