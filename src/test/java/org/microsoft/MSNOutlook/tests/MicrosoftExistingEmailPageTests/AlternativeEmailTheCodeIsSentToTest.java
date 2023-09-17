package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

// Update 14 April 2023: This test-case is not applicable with the current test-data email, as it skips 'Help Protection Page'
// Update 27 February 2023:  Warning! Serious bug: it is allowed to send a confirmation code to a non-existing email: is to be registered on Atlassian in the Microsoft project
public class AlternativeEmailTheCodeIsSentToTest extends RequiredConditions {

    @Test(description = "Verifies that an alternative email where the code is sent to is the one a user selected as an account protection.")
    @Parameters("browser")
    public void verifyAlternativeEmailTheCodeIsSentToIsTheSelectedOne() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();

        String alternativeEmailAddress = "someone_for_testing@hotmail.com";
        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account)
                .selectAlternativeEmailAddress() // this method is skipped due to skipped Help Protection Page
                .inputAlternativeEmailAddress(alternativeEmailAddress);// this method is skipped due to skipped Help Protection Page
        new MSAccountProtectionHelpPage()
                .clickNextButtonOnAccountProtectionPage();

        String actualEmailAddressTheCodeWasSent = new CodeVerificationPage().getEmailAddressTheCodeWasSent();

        Assert.assertEquals(actualEmailAddressTheCodeWasSent, alternativeEmailAddress);
    }
}