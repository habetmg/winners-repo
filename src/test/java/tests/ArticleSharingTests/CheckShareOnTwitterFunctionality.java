package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnTwitterFunctionality extends BaseTest {

    @BeforeMethod(description = "login in facebook")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test
    public void shareArticleOnTwitter() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("twitter");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("Compose new Tweet / Twitter");
        twitterPage.fillEmail();
        twitterPage.fillPassword();
        twitterPage.clickOnLogin();
    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
