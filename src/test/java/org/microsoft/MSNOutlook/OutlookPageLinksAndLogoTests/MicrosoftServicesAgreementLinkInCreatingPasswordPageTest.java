package org.microsoft.MSNOutlook.tests.OutlookPageLinksAndLogoTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.MicrosoftServicesAgreementPage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.hamcrest.MatcherAssert.assertThat;

public class MicrosoftServicesAgreementLinkInCreatingPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if 'Microsoft Services Agreement' link in lightbox In CreateAPasswordPage is available")
    @Parameters("browser")
    public void verifyThatMicrosoftServicesAgreementLinkInCreatingPasswordPageIsAvailable() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLivePage()
                .openPage()
                .signIn();
        new LogInOutlookLivePage().createNewAccountViaLink()
                .signUp(account);
        new CreateAPasswordPage().readMicrosoftServicesAgreement();

        String actualMicrosoftServicesAgreementPageContent = new MicrosoftServicesAgreementPage()
                .getMicrosoftServicesAgreementPageContent();
        String expectedMicrosoftServicesAgreementPageTitle = resourceBundle
                .getString("'Microsoft Services Agreement' page title");

        String alertMessageIfTestFails = "'Microsoft Services Agreement' link is not available or does not work";

        assertThat(alertMessageIfTestFails, actualMicrosoftServicesAgreementPageContent
                .contains(expectedMicrosoftServicesAgreementPageTitle));
    }
}