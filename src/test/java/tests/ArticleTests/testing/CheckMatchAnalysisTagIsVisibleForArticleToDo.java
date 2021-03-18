package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckMatchAnalysisTagIsVisibleForArticleToDo extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Insert Content 3. Upload article image " +
            "4. Set winners.net in settings 5. Set 'Dota 2' article tag 6. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleTag("match-predictions-and-analysis", "Match predictions");
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check 'Dota 2' tag visible for created article in Analysis page")
    public void checkDota2TagIsVisibleForArticle() {
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(analysisPage.getSelectedTagForArticle(ArticleApiCalls.articleName), "Match predictions");
    }

    @AfterTest(description = "Delete Created Article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
