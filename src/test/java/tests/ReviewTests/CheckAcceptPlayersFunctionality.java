package tests.ReviewTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckAcceptPlayersFunctionality extends BaseTest {

    @Test(description = "Check Accept players functionality")
    public void checkAcceptPlayersFunctionality() throws InterruptedException, IOException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 60);
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        Assert.assertEquals(reviewPage.getBookmakerReviewPageTitle(), "Bet365 Review");
        Assert.assertEquals(reviewPage.getAcceptPlayers(),"Accept players from " + mainPage.getIpBasedCountry());
        reviewPage.clickOnNavBarItem("Basic Info");
        reviewPage.clickOnWebSiteUrl();
        mainPage.handleWindowByTitle("Open Account Offer");
        Assert.assertEquals(mainPage.getPageUrl(),"https://www.bet365.com/olp/open-account?affiliate=365_00942275");
    }
}
