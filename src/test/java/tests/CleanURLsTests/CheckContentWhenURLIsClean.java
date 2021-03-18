package tests.CleanURLsTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckContentWhenURLIsClean extends BaseTest {
    @Test(description = "WN-243 : (NOK) Check content when URL is clean")
    public void checkBookmakerNotShownWhenRestricted() throws InterruptedException {
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set");
        footerPage.clickOnReviewsSubMenuElement("Winners.bet Review");
        Assert.assertEquals(bookmakersPage.getOverviewTitleText(), "Winners.bet Review");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertTrue(filterPage.getChartElement());
        softAssert.assertEquals(filterPage.countryIs(), "Armenia");
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set");
        softAssert.assertAll();
    }
}
