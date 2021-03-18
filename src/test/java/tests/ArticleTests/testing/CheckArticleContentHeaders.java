package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;


public class CheckArticleContentHeaders extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Test for checking that every article should have unique URL which should contain ID and slug together")
    public void checkArticleContentHeaders() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        HashMap<String, String> contentHeaders = articlePage.getHeading();
        Assert.assertEquals("Heading2", contentHeaders.get("h2"));
        Assert.assertEquals("Heading3", contentHeaders.get("h3"));
        Assert.assertEquals("Heading4", contentHeaders.get("h4"));
        Assert.assertEquals("Heading5", contentHeaders.get("h5"));
        Assert.assertEquals("Heading6", contentHeaders.get("h6"));
    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
