package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckComparisonChartAfterRefreshing  extends BaseTest {
    @Test(description = "WN-247 : (OK) Comparison chart after refreshing")
    public void countryKeepInArticlePage() throws InterruptedException {
        mainPage.chooseComparisonSubtab();
        mainPage.waitToTitleContains("Find the best betting sites");
        Thread.sleep(2000);
        mainPage.addBookmakerToComparisonByPosition(1);
        mainPage.addBookmakerToComparisonByPosition(2);
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2);
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2);
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2);
        softAssert.assertAll();
    }
}
