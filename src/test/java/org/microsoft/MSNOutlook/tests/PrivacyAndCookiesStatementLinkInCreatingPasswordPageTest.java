package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.tests.service.AccountCompiler;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertTrue;

public class PrivacyAndCookiesStatementLinkInCreatingPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if 'Privacy and Cookies statement' link in lightbox In CreateAPasswordPage is available")
    public void verifyThatPrivacyAndCookiesStatementLinkInCreatingPasswordPageIsAvailable() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        new CreateAPasswordPage().readPrivacyAndCookiesStatement();

        String actualPrivacyAndCookiesStatementPageContent = new PrivacyAndCookiesStatementPage()
                .getPrivacyAndCookiesStatementPageContent();
        String expectedPrivacyAndCookiesStatementPageTitle = resourceBundle
                .getString("'Privacy and cookies statement' page title");

        String alertMessageIfTestFails = "'Privacy and cookies statement.' link is not available or does not work";

        assertTrue(alertMessageIfTestFails,
                actualPrivacyAndCookiesStatementPageContent.contains(expectedPrivacyAndCookiesStatementPageTitle));
    }
}