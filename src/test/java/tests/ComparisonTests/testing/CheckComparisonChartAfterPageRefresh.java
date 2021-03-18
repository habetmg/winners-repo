package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;


public class CheckComparisonChartAfterPageRefresh extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
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

    @Test(description = "WN-133 Bookmaker Comparison-Refresh/Back page")
    public void checkComparisonChartAfterPageRefreshProd() throws InterruptedException {

        Integer numberOfBookmakersAdded=2;
        Integer startingFrom=1;

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(3000);

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded,startingFrom,"accenting").size());

        bookmakersTablePage.refreshPage();
        Thread.sleep(3000);
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2, "Full Review");
        Thread.sleep(3000);

        webDriver().navigate().back();
        Thread.sleep(3000);

        comparisonPage.clickOnComparisonButton();
        Thread.sleep(3000);

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage
                .getNumberOfCheckedBookmakers().size());

        Assert.assertEquals(lstAfterSort,lstBeforeSort,"Legends before and after sort DO NOT MATCH!!!");

    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
     }
}
