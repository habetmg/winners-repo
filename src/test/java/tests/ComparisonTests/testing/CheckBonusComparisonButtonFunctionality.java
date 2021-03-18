package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBonusComparisonButtonFunctionality extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "WN-114 Bookmakers full review checking with changed country and checking list and chart after that. ")
    public void checkBonusComparisonButtonFunctionality() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.clickOnShowAllButton();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        Thread.sleep(2000);
        softAssert.assertEquals(webDriver().getCurrentUrl()
                ,"https://win:w1nPorta2@affiliates.priotix.xyz/review/"
                        +BookmakerApiCalls.bookmakerName,"Wrong Url - Selected!!!");

        comparisonPage.clickOnBonusesButton();
        Thread.sleep(2000);
        softAssert.assertTrue(comparisonPage.bookmakerNameInHeader ().contains(BookmakerApiCalls.bookmakerName)
                ,"Wrong name was Displayed!!!");
        softAssert.assertEquals(comparisonPage.bookmakerNameIsInHeader (),"div.StickyHeader"
                ,"Not in Sticky Header");
        softAssert.assertTrue(comparisonPage.compareWithOthersInHeader ().contains("COMPARE WITH OTHERS")
                ,"Wrong name was Displayed!!!");
        softAssert.assertEquals(comparisonPage.compareWithOthersIsInHeader (),"div.StickyHeader"
                ,"Not in Sticky Header");
        softAssert.assertTrue(comparisonPage.websideInHeader ().contains("WEBSITE")
                ,"Wrong name was Displayed!!!");
        softAssert.assertEquals(comparisonPage.websideIsInHeader (),"div.StickyHeader"
                ,"Not in Sticky Header");

        comparisonPage.clickBonusComparisonButon();

        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not selected!!!");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1)
                .contains("rgb(9, 105, 250)"+BookmakerApiCalls.bookmakerName),"Wrong legend!!!");
        softAssert.assertTrue(bookmakersTablePage.valueInSortByDropDown().equals("Bonus Amount")
                ,"Wrong Filter was set!!!");
        Thread.sleep(15000);
        softAssert.assertAll();
//          * List of bookmakers sorted by Bonus Amount
    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
