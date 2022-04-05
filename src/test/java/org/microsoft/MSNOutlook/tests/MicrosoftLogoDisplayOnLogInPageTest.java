package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MicrosoftLogoDisplayOnLogInPageTest extends RequiredConditions {

    //    The test  does work, tested: 02 April 2022
    @Test(description = "Verifies if Microsoft Logo is displayed on LogIn Page")
    public void verifyMicrosoftLogoDisplayOnLogInPage() {
        new OutlookLiveComPage()
                .openPage()
                .signIn();

        String actualLogoOnLogInPage = new LogInPage().getTitleLogoOfLoginInPage();
        String expectedLogoOnLogInPage = "Microsoft";

        Assert.assertEquals(actualLogoOnLogInPage, expectedLogoOnLogInPage);
    }
}