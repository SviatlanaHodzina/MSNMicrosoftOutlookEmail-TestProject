package org.microsoft.MSNOutlook.tests.OutlookPageLinksAndLogoTests;

import org.microsoft.MSNOutlook.pages.LogInOutlookLivePage;
import org.microsoft.MSNOutlook.pages.OutlookLivePage;
import org.microsoft.MSNOutlook.tests.RequiredConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class MicrosoftLogoDisplayOnLogInPageTest extends RequiredConditions {
    @Test(description = "Verifies if Microsoft Logo is displayed on LogIn Page")
    @Parameters("browser")
    public void verifyMicrosoftLogoDisplayOnLogInPage() throws MalformedURLException {
        new OutlookLivePage()
                .openPage()
                .signIn();

        String actualLogoOnLogInPage = new LogInOutlookLivePage().getTitleLogoOfLoginInPage();
        String expectedLogoOnLogInPage = "Microsoft";

        Assert.assertEquals(actualLogoOnLogInPage, expectedLogoOnLogInPage);
    }
}