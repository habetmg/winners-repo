package tests.FilterTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckChangedFilterAfterNavigationPages extends BaseTest {

    @Test(description = "Check changed filter after navigation")
    public void checkChangedFilterAfterNavigationPages() throws InterruptedException {
        filterPage.selectCountryInFilterSection("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries");
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(mainPage.getUrl(),"https://win:w1nPorta2@affiliates.priotix.xyz/analysis-and-predictions?countries=__all__");
        analysisPage.selectArticleByNumber(1);
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries");
        }
    }

