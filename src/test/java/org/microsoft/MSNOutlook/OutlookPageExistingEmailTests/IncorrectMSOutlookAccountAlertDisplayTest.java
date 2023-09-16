package org.microsoft.MSNOutlook.tests.OutlookPageExistingEmailTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
// edit to Outlook
public class IncorrectMSOutlookAccountAlertDisplayTest extends RequiredConditions {
    @Test(description = "Verifies that incorrect input MS email account alert is displayed")
    @Parameters("browser")
    public static void verifyThatNotValidInputMSEmailAccountAlertIsDisplayed() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLivePage()
                .openPage()
                .signIn();
        boolean displayedErrorMessage =  new SignInOutlookPage()
                .signInWithIncorrectOutlookEmail(account)
                .alertMSOutlookAccountMessageIsDisplayed();
        String testFailedMessage = "Outlook account does exist, there is no error message here. Enter password";

        Assert.assertTrue(displayedErrorMessage, testFailedMessage);
    }
}