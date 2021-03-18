package tests.CleanURLsTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;


public class CheckHeaderPageURLs extends BaseTest {

    @Test(description = "WN-146 : (OK) Header/Footer URL")
    public void checkHeaderPagesURLs() throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains("Best Betting Sites");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/best-sports-betting-sites");
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        mainPage.waitToTitleContains("Best Esports Betting Sites");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/best-esports-betting-sites");
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        mainPage.waitToTitleContains("LoL");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/best-lol-betting-sites");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/bookmaker-comparison");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        mainPage.waitToTitleContains("CS:GO");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/best-csgo-betting-sites");
        mainPage.selectTabInHeader("Analysis and Predictions");
        mainPage.waitToTitleContains("Analysis and Predictions");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/analysis-and-predictions");
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        mainPage.waitToTitleContains("Dota2");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/best-dota2-betting-sites");
        softAssert.assertAll();
    }
}
