package tests.QuickCardTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckHoverOnQuickCardFunctionalityToDo extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image 4. Add Data in Bookmaker Profile section" +
            "5. Add Bonus for bookmaker, 6. Add data in 'Reviews' tab, 7. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test",1, BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerSEO(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check hover on Quick card functionality")
    public void checkHoverOnQuickCardFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        quickCardPage.hoverOnBookmakerImage();
    }

    @AfterTest(description = "Delete created Bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
