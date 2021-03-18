package tests.ComparisonTests.production;


import org.testng.annotations.Test;
import pages.BookmakersTablePage;
import tests.base.BaseTest;


class CheckComparisonChartAvailabilityForDifferentBookmakersProd extends BaseTest {

    @Test(description = "WN-132 Checks comparison chart ability when bookmaker deleting in bookmakers list.")
    public void checkComparisonChartAvailabilityForDifferentBookmakers() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        filterPage.selectCountryInHeader("All Countries");
        comparisonPage.clickOnShowAllButton();

        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(), 46
                ,"Not all bookmakers are available");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.clickOnShowAllButton();

        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(),46,
                "Not all bookmakers are available after Comparison button click");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Midnite","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("GG.BET","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("BetMGM","Plus");

        filterPage.selectCountryInHeader("Armenia");

        softAssert.assertFalse(bookmakersTablePage.bookmakerNameInBookmakerTable("Midnite")
                ,"Bookmaker should not be in the list!!!");
        softAssert.assertFalse(bookmakersTablePage.bookmakerNameInBookmakerTable("BETMGM")
                ,"Bookmaker should not be in the list!!!");

        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("rgb(9, 105, 250)GG.BET")
                ,"Wrong chart is Displayed!!!");//rgb(9, 105, 250)GG.BET: GG.BET in blue (250)

        softAssert.assertAll();
    }
}

//How to set a Request for Prod API?