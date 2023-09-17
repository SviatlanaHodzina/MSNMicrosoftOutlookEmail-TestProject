package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

// there should be other approaches for testing email service other than real registration
// and operate with its functions.

//CodeVerificationPage testing is unable due to the blocked code verification with too many codes sent
//reconstruct this test
public class SignInExistingMSEmailWithAlternativeEmailProtectionTest extends RequiredConditions {

    @Test(description = "Verifies if sign in existing MS Email with alternative email protection option is enable")
    @Parameters("browser")
    public void verifyThatSignInExistingMSEmailWithAlternativeEmailProtectionOptionIsEnable() throws MalformedURLException, InterruptedException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        String alternativeEmailAddress = "someone_for_testing@hotmail.com";
        String passwordOfAltEmail = "efvbnjiUYTR";

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account)
                .selectAlternativeEmailAddress()
                .inputAlternativeEmailAddress(alternativeEmailAddress);
        new MSAccountProtectionHelpPage()
                .clickNextButtonOnAccountProtectionPage();
        driver.getWindowHandle();

        new FirefoxDriver().switchTo().newWindow(WindowType.TAB);
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage()
                .signInYourOutlookAccount()
                .signInAlternativeOutlookEmail(alternativeEmailAddress)
                .enterMSAlternativeEmailPassword(passwordOfAltEmail);
        new SignInOutlookPage()
                .enterMSAlternativeEmailPassword(passwordOfAltEmail);
        new SignedInAlternativeMSAccountPage()
                .selectInboxMSAccountSecurityCodeMessage();
        SignedInAlternativeMSAccountPage
                .getSecurityCodeInInboxMessageReceived();
        new ChromeDriver().getWindowHandle();

        new ChromeDriver().switchTo().window(driver.getWindowHandle());
        new CodeVerificationPage()
                .enterTheReceivedSecurityCode();
        String actualCurrentSignedInAccount = new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();

        Assert.assertEquals(actualCurrentSignedInAccount, alternativeEmailAddress);
    }
}