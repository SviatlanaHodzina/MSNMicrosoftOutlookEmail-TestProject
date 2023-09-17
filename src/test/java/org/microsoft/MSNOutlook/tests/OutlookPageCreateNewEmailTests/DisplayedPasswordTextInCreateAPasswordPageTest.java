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

import static org.testng.AssertJUnit.assertEquals;

public class DisplayedPasswordTextInCreateAPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if the entered password by user matches the one " +
            "displayed in a placeholder with selected checkbox 'Show password'")
    @Parameters("browser")
    public void verifyThatDisplayedPasswordInPlaceholderMatchesTheOneEnteredByUser() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        String actualPasswordTextInPlaceholder = new CreateAPasswordPage()
                .enterThePasswordInCreateAPasswordPage(account).getPasswordDisplayedInPlaceholder();

        String expectedPasswordTextDisplayedInPlaceholder = account.getPassword();

        String alertMessageIfTestFails = "Password, displayed in placeholder does not match the one entered by user";

        assertEquals(alertMessageIfTestFails, expectedPasswordTextDisplayedInPlaceholder, actualPasswordTextInPlaceholder);
    }
}