package tests.BookmakersTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCountryFiledBoundaries extends BaseTest {

    @Test(description = "Check country field boundaries")
    public void checkCountryFieldBoundaries() {
        filterPage.selectCountryInFilterSection("Palestinian Territory,Occupied");
        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(),"Palestinian Territory, Occupied");
        softAssert.assertEquals(filterPage.getSelectedCountryInSubHeader(), "Palestinian Territory, Occupied");
        filterPage.selectCountryInFilterSection("North Macedonia, Republic of");
        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(),"North Macedonia, Republic of");
        softAssert.assertEquals(filterPage.getSelectedCountryInSubHeader(), "North Macedonia, Republic of");
        softAssert.assertAll();
    }
}
