package tests.FilterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CheckFiltersShouldBeKeptAfterRefreshingPage extends BaseTest {


    @Test(description = "Check Filters should be kept after refreshing page")
    public void checkFiltersShouldBeKeptAfterRefreshingPage() throws InterruptedException {
        filterPage.clickOnExpandIconsBySectionName("Cashout Available");
        filterPage.selectValueForCashoutAvailableSection("Yes");
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.getCheckedCashoutAvailableSectionItem(), "Yes");

        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "tag-1");
        filterPage.selectCheckbox("Esports Covered", "tag-1");
        mainPage.refreshPage();
        filterPage.clickOnExpandIconsBySectionName("Esports Covered");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("tag-1")));

        filterPage.clickOnExpandIconsBySectionName("License Country");
        filterPage.selectCountryInLicenseCountry("Albania");
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.getSelectedLicenseCountry(), "Albania");
        softAssert.assertAll();
    }
}
