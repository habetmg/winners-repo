package tests.CleanURLsTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCountryShouldKeptAfterNavigation extends BaseTest {
    @Test(description = "WN-238 : (OK) Country should kept after navigation")
    public void CheckingCountryKeptAfterNavigation () throws InterruptedException {
        filterPage.selectCountryInHeader("All Countries");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(filterPage.countryIs(), "All Countries");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(), "All Countries");
        mainPage.selectTabInHeader("Analysis and Predictions");
        Thread.sleep(2000);
        analysisPage.selectArticleByNumber(1);
        mainPage.clickOnHomepageIcon();
        Assert.assertEquals(filterPage.countryIs(), "All Countries");
    }
}
