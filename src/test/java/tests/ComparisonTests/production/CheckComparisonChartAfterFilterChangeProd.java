package tests.ComparisonTests.production;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.List;


public class CheckComparisonChartAfterFilterChangeProd extends BaseTest {

    @Test(description = "WN-150 Comparison-Chart after changing filter")
    public void checkComparisonChartAfterFilterChange() throws InterruptedException {

        Integer numberOfBookmakersAdded=4;
        Integer startingFrom=1;

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(2000);

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded,startingFrom,"accenting").size());

        bookmakersTablePage.selectValueInSortByDropDown("Sport / Game Coverage");

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage.getNumberOfCheckedBookmakers().size());

        Assert.assertEquals(lstBeforeSort,lstAfterSort,"Legends before and after sort DO NOT MATCH!!!");
    }
}
