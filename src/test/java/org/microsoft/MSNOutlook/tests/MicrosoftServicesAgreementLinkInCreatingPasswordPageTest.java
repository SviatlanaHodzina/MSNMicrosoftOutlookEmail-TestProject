package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.MicrosoftServicesAgreementPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.tests.service.AccountCompiler;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.hamcrest.MatcherAssert.assertThat;

public class MicrosoftServicesAgreementLinkInCreatingPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if 'Microsoft Services Agreement' link in lightbox In CreateAPasswordPage is available")
    public void verifyThatMicrosoftServicesAgreementLinkInCreatingPasswordPageIsAvailable() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
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