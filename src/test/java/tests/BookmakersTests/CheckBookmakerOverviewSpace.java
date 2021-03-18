package tests.BookmakersTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerOverviewSpace extends BaseTest {


    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add  six digits in bookmaker bonus value field")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.addBookmakerProfileWithOverviewSpace(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerSEO(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check bookmaker overview space")
    public void checkBookmakerOverviewSpace() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(reviewPage.getOverviewContent(), "");
    }

    @AfterTest(description = "delete Created Bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
