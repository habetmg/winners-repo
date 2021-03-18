package tests.CleanURLsTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckFiltersAreWorkingWhenURLIsClean extends BaseTest {
    @Test(description = "WN-249 : (OK) Filters are working when url is clean")
    public void checkFiltersWorkWhenURLIsClean() throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        Assert.assertEquals(filterPage.countryIs(), "Armenia");
        filterPage.searchBookmakerName("b");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/?countries=5ede36d8f045090013da564a&name=b&sort=-reviews.overall.rating");
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/?countries=5ede36d8f045090013da564a");
        filterPage.clickOnExpandIconsBySectionName("Other Products");
        filterPage.selectCheckbox("Other Products","Casino");
        filterPage.searchBookmakerName("Winners.bet");
        softAssert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set");
        softAssert.assertAll();
    }
}
