package tests.ReviewTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBonusComparisonButtonFunctionality extends BaseTest {

    @Test(description="Check 'Bonus Comparison' button funtionality")
    public void checkBonusComparisonButtonFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        reviewPage.clickOnNavBarItem("Bonuses");
        reviewPage.clickOnBonusComparisonButton();
        bookmakersTablePage.clickOnSortByDropDown();
        Assert.assertEquals(bookmakersTablePage.getSelectedItemInSortByDropDown(), "Bonus Amount");

    }
}
