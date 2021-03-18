package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllCountriesShouldKeptAfterPageChanges extends BaseTest {
    @Test(description = "WN-250 : (OK) All Countries should kept after page changes")
    public void checkingIfAllCountriesKeptAfterPageChanges() throws InterruptedException {
        filterPage.selectCountryInFilterSection("All Countries");
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/?countries=__all__&sort=-reviews.overall.rating");
        footerPage.clickOnWinnersNetSubMenuPages("Contact");
        mainPage.waitToTitleContains("Contact Us");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/contact");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains("Compare Betting Sites");
        softAssert.assertEquals(filterPage.countryIs(),"All Countries");
        softAssert.assertAll();
    }
}
