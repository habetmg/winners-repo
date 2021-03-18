package tests.CleanURLsTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckCountryFilterNavigation extends BaseTest {
    @Test(description = "WN-228 : (OK) Country-filter navigation")
    public void checkHeaderFilterNavigation() throws InterruptedException {
        filterPage.selectCountryInHeader("Belgium");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains("Best Betting Sites");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/best-sports-betting-sites?countries=5ede36d8f045090013da5654");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Football", "Tennis", "Basketball")));
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        softAssert.assertTrue(filterPage.getChartElement());
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/bookmaker-comparison?countries=5ede36d8f045090013da5654");
        softAssert.assertTrue(filterPage.getChartElement());
        mainPage.goToBackPage();
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/best-sports-betting-sites?countries=5ede36d8f045090013da5654");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Football", "Tennis", "Basketball")));
        softAssert.assertAll();
    }
}
