package org.microsoft.MSNOutlook.tests;

import org.microsoft.MSNOutlook.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WindowsHandlingTest extends RequiredConditions {

    @Test(description = "Verifies windows handling is enable")
    @Parameters("browser")
    public void verifyThatWindowsHandlingIsEnable() throws MalformedURLException {
        new SignInMicrosoftPage()
                .openNewTabByAction();
        new MicrosoftPage()
                .openPage();
        new LogInMicrosoftPage()
                .signInYourMicrosoftAccount();
        //    String tab1 = driver.getWindowHandle(); // didn't work earlier, recheck now

        //    driver.switchTo().newWindow(WindowType.WINDOW);// didn't work earlier, recheck now
        new SignInMicrosoftPage().openNewTabByAction();
        new OutlookLivePage()
                .openPage();
        new LogInOutlookLivePage()
                .signInYourOutlookAccount();
        //        String tab2 = driver.getWindowHandle();// didn't work earlier, recheck now

        //        driver.switchTo().window(tab1); // didn't work earlier, recheck now
        new SignInMicrosoftPage().handleWindowTabBackByAction();
        String titleSignInMicrosoftPage = new SignInMicrosoftPage().getTitleOnSignInPage();

        new SignInOutlookPage().handleWindowTabForwardByAction();
        String titleSignInOutlookLivePage = new SignInOutlookPage().getTitleOnSignInPage();

        Assert.assertEquals(titleSignInOutlookLivePage, titleSignInMicrosoftPage);
    }
}