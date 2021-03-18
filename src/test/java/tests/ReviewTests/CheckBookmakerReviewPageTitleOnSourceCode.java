package tests.ReviewTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerReviewPageTitleOnSourceCode extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Best Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check Seo meta page functionality")
    public void checkSeoMetaPageFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.selectBookmakerFromBookmakersTable("Winners.bet");
        softAssert.assertTrue(mainPage.getPageSource().contains("<title>Winners.bet Review, Bonus and information - Winners.net</title>"));
    }

    @AfterTest(description = "Delete created Bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}

