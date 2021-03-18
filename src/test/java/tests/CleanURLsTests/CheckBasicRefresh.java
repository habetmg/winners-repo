package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBasicRefresh extends BaseTest {
    @Test(description = "WN-244 : (OK) Basic refresh")
    public void checkBasicRefresh() throws InterruptedException {
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(),"Armenia");
        filterPage.selectCountryInHeader("All Countries");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(),"All Countries");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.countryIs(),"All Countries");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/bookmaker-comparison?countries=__all__");
        softAssert.assertAll();
    }
}
