package tests.ReviewTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

//PRODUCTION
public class CheckShowMoreButtonFunctionalityForCurrenciesAndCountries extends BaseTest {

    @Test(description = "Check show more buttons functionality for Currencies and Countries")
    public void checkShowMoreButtonFunctionalitiesForCurrenciesAndCountries() {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.selectBookmakerFromBookmakersTable("Bet365");
        reviewPage.clickOnNavBarItem("Additional Info");
        reviewPage.clickOnShowMoreButtonByFieldName("Excluded countries");
        softAssert.assertEquals(reviewPage.getExcludedCountries(), new ArrayList<>(Arrays.asList("Afghanistan", "American Samoa", "Angola", "Belgium", "Burundi", "Colombia", "Congo", "Cuba", "Czech Republic", "Congo", "the Democratic Republic of the", "Equatorial Guinea", "Eritrea", "France", "French Guiana", "Guadeloupe", "Guam", "Guinea-Bissau", "Haiti", "Hong Kong", "Iran", "Islamic Republic of", "Iraq", "Israel", "Libya", "Macao", "Martinique", "Monaco", "Myanmar", "Netherlands", "North Korea", "Philippines", "Poland", "Portugal", "Puerto Rico", "Romania", "Singapore", "Somalia", "South Africa", "Sudan", "Syrian Arab Republic", "Tajikistan", "Turkey", "Turkmenistan", "Virgin Islands", "U.S.", "Uzbekistan", "Venezuela", "Yemen", "Zimbabwe", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Louisiana")));
        reviewPage.clickOnShowMoreButtonByFieldName("Currency Accepted");
        softAssert.assertEquals(reviewPage.getCurrencies(), new ArrayList<>(Arrays.asList("EUR", "USD", "GBP", "CAD", "NOK", "SEK", "AUD", "CHF", "DKK", "NZD", "CNY", "JPY", "BRL", "ARS", "MXN", "IDR", "THB")));
        softAssert.assertAll();
    }
}
