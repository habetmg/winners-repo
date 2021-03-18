package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckFootballTagIsVisibleForArticleToDo extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Insert Content 3. Upload article image 4. Set winners.net in settings 5. Set article status publish now")
    public void beforeTest() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleTag("sports","Football");
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check 'Dota 2' tag visible for created article in Analysis page")
    public void checkDota2TagIsVisibleForArticle() {
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(analysisPage.getSelectedTagForArticle(ArticleApiCalls.articleName), "Football");
    }

    @AfterTest(description = "Delete Created Article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
