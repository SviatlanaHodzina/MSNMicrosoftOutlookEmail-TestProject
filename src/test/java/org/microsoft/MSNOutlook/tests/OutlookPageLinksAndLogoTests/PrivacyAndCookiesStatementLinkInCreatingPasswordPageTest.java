package org.microsoft.MSNOutlook.tests.OutlookPageLinksAndLogoTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertTrue;

public class PrivacyAndCookiesStatementLinkInCreatingPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if 'Privacy and Cookies statement' link in lightbox In CreateAPasswordPage is available")
    @Parameters("browser")
    public void verifyThatPrivacyAndCookiesStatementLinkInCreatingPasswordPageIsAvailable() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLivePage()
                .openPage()
                .signIn();
        new LogInOutlookLivePage().createNewAccountViaLink()
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