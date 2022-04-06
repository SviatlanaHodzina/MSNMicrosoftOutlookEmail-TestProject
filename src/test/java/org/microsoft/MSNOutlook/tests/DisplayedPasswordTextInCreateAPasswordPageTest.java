package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.CreateAPasswordPage;
import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DisplayedPasswordTextInCreateAPasswordPageTest extends RequiredConditions {

    @Test(description = "Verifies if the entered password by user matches the one " +
            "displayed in a placeholder with selected checkbox 'Show password'")
    public void verifyThatDisplayedPasswordInPlaceholderMatchesTheOneEnteredByUser() {
        MSAccount account = AccountCompiler.withCredentialFromProperty();

        new OutlookLiveComPage()
                .openPage()
                .signIn();
        new LogInPage().goToCreateAccountPage()
                .signUp(account);
        String actualPasswordTextInPlaceholder = new CreateAPasswordPage()
                .enterThePassword(account).getPasswordDisplayedInPlaceholder();

        String expectedPasswordTextDisplayedInPlaceholder = account.getPassword();

        String alertMessageIfTestFails = "Password, displayed in placeholder does not match the one entered by user";

        assertEquals(alertMessageIfTestFails, expectedPasswordTextDisplayedInPlaceholder, actualPasswordTextInPlaceholder);
    }
}