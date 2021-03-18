package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckArticleImageSize extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Test for checking that every article should have unique URL which should contain ID and slug together")
    public void checkArticleContentHeaders() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        float analysisPageImageSize = analysisPage.getImageSize();
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        float articlePageImageSize = articlePage.getImageSizeInArticlePage();
        mainPage.openUrl(articlePage.getImageUrl());
        float newPageImageSize = articlePage.getImageSizeOnNewPage();
        Log.warn(String.valueOf(analysisPageImageSize));
        Log.warn(String.valueOf(articlePageImageSize));
        Log.warn(String.valueOf(newPageImageSize));
        Assert.assertEquals(analysisPageImageSize,articlePageImageSize);
        Assert.assertEquals(articlePageImageSize, newPageImageSize);
    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
