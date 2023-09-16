package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

// it doesn't sign in alternative email in a new window as it is coded, but in the same one,
// that is opened for the received code input to sign in main account,
// However driver just opens blank new windows additionally
public class SignInExistingMSEmailWithIncorrectPasswordViaVerificationCodeInAlternativeEmailTest extends RequiredConditions {

    @Test(description = "Verifies if sign in existing MS Email with incorrect password is available via code verification in alternative email.")
    @Parameters("browser")
    public void verifyThatSignInExistingMSEmailWithIncorrectPasswordViaAlternativeEmailProtectionOptionIsEnable() throws MalformedURLException, InterruptedException {

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
        new VerifyingIdentityPage()
                .selectRecoveryOptionEmailToSendCode()// 09, 11 March. log4j didn't register 'not selecting the recovery option' and goes to the next step
                .enterRecoveryOptionEmail(alternativeEmailName, alternativeEmailDomain)
                .getCodeButtonClickToRecoverAccount();
       // String tabTestedEmail = driver.getWindowHandle(); // didn't work earlier, recheck 16 April

        //driver.switchTo().newWindow(WindowType.WINDOW); // didn't work earlier, recheck 16 April
        new MicrosoftPage().openNewTabByAction();
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage()
                .signInYourOutlookAccount()
                .signInAlternativeOutlookEmail(alternativeEmailAddress)
                .enterMSAlternativeEmailPassword(passwordOfAltEmail);
        new MSAccountProtectionHelpPage()
                .selectAlternativeEmailAddress()
                .inputAlternativeEmailAddress(account.getEmailName().concat("@").concat(account.getDomain()));
        new MSAccountProtectionHelpPage()
                .clickNextButtonOnAccountProtectionPage();
//        new MSAccountProtectionHelpPage()
//                .skipAccountProtectionStep();
//        new BreakPasswordsPage()
//                .skipBreakPasswordsOffer();// stopped offering to break free from passwords
//        new StaySignedInOfferPage()
//                .yesStaySignedIn();// use this method after it stops offer you to break free from passwords in case it doesn't offer use one of the account's protection method
        new OutlookLivePage().handleWindowTabBackByAction();
        new SignedInAlternativeMSAccountPage()
                .selectInboxMSAccountSecurityCodeMessage();//revise receiving the last inbox email with a security code
        SignedInAlternativeMSAccountPage
                .getSecurityCodeInInboxMessageReceived();// revise the receiving method code
//        String tabAlternativeEmail = driver.getWindowHandle(); // didn't work earlier, recheck 16 April
        new OutlookLivePage().handleWindowTabBackByAction();
//        new FirefoxDriver().switchTo().window(tabTestedEmail);// didn't work earlier, recheck 16 April
        new CodeEnterPage()
                .enterTheReceivedCode();
        String actualCurrentSignedInAccount = new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();

        Assert.assertEquals(actualCurrentSignedInAccount, alternativeEmailAddress);
    }
}