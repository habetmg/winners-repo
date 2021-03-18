package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckFindBookmakerByBookmakerName extends BaseTest {

    @Test(description = "WN-85 (OK) Filter by Bookmaker name")
    public void filterBySportsCoveredSection() throws Exception {
        filterPage.searchBookmakerName("book");
//        softAssert.assertEquals(filterPage.getSearchBookmakerFieldValue(), "book");
        filterPage.clickOnWinnersLogo();
    }
}
