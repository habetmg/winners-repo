package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterTest;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckUnsetWinnersNetChannelFromSettings extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void publishArticle() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check unset winners.net channel functionality")
    public void checkUnsetWinnersNetChannelFromSettings() throws Exception {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        articleApiCalls.unSetWinnersNetFromSettings();
        mainPage.refreshPage();
        Assert.assertEquals(mainPage.getPageNotFoundMessage(), "404\n" +
                "Page not Found\n" +
                "GO TO HOMEPAGE");
    }

    @AfterTest(description = "Delete created Article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
