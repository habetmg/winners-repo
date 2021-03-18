package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnRedditFunctionality extends BaseTest {
    @BeforeMethod(description = "login in facebook")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test
    public void shareArticleOnReddit() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("reddit");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("reddit.com: Log in");
        redditPage.fillUsername();
        redditPage.fillPassword();
        redditPage.clickOnLogin();
        redditPage.chooseCommunity();
    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}

