package tests.BookmakersTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckBookmakerSortingOptions extends BaseTest {

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

    @Test(description = "Check Bookmaker sorting options")
    public void checkBookmakerSortingOptions() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");

        bookmakersTablePage.selectValueInSortByDropDown("Rankings");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));

        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_1,
                BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_5)));

        bookmakersTablePage.selectValueInSortByDropDown("Sport / Game Coverage");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));


        bookmakersTablePage.selectValueInSortByDropDown("Responsible Gaming");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));


        bookmakersTablePage.selectValueInSortByDropDown("Deposit & Withdrawal");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));


        bookmakersTablePage.selectValueInSortByDropDown("Onboarding Process");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));

        bookmakersTablePage.selectValueInSortByDropDown("Customer Service");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));

        bookmakersTablePage.selectValueInSortByDropDown("Utility Features");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_5,
                BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_1)));
        softAssert.assertAll();
    }

    @AfterTest(description = "Delete created bookmakers", alwaysRun = true)
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
    }
}
