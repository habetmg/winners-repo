package tests.ComparisonTests.production;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBonusComparisonButtonFunctionalityProd extends BaseTest {

    @Test(description = "WN-114 Bookmakers full review checking with changed country and checking list and chart after that. ")
    public void checkBonusComparisonButtonFunctionality() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        filterPage.selectCountryInHeader("All Countries");
        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not selected!!!");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        Thread.sleep(2000);
        softAssert.assertEquals(webDriver().getCurrentUrl(),"https://winners.net/review/betway"
                ,"Wrong Url - Selected!!!");
        comparisonPage.clickOnBonusesButton();
        Thread.sleep(5000);
        softAssert.assertTrue(comparisonPage.bookmakerNameInHeader ().contains("Betway")
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
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("rgb(9, 105, 250)Betway")
                ,"Wrong Name in legend!!!");
        softAssert.assertTrue(bookmakersTablePage.valueInSortByDropDown().equals("Bonus Amount")
                ,"Wrong Filter was set!!!");

        softAssert.assertAll();
//          * List of bookmakers sorted by Bonus Amount
    }
}
