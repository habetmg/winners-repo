package tests.BookmakersTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckOpenBookmakersWebSiteFromBookmakersTable extends BaseTest {

    @Test(description = "Open bookmakers site from bookmakers table")
    public void OpenBookmakersWebSiteFromBookmakersTable() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Website");
        mainPage.handleWindowByTitle("Betway CA Sports offer");
        softAssert.assertEquals(mainPage.getPageUrl(),"https://betway.com/bwp/sportsoffercan/en-ca/");

        mainPage.handleWindowByTitle("Compare Betting Sites - Winners.net");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Website");
        mainPage.handleWindowByTitle("Open Account Offer");
        softAssert.assertEquals(mainPage.getPageUrl(),"https://betway.com/bwp/sportsoffercan/en-ca/");

        mainPage.handleWindowByTitle("Compare Betting Sites - Winners.net");
        bookmakersTablePage.clickOnButtonsByBookmakerName("1XBET", "Website");
        mainPage.handleWindowByTitle("First Deposit ⇒ 1xbet.com");
        softAssert.assertEquals(mainPage.getPageUrl(),"https://x-1xbet-58189.world/bonus/rules/1st/?tag=d_572609m_97c_");

        softAssert.assertAll();
    }
}
