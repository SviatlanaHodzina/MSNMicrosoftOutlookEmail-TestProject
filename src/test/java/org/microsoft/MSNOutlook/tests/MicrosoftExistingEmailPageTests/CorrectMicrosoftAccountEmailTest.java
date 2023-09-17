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

// Updated 14 April 2023: passed test-case
public class CorrectMicrosoftAccountEmailTest extends RequiredConditions {

    @Test(description = "Verifies if the input Microsoft Account Email is correct")
    @Parameters("browser")
    public void verifyThatTheInputMicrosoftAccountEmailIsCorrect() throws MalformedURLException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new MicrosoftPage()
                .openPage();
        String actualMessageForCorrectMicrosoftAccountEmail = new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .getTitleEnterPasswordMessage();
        String expectedMessageForCorrectMicrosoftAccountEmail = resourceBundle.getString("Enter password");

        Assert.assertEquals(actualMessageForCorrectMicrosoftAccountEmail, expectedMessageForCorrectMicrosoftAccountEmail);
    }
}