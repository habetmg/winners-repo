package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBookmakerNavigation extends BaseTest {
    @Test(description = "WN-222 : (OK) Bookmaker filters on footer")
    public void checkBestBookmakersButtonsFromFooter() {
        footerPage.clickOnBookmakersSubMenuPages("Best Bookmakers");
        mainPage.waitToTitleContains("Best Betting Sites");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Best Sports Betting Sites");
        footerPage.clickOnBookmakersSubMenuPages("Best Esports Bookmakers");
        mainPage.waitToTitleContains("Best Esports Betting Sites");
        softAssert.assertEquals(footerPage.checkEsportsHeaderComponent(), "Best Esports Betting Sites");
        footerPage.clickOnBookmakersSubMenuPages("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Bookmaker Comparison");
        footerPage.clickOnBookmakersSubMenuPages("Bookmaker Recommender");
        mainPage.waitToTitleContains("Choose Your Bookmaker");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Bookmaker Recommender");
        softAssert.assertAll();
    }
}
