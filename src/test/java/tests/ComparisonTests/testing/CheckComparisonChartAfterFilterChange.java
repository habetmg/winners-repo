package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;


public class CheckComparisonChartAfterFilterChange extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createFiveBookmakers() throws Exception {
        bookmakerApiCalls.createFiveBookmakers();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_5);
    }

    @Test(description = "WN-150 Comparison-Chart after changing filter")
    public void checkComparisonChartAfterFilterChange() throws InterruptedException {

        Integer numberOfBookmakersAdded=4;
        Integer startingFrom=1;

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(2000);

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded,startingFrom,"accenting").size());

        bookmakersTablePage.selectValueInSortByDropDown("Sport / Game Coverage");
        comparisonPage.clickOnShowAllButton();
        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage.getNumberOfCheckedBookmakers().size());

        Assert.assertEquals(lstAfterSort,lstBeforeSort,"Legends before and after sort DO NOT MATCH!!!");
    }
    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);

    }
}
