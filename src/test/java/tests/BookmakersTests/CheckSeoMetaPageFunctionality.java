package tests.BookmakersTests;

import api.BookmakerApiCalls;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckSeoMetaPageFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Best Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerSEO(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check Seo meta page functionality")
    public void checkSeoMetaPageFunctionality() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
        softAssert.assertEquals(mainPage.getTitle(), "Meta-title-test");
        bookmakerApiCalls.updateBookmakerSEO(BookmakerApiCalls.bookmakerId);
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getTitle(),"Meta-title-test-edit");
        softAssert.assertAll();
    }

//    @AfterTest(description = "Delete created Bookmaker")
//    public void deleteCreatedBookmaker() throws Exception {
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
//    }
}
