package tests.QuickCardTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckQuickCardReviewFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image 4. Add Data in Bookmaker Profile section" +
            "5. Add Bonus for bookmaker, 6. Add data in 'Reviews' tab, 7. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test",1, BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerSEO(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check quick card review functionality")
    public void checkQuickCardReviewFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmakerName,1), new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test", "REVIEW", "WEBSITE")));
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmakerName,2), new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test", "REVIEW", "WEBSITE")));
        softAssert.assertAll();
    }

    @AfterTest(description = "delete created bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
