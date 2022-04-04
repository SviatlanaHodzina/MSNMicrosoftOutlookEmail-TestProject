package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.pages.LogInPage;
import org.microsoft.MSNOutlook.pages.OutlookLiveComPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LogInPageOpeningTest extends RequiredConditions {

    //    The test with properties from ResourceBundle does work, tested on existed email: 02 April 2022
    @Test(description = "Verifies if LogInPage can be opened for signing in")
    public void verifyIfLogInPageCanBeOpened() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.US);

        new OutlookLiveComPage()
                .openPage()
                .signIn();

        String actualLogInPageTitleSignIn = new LogInPage().getTitleSignInOfLoginInPage();
        String expectedLogInPageTitleSignIn = resourceBundle.getString("Sign in");

        Assert.assertEquals(actualLogInPageTitleSignIn, expectedLogInPageTitleSignIn);
    }
}