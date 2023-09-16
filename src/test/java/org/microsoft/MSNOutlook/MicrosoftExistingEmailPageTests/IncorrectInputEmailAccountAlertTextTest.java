package org.microsoft.MSNOutlook.tests.MicrosoftExistingEmailPageTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInMicrosoftPage;
import org.microsoft.MSNOutlook.pages.MicrosoftPage;
import org.microsoft.MSNOutlook.pages.SignInMicrosoftPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;
// Updated 15 September: actually this is a passed test-case, but failed due to the changed phrasing of alert message.   This fail should be reported to microsoft's developer.
//                      Alert message about incorrect input email name should contain exactly what's wrong input
//                      actual: Enter a valid email address, phone number, or Skype name, required mention that Email name is incorrect
// Updated 15 April: actually this is a passed test-case, but failed due to the changed phrasing of alert message.
public class IncorrectInputEmailAccountAlertTextTest extends RequiredConditions {

    @Test(description = "Verifies the alert text of incorrect input email account")
    @Parameters("browser")
    public static void verifyAlertTextOfNotValidInputEmailAccount() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithIncorrectEmail(account);
        String actualDisplayedAlertMessage =  new SignInMicrosoftPage().getAlertMSAccountAbsenceMessage();
        String expectedAlertMSAccountAbsenceMessage = resourceBundle.getString("'Microsoft account does not exist' alert");
        String testFailedMessage = "Microsoft account does exist, there is no error message here. Enter password";

        Assert.assertEquals(actualDisplayedAlertMessage, expectedAlertMSAccountAbsenceMessage, testFailedMessage);
    }
}