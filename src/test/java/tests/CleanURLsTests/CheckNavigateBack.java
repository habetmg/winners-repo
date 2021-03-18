package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckNavigateBack extends BaseTest {
    @Test(description = "WN-246 : (OK) Navigate-back")
    public void checkNavigateBackCleanURL () throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        bookmakersTablePage.clickOnBookmakerReview("Betway");
        mainPage.waitToTitleContains("Betway Review");
        Assert.assertEquals(bookmakersPage.getOverviewTitleText(), "Betway Review");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/");
        softAssert.assertEquals(filterPage.countryIs(), "Armenia");
        filterPage.selectCountryInHeader("Algeria");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        softAssert.assertEquals(filterPage.countryIs(), "Algeria");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(filterPage.countryIs(), "Algeria");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/?countries=5ede36d8f045090013da5642&sort=-reviews.overall.rating");
        softAssert.assertAll();
    }
}
