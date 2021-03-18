package tests.QuickCardTests;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckExistingQuickCardFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image 4. Add Data in Bookmaker Profile section" +
            "5. Add Bonus for bookmaker, 6. Add data in 'Reviews' tab, 7. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createTwoBookmakers();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("quick_card_name_1", 1, BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_1);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.addQuickCardInfo("quick_card_name_2", 1, BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_2);
    }

    @Test(description = "Check Existing quick card functionality")
    public void checkExistingQuickCardFunctionality() throws Exception {
        Assert.assertEquals(bookmakerApiCalls.publishBookmakerWithIncorrectRating(BookmakerApiCalls.bookmaker_Id_2),"{\"errors\":[{\"slug\":\"bookmaker-quick-card-rank-invalid\",\"message\":\"Quick card with rank 1 exists. \\n        Check " + BookmakerApiCalls.bookmaker_Name_1 + " bookmaker\",\"details\":{\"path\":\"bookmaker-quick-card-rank\"}}],\"message\":\"Quick card with rank 1 exists. \\n        Check "
                + BookmakerApiCalls.bookmaker_Name_1 + " bookmaker\"}");
    }

//    @AfterTest(description = "Delete created Bookmaker")
//    public void deleteCreatedBookmakers() throws Exception {
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
//    }
}
