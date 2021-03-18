package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleLocation extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Checking new created and published article should be first article in Analysis and Preconditions page")
    public void checkArticleLocation() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(analysisPage.getFirstArticleName(), ArticleApiCalls.articleName);
    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void AfterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
