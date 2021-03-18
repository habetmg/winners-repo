package tests.BackOfficeTests.testing;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class BookmakersListView extends BaseTest {

    @Test(description = "Check sorting by column names")
    public void checkSortingByColumnNames() throws Exception {
        mainPage.openUrl("https://backoffice.priotix.xyz/");
        backOfficePage.fillEmail("zeus@winesports.com");
        backOfficePage.fillPassword("w1nPorta2");
        backOfficePage.clickOnLoginButton();
        mainPage.openUrl("https://backoffice.priotix.xyz/winners-net/bookmakers");
//        backOfficePage.selectColumnByName("Name");
//        Thread.sleep(3000);
//        Assert.assertTrue(backOfficePage.checkIfDescendingSortedBookmakerNames(backOfficePage.getBookmakerItemsByName("Name")));
//        backOfficePage.selectColumnByName("Name");
//        Thread.sleep(3000);
//        Assert.assertTrue(backOfficePage.checkIfAscendingSortedBookmakerNames(backOfficePage.getBookmakerItemsByName("Name")));
//        backOfficePage.selectColumnByName("Rating");
//        Thread.sleep(3000);
//        Assert.assertTrue(backOfficePage.checkIfRatingSorted(backOfficePage.getBookmakerItemsByName("Rating")));
        Thread.sleep(2000);
//        backOfficePage.selectColumnByName("SE Status");
//        softAssert.assertTrue(backOfficePage.checkIfDatesDescendingSorted(backOfficePage.getBookmakerItemsByName("SE Status")));
        backOfficePage.selectColumnByName("Last Updated");
        Thread.sleep(2000);
        softAssert.assertTrue(backOfficePage.checkIfDatesDescendingSorted(backOfficePage.getBookmakerItemsByName("Last Updated")));
        Thread.sleep(3000);
    }
}

