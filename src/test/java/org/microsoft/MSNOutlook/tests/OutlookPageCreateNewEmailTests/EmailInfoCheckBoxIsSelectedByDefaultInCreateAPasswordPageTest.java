package org.microsoft.MSNOutlook.tests.OutlookPageCreateNewEmailTests;

import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertTrue;

public class EmailInfoCheckBoxIsSelectedByDefaultInCreateAPasswordPageTest extends RequiredConditions {
    @Test(description = "Verifies if the checkbox for receiving information about " +
            "Microsoft products and services is selected by default")
    @Parameters("browser")
    public void verifyThatEmailInfoCheckBoxIsSelectedByDefault() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        boolean emailInfoCheckBoxIsSelected =
                new CreateAPasswordPage().emailInfoCheckBoxIsSelected();

        String alertMessageIfTestFails = "Checkbox for receiving " +
                "information about Microsoft products and services is " +
                "not selected by default";

        assertTrue(alertMessageIfTestFails, emailInfoCheckBoxIsSelected);
    }
}