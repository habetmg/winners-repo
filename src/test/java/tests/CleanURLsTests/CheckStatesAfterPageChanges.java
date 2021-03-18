package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckStatesAfterPageChanges extends BaseTest {
    @Test(description = "WN-251 : (OK) States after page changes")
    public void checkStatesAfterNavigation()  {
        filterPage.selectCountryInHeader("United States");
        filterPage.selectCountryStateInHeader("Minnesota");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(filterPage.countryIs(), "United States");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        softAssert.assertEquals(filterPage.countryIs(), "United States");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota");
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.countryIs(), "United States");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota");
        softAssert.assertEquals(bookmakersTablePage.checkEmptyBookmakersTable(), true);
        softAssert.assertAll();
    }
}
