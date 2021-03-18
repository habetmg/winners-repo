package tests.BookmakersTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BookmakerHeaderMenuOptions extends BaseTest {

    @Test(description = "Check Bookmaker Header menu options")
    public void checkBookmakerHeaderMenuOptions() throws InterruptedException {
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        Assert.assertEquals(mainPage.getBestEsportsBettingSitesMenuItems(),new ArrayList<>(Arrays.asList("All Esports Betting Sites", "Best CS:GO Betting Sites",
                "Best Dota2 Betting Sites", "Best LoL Betting Sites")));
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        Assert.assertEquals(mainPage.getSelectedEsportMenuItem(), "Best Dota2 Betting Sites");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Collections.singletonList("Dota 2")));
    }
}
