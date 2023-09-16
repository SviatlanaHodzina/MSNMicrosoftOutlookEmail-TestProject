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
//fix the issue
//- In case step of entering password is put first, then  checking if the checkbox is selected,
//  then steps are replaced: firstly checkbox goes, then password input:
//                      .enterThePasswordInCreateAPasswordPage(account)
//                      .emailInfoCheckBoxIsSelected()
//- The issue is: either the code selects the tested checkbox twice, so test result is the false fail
//                or the tested checkbox is selected by default, so the engineer-tester should unselected it
//                and select again for testing the given function
public class EmailInfoCheckBoxInCreateAPasswordPageTest extends RequiredConditions {
    @Test(description = "Verifies if the checkbox concerning receiving information about " +
            "Microsoft products and services is available to be selected")
    @Parameters("browser")
    public void verifyThatEmailInfoCheckBoxIsAvailable() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        new CreateAPasswordPage().selectCheckBoxToGetInfoAboutMicrosoft();
        new CreateAPasswordPage().selectCheckBoxToGetInfoAboutMicrosoft();
        boolean emailInfoCheckBoxIsSelected =
                new CreateAPasswordPage().emailInfoCheckBoxIsSelected();

        String alertMessageIfTestFails = "Checkbox for receiving " +
                "information about Microsoft products and services is " +
                "not available or the given checkbox is selected by default";

        assertTrue(alertMessageIfTestFails, emailInfoCheckBoxIsSelected);
    }
}