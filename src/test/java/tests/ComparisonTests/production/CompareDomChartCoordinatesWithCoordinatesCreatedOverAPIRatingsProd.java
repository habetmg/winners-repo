package tests.ComparisonTests.production;

import api.BookmakerApiCalls;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


class CompareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatingsProd extends BaseTest {

    @Test(description = "Retrieves coordinates from comparison chart and compares with ones created on ratings from API")
    public void compareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Plus");
        System.out.println("\nBet365");

        Assert.assertEquals("API Overall Rating and Overall Rating Calculated on DOM Coordinates - DO NOT MATCH!!!",
                comparisonPage.getOverallRatingFromApi(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)),
                comparisonPage.calculateOverallRatingOnDomCoordinates(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)));

        Assert.assertEquals(comparisonPage.createCoordinatesOnApiRatings(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId))
                ,comparisonPage.getComparisonChartCoordinates());
    }
}
