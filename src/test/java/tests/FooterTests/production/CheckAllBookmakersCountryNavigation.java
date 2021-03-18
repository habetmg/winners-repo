package tests.FooterTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllBookmakersCountryNavigation extends BaseTest {
    @Test(description = "WN-285 : OK for all bookmakers country navigation")
    public void checkingIfCountryKeptAfterAllBookmakerPageNavigation() throws InterruptedException {
        filterPage.selectCountryInHeader("Albania");
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains("All bookmakers");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains("Best Betting Sites");
        Assert.assertEquals(filterPage.countryIs(), "Albania");
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 0);
    }
}
