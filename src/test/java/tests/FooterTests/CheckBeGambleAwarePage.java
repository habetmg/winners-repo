package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBeGambleAwarePage extends BaseTest {
    @Test(description = "WN-44 : (OK) BeGambleAware.org Page")
    public void checkBeGambleAwareIcon() throws InterruptedException {
        footerPage.ClickOnSocialPages("beGambleAware");
        mainPage.handleWindowByTitle("BeGambleAware®: Gambling Help & Gambling Addiction | BeGambleAware");
        Assert.assertEquals(footerPage.getBeGambleAwarePageUrl(), "https://www.begambleaware.org/");
    }
}
