package tests.QuickCardTests;

import api.BookmakerApiCalls;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckQuickCardSortedByRankings extends BaseTest {

    @BeforeMethod(description = "test")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createFiveBookmakers();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-1", 1, BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_1);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-2", 2, BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_2);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-3", 3, BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_3);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-4", 4, BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_4);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-5", 5, BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_5);
    }

    @Test()
    public void test() {
         mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmaker_Name_1, 1),
                new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test-1", "REVIEW", "WEBSITE")));
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmaker_Name_2, 2),
                new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test-2", "REVIEW", "WEBSITE")));
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmaker_Name_3,3),
                new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test-3", "REVIEW", "WEBSITE")));
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmaker_Name_4,4),
                new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test-4", "REVIEW", "WEBSITE")));
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(BookmakerApiCalls.bookmaker_Name_5,5),
                new ArrayList<>(Arrays.asList("5.9 / 10", "Text-to-display-test-5", "REVIEW", "WEBSITE")));
        softAssert.assertAll();
    }

//    @AfterTest()
//    public void deleteCreatedBookmakers() throws Exception {
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
//    }
}
