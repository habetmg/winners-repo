package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;

public class CheckComparisonChartColorChangingProd extends BaseTest {

    @Test(description = "WN-107 Comparison-Color-Changing (Low overall rating chart changes color")
    public void checkComparisonChartColorChangingProd() throws InterruptedException {

        Integer numberOfBookmakersAdded=2;
        Integer startingFrom=1;

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(2000);

        ArrayList directAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
               (numberOfBookmakersAdded, startingFrom,"accenting").size());

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Plus");

        numberOfBookmakersAdded=2;
        startingFrom=2;

        ArrayList reverseAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded, startingFrom,"descending").size());
        Assert.assertEquals(directAdd,reverseAdd, "Color-change FAILED!!!");
    }
}
