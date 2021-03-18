package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckFacebookPage extends BaseTest {
    @Test(description = "WN-42 : (OK) Facebook Official Page")
    public void checkFacebookIcon() throws InterruptedException {
        footerPage.clickOnSocialIcons("facebook");
        mainPage.handleWindowByTitle("Winners.Net - Home | Facebook");
        Assert.assertEquals(footerPage.getWinnersNetFacebookPageUrl(), "https://www.facebook.com/winnersnetgg");
    }
}
