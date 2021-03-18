package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckUnexpectedRefresh extends BaseTest {
    @Test(description = "WN-245 : (NOK) Unexpected refresh")
    public void unexpectedRefresh() throws InterruptedException {
        filterPage.selectCountryInHeader("Andorra");
        Assert.assertEquals(filterPage.countryIs(), "Andorra");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/?countries=5ede36d8f045090013da5644&sort=-reviews.overall.rating");
        footerPage.clickOnWinnersNetSubMenuPages("About Us");
        mainPage.waitToTitleContains("About Us");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/about-us");
        mainPage.refreshPage();
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains("Find the best betting sites");
        Assert.assertEquals(filterPage.countryIs(),"Andorra");
        softAssert.assertAll();
    }
}
