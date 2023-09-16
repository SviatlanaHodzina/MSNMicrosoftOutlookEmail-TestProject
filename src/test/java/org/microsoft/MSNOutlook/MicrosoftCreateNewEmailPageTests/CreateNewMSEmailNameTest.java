package org.microsoft.MSNOutlook.tests.MicrosoftCreateNewEmailPageTests;

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
 // edit code
public class CreateNewMSEmailNameTest extends RequiredConditions {

    @Test(description = "Verifies a possibility of a new email's name creation with domain @outlook.")
    @Parameters("browser")
    public void verifyThatOneCanCreateANewEmailNameWithOutlookDomain() throws MalformedURLException {
        MSAccount account = AccountCompiler.withCredentialFromProperty();
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage().createNewAccount()
                .signUp(account);
        String actualAvailableEmailNameAccountOutlook = new CreateAPasswordPage().getAvailableEmailAccountDisplayed();

        String expectedAvailableEmailNameAccountOutlook = account.getEmailName().concat("@")
                .concat(account.getDomain());

        boolean availableEmailNameAccountOutlookIsDisplayed = actualAvailableEmailNameAccountOutlook
                .contains(expectedAvailableEmailNameAccountOutlook);

        String alertMessageIfTestFails = "WebDriver couldn't get the text attribute of the Element " +
                "or Created Email Name does not match the one displayed on the top of Password Page";

        assertTrue(alertMessageIfTestFails, availableEmailNameAccountOutlookIsDisplayed);
    }
}