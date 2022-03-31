package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenedLogInPageTest extends RequiredConditions {

    // The test does work: 29 March 2022
    @Test(description = "Verifies if one can open SignIn Page")
    public void verifyIfYouCanOpenSignInPage() {

        String expectedTitle = "Microsoft";
        new OutlookLiveComPage()
                .openPage()
                .signIn();

        String signInPageTitle = new LogInPage().getTitleOfLoginInPage();

        Assert.assertEquals(signInPageTitle, expectedTitle);
    }
}