package tests.ReviewTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CheckExcludedCountriesForBookmaker extends BaseTest {

    @Test(description = "Check Excluded Countries for Bookmaker")
    public void checkExcludedCountriesForBookmaker() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        reviewPage.clickOnNavBarItem("Additional Info");
        reviewPage.clickOnShowMoreButtonByFieldName("Excluded countries");
        softAssert.assertEquals(reviewPage.getExcludedCountries(), new ArrayList<>(Collections.singletonList("tag-1")));
        reviewPage.clickOnShowMoreButtonByFieldName("Currency Accepted");
        softAssert.assertEquals(reviewPage.getCurrencies(), new ArrayList<>(Collections.singletonList("tag-1")));
        softAssert.assertAll();
    }
}
