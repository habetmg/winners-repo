package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCleanUpURL extends BaseTest {
    @Test(description = "WN-221 : (OK) Clean-up URL")
    public void URLIsCleanAfterBackNavigation() throws InterruptedException {
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains("Best Betting Sites");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/best-sports-betting-sites");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains("Find the best betting sites, bonuses and features");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/");
        softAssert.assertAll();
    }
}
