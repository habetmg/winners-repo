package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckSortByFilterOnAllCountriesCase extends BaseTest {
    @Test(description = "WN-252 : (OK) Sort-by filter on All countries case")
    public void checkSortByFilter() throws InterruptedException {
        filterPage.selectCountryInHeader("All Countries");
        mainPage.waitToTitleContains("Find the best betting sites");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/?countries=__all__&sort=-reviews.overall.rating");
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/?countries=__all__&sort=name");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName().subList(0,5), new ArrayList<>(Arrays.asList("10bet", "18bet", "1XBET", "22BET","arcanebet")));
        mainPage.clickOnHomepageIcon();
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/?countries=__all__");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName().subList(0,5), new ArrayList<>(Arrays.asList("Betway", "Bet365", "William Hill", "1XBET","VBET")));
        softAssert.assertAll();
    }
}
