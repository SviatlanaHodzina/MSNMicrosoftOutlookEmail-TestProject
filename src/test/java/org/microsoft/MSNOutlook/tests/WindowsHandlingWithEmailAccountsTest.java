package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.model.MSAccount;
import org.microsoft.MSNOutlook.pages.*;
import org.microsoft.MSNOutlook.service.AccountCompiler;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

// The test performance is out of the order defined below:
// This attribute value currentSignedInMSAccountName should be obtained after signing in new ProtonmailPage()
//       account:  String currentSignedInMSAccountName = new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();
//       actually method works before it
// code the test-case for handling 2 email addresses windows and interacting with each other:
// Microsoft email nameforqatesting@outlook.com and Proton email qualitytestersurname@protonmail.com
public class WindowsHandlingWithEmailAccountsTest extends RequiredConditions {

    @Test(description = "Verifies windows handling with two email accounts is enable via signed in different accounts names comparing.")
    @Parameters("browser")
    public void verifyThatWindowsHandlingWithTwoEmailAccountsIsEnable() throws MalformedURLException, InterruptedException {

        MSAccount account = AccountCompiler.withCredentialFromProperty();
//        Proton protonAccount = ProtonAccountCompiler.withCredentialFromProperty();
        String secondEmailName = "qualitytestersurname";
        String secondEmailDomain = "@protonmail.com";
        String secondEmailAddress = secondEmailName.concat(secondEmailDomain);
        String passwordOfSecondEmail = "w345sdfgszdgh46sfProton";

        new SignInMicrosoftPage()
                .openNewTabByAction();

        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount()
                .signInWithCorrectEmail(account)
                .enterMSAccountCorrectPassword(account);
        new StaySignedInOfferPage()
                .yesStaySignedIn();

        //    String tab1 = driver.getWindowHandle(); // didn't work earlier, recheck now
        //    driver.switchTo().newWindow(WindowType.WINDOW);// didn't work earlier, recheck now        new MicrosoftPage().openNewTabByAction();

        new SignInMicrosoftPage().openNewTabByAction();
        new ProtonmailPage()
                .openPage();
        new ProtonmailPage()
                .enterCorrectProtonEmailOrSurname(secondEmailAddress)
                .enterProtonCorrectPassword(passwordOfSecondEmail)
                .signInProtonAccountButtonClick();

//        String tab2 = driver.getWindowHandle();// didn't work earlier, recheck now
//        driver.switchTo().window(tab1); // didn't work earlier, recheck now

        new SignInMicrosoftPage().handleWindowTabBackByAction();

        String currentSignedInMSAccountName =
                new SignedInCurrentMSAccountPage().checkCurrentSignedInAccountName();

        new SignInMicrosoftPage().handleWindowTabForwardByAction();

        String currentSignedInProtonAccountName =
                new ProtonSignedInAccountPage().checkCurrentSignedInProtonAccountName();
        new ProtonSignedInAccountPage().hideProtonAccountDropdownContentBox();

        Assert.assertNotEquals(currentSignedInMSAccountName, currentSignedInProtonAccountName);
    }
}