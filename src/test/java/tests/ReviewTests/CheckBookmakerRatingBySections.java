package tests.ReviewTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerRatingBySections extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check bookmaker rating by review section name")
    public void checkBookmakerRatingBySections() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakerApiCalls.bookmakerName, "Full Review");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Bonuses"),4);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Sport / Game Coverage"),7);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Responsible Gaming"),7);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Deposit & Withdrawal"),1);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Onboarding Process"),8);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Customer Service"),8);
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Utility Features"),4);
    }

    @AfterTest(description = "Delete Created bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
