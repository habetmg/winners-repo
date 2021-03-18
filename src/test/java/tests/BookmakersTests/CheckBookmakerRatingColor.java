package tests.BookmakersTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;

public class CheckBookmakerRatingColor extends BaseTest {

    @BeforeMethod(description = "Create Bookmakers")
    public void publishBookmakers() throws Exception {
        bookmakerApiCalls.createFiveBookmakers();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_1, BookmakerApiCalls.bookmaker_Name_1);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_1);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_2, BookmakerApiCalls.bookmaker_Name_2);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_2);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_3, BookmakerApiCalls.bookmaker_Name_3);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_3);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_4, BookmakerApiCalls.bookmaker_Name_4);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_4);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_5, BookmakerApiCalls.bookmaker_Name_5);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_5);
    }
}
