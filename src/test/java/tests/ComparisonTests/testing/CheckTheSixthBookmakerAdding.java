package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


class CheckTheSixthBookmakerAdding extends BaseTest  {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check Functionalities adding six bookmakers to comparison Chart WN-163")
    public void checkTheSixthBookmakerAdding() throws Exception {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(5000);
        comparisonPage.clickOnShowAllButton();
        Thread.sleep(4000);

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName,"Plus");

        softAssert.assertEquals("\"" +bookmakersTablePage.getNumberOfCheckedBookmakers().size()+"\"",
                "\"1\"","Number of selected bookmakers differ!!!");

        comparisonPage.currentUrl0=webDriver().getCurrentUrl();
        Thread.sleep(1000);
        comparisonPage.clickOnShowAllButton();
        Thread.sleep(4000);

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName,"Plus");
        softAssert.assertEquals("\"" +bookmakersTablePage.getNumberOfCheckedBookmakers().size()+"\"",
                       "\"0\"", "Number of selected bookmakers is not 0 !!!" );

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

        bookmakersTablePage.refreshPage();
        Thread.sleep(1000);
        comparisonPage.clickOnShowAllButton();
        Thread.sleep(4000);

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_1,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_3,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_4,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_5,"Plus");

        bookmakersTablePage.refreshPage();
        Thread.sleep(1000);
        webDriver().get(comparisonPage.currentUrl0);
        Thread.sleep(1000);
        comparisonPage.clickOnShowAllButton();
        Thread.sleep(4000);

        softAssert.assertEquals("\"" +bookmakersTablePage.getNumberOfDisabledBookmakers()+"\"",
                "\"5\"","Not 5 bookmakers are checked!!!" );

        bookmakersTablePage.refreshPage();
        Thread.sleep(1000);
        webDriver().get(comparisonPage.currentUrl0);
        Thread.sleep(1000);
        comparisonPage.clickOnShowAllButton();
        Thread.sleep(4000);

        softAssert.assertEquals("\"" +bookmakersTablePage.getNumberOfCheckedBookmakers()+"\"",
                "\"1\"","Number of selected bookmakers differ!!!");
        softAssert.assertEquals( comparisonPage.getNameFromLegendElement(),bookmakerApiCalls.bookmakerName
                ,"Not the same bookmaker was selected after URL refresh!!!");
        softAssert.assertAll();
    }

   @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
   public void deleteCreatedBookmakers() throws Exception {
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
   }
}