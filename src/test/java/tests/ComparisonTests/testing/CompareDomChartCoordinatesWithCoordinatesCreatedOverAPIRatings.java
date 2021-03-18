package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


class CompareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Retrieves coordinates from comparison chart and compares with ones created on ratings from API")
    public void compareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Plus");
        System.out.println("\n"+BookmakerApiCalls.bookmakerName);
        System.out.println("Bookmaker ID: " + BookmakerApiCalls.bookmakerId+"\n");

        Assert.assertEquals("API Overall Rating and Overall Rating Calculated on DOM Coordinates - DO NOT MATCH!!!",
                comparisonPage.getOverallRatingFromApi(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)),
                comparisonPage.calculateOverallRatingOnDomCoordinates(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)));

        Assert.assertEquals(comparisonPage.createCoordinatesOnApiRatings(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId))
                ,comparisonPage.getComparisonChartCoordinates());
    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
