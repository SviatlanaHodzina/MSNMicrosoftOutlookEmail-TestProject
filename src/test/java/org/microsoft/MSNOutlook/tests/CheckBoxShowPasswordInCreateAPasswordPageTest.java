package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CheckBoxShowPasswordInCreateAPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if the checkbox 'Show password' is available to be selected")
    public void verifyThatCheckBoxShowPasswordIsAvailable() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        boolean showPasswordCheckBoxIsSelected = new CreateAPasswordPage()
                .enterThePassword(account).showPasswordCheckBoxIsSelected();

        String alertMessageIfTestFails = "'Show password' checkbox is not available";

        assertTrue(alertMessageIfTestFails, showPasswordCheckBoxIsSelected);
    }
}