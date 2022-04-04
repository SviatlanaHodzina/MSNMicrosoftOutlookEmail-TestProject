package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotValidInputEmailAccountAlertTextTest extends RequiredConditions {

    @Test(description = "Verifies the alert text of not valid input email account")
    public static void verifyAlertTextOfNotValidInputEmailAccount() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().signInWithExistingEmail(account);
        String actualDisplayedAlertMessage =  new LogInPage().getAlertMSAccountAbsenceMessage(account);
        String expectedAlertMSAccountAbsenceMessage = resourceBundle.getString("'Microsoft account does not exist alert'");
        String testFailedMessage = "Microsoft account does exist, there is no error message here. Enter password";

        Assert.assertEquals(actualDisplayedAlertMessage, expectedAlertMSAccountAbsenceMessage, testFailedMessage);
    }
}