package tests.ComparisonTests.testing;


import api.BookmakerApiCalls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import tests.base.BaseTest;

import java.util.ArrayList;


class CheckComparisonChartAvailabilityForDifferentBookmakers extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createThreeBookmakers() throws Exception {
        bookmakerApiCalls.createThreeBookmakers();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerProfileWithRestrictedArmenia(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerProfileWithRestrictedArmenia(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmaker_Id_3);
    }

    @Test(description = "WN-132 Checks comparison chart ability when bookmaker deleting in bookmakers list.")
    public void checkComparisonChartAvailabilityForDifferentBookmakers() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        comparisonPage.clickOnShowAllButton();

        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(),comparisonApiCalls
                .getTotalNumberOfBookmakersFromApi(),"Numbers of bookmakers Do NOT MATCH");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.clickOnShowAllButton();

        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(),comparisonApiCalls
                .getTotalNumberOfBookmakersFromApi(),
                "Numbers of bookmakers Do NOT MATCH after Comparison button click");

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_1,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2,"Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_3,"Plus");

        filterPage.selectCountryInHeader("Armenia");

        softAssert.assertFalse(bookmakersTablePage.bookmakerNameInBookmakerTable(BookmakerApiCalls.bookmaker_Name_1)
                ,"Bookmaker should not be in the list!!!");
        softAssert.assertFalse(bookmakersTablePage.bookmakerNameInBookmakerTable(BookmakerApiCalls.bookmaker_Name_2)
                ,"Bookmaker should not be in the list!!!");

        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("rgb(9, 105, 250)"
                        +BookmakerApiCalls.bookmaker_Name_3)
                ,"Wrong chart is Displayed!!!");//rgb(9, 105, 250)GG.BET: GG.BET in blue (250)

        softAssert.assertAll();
    }
    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
    }

}

//ինչո՞ւ է total-ի արժեքը 604