package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.tests.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class EmailInfoCheckBoxInCreateAPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if the checkbox concerning receiving information about " +
            "Microsoft products and services is available to be selected")
    public void verifyThatEmailInfoCheckBoxIsAvailable() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        boolean emailInfoCheckBoxIsSelected = new CreateAPasswordPage()
                .enterThePassword(account).emailInfoCheckBoxIsSelected();

        String alertMessageIfTestFails = "Checkbox concerning receiving " +
                "information about Microsoft products and services is not available";

        assertTrue(alertMessageIfTestFails, emailInfoCheckBoxIsSelected);
    }
}
