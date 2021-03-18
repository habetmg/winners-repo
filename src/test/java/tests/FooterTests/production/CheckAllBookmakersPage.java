package tests.FooterTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllBookmakersPage extends BaseTest {
    @Test(description = "WN-283 : OK for all bookmakers")
    public void checkAllBookmakersPage() throws InterruptedException {
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains("All bookmakers");
        softAssert.assertEquals(quickCardPage.quickCardCount(), 5);
        softAssert.assertEquals(mainPage.getPageSubHeaderText(), "All Bookmakers");
        softAssert.assertEquals(mainPage.getPageDescriptionText(), "On Winners.net you can find the best reviews for all bookmakers, " +
                "all in one place. Sports or esports, Winners.net offers a great selection of bookmakers that cover them all. " +
                "Read, compare, review, and choose your favorite betting sites with Winners.net.");
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 1);
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(filterPage.countryIs(), "Armenia");
        softAssert.assertEquals(quickCardPage.quickCardCount(), 3);
        mainPage.chooseComparisonSubtab();
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 0);
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains("All bookmakers");
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 1);
        softAssert.assertAll();
    }
}
