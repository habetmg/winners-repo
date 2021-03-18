package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;

public class CheckComparisonChartColorChanging extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker: Important Bookmaker list has not to be empty")
    public void createTwoBookmakers() throws Exception {
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
    }

    @Test(description = "WN-107 Comparison-Color-Changing (Low overall rating chart changes color")
    public void checkComparisonChartColorChangingProd() throws InterruptedException {

        int numberOfBookmakersAdded=2;
        int startingFrom=1;
        String addingSequence="accenting";

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(2000);

        ArrayList directAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
               (numberOfBookmakersAdded, startingFrom,addingSequence).size());

        Thread.sleep(5000);

        comparisonPage.addNBookmakersFromNbr(numberOfBookmakersAdded, startingFrom,addingSequence);

        numberOfBookmakersAdded=2;
        startingFrom=2;
        addingSequence="descending";

        Thread.sleep(2000);

        ArrayList reverseAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded, startingFrom,addingSequence).size());
        Assert.assertEquals(directAdd,reverseAdd, "Color-change FAILED!!!");
    }
    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
    }
}
