package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckCountryKeptOnArticlePage extends BaseTest {
    @Test(description = "WN-242 : (OK) Country kept on article's page")
    public void countryKeepInArticlePage() throws InterruptedException {
        filterPage.selectCountryInHeader("Albania");
        mainPage.selectTabInHeader("Analysis and Predictions");
        mainPage.waitToTitleContains("Analysis and Predictions");
        softAssert.assertEquals(analysisPage.getAnalysisSubheaderText(), "Analysis and Predictions");
        softAssert.assertEquals(mainPage.getUrl(),"https://winners.net/analysis-and-predictions?countries=5ede36d8f045090013da5641");
        analysisPage.selectArticleByNumber(2);
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains("Find the best betting sites");
        Assert.assertEquals(filterPage.countryIs(),"Albania");
        filterPage.searchBookmakerName("pointsbet");
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set");
        softAssert.assertAll();
    }
}
