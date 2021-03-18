package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleSourceCodeVideoFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert Source Code Video With Embed Code, " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Source Code Video Using Youtube Embed Code");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check open twitch functionality")
    public void checkArticleSourceCodeVideoFunctionality() throws Exception {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        articlePage.switchToIframe();
        Assert.assertTrue(mainPage.getPageSource().contains("https://www.youtube.com/watch?v=eKUa_7jfOMY"));
        articleApiCalls.addArticleContent("Insert Reddit Embed code");
        mainPage.refreshPage();
        Assert.assertEquals(redditPage.getBlockQuoteText(), "Wettbewerb: Karnevals-, Fastnachts-, Fassenachts-, Fasnachts-, Fasnets-, Faschings-, Fastabends-, Fastelovends- oder Fasteleersgebäck from r/Kochen");
        Assert.assertEquals(mainPage.getPageUrl(), "https://win:w1nPorta2@affiliates.priotix.xyz/analysis-and-predictions/"
                + ArticleApiCalls.articleId + "/" + ArticleApiCalls.articleName);
    }

    @AfterTest(description = "Delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
