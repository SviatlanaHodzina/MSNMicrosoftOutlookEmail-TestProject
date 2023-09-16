package org.microsoft.MSNOutlook.tests.OutlookPageCreateNewEmailTests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertTrue;

public class CheckBoxShowPasswordInCreateAPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if the checkbox 'Show password' is available to be selected")
    @Parameters("browser")
    public void verifyThatCheckBoxShowPasswordIsAvailable() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage()
                .signIn();
        new LogInOutlookLivePage().createNewAccountViaLink()
                .signUp(account);
        boolean showPasswordCheckBoxIsSelected = new CreateAPasswordPage()
                .enterThePasswordInCreateAPasswordPage(account).showPasswordCheckBoxIsSelected();

        String alertMessageIfTestFails = "'Show password' checkbox is not available";

        assertTrue(alertMessageIfTestFails, showPasswordCheckBoxIsSelected);
    }
}