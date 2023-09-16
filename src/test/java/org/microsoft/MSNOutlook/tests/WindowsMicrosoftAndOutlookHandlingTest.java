package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WindowsMicrosoftAndOutlookHandlingTest extends RequiredConditions {

    @Test(description = "Verifies windows of Microsoft and Outlook pages handling is enable")
    @Parameters("browser")
    public void verifyThatWindowsMicrosoftAndOutlookHandlingIsEnable() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        String alternativeEmailName = "someone_for_testing";
        String alternativeEmailDomain = "@hotmail.com";
        String alternativeEmailAddress = alternativeEmailName.concat(alternativeEmailDomain);
        String passwordOfAltEmail = "efvbnjiUYTR";
        String incorrectPassword = "kem4vz2i65eau2mfias";

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountIncorrectPassword(account, incorrectPassword)
                .recoverAccountViaForgotPasswordLink();
//        new VerifyingIdentityPage().openNewWindowByAction();// skips opening new window and doesn't register this error

        new VerifyingIdentityPage().openNewTabViaSelenium();// doesn't open new tab, opens page in the same tab instead
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage()
                .signInYourOutlookAccount()
                .signInAlternativeOutlookEmail(alternativeEmailAddress)
                .enterMSAlternativeEmailPassword(passwordOfAltEmail);
//                .yesStaySignedInAlternative();// use this method after it stops offer you to break free from passwords
        String actualSignedInAccountName = new SignedInAlternativeMSAccountPage()
                .checkCurrentSignedInAlternativeAccountName();
//        new SignedInAlternativeMSAccountPage().handleWindowTurnBackByAction();// skips opening turning to another opened window and doesn't register this error

        new SignedInAlternativeMSAccountPage().turnToPreviousTabViaSelenium();// doesn't turn to previous tab and doesn't register
        String recoveryOptionEmailTextHint = new VerifyingIdentityPage()
                .getRecoveryOptionTextHintPage();
        boolean actualSignedInAccountNameStartsWithHintedLettersInRecoveryOptionEmailTextHint =
                actualSignedInAccountName.startsWith(recoveryOptionEmailTextHint);
        String incorrectAlternativeEmail = "The verification code is sent to incorrect email";
        Assert.assertTrue(actualSignedInAccountNameStartsWithHintedLettersInRecoveryOptionEmailTextHint, incorrectAlternativeEmail);
    }
}