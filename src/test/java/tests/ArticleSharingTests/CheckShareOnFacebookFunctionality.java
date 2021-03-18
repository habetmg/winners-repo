package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnFacebookFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Set Article Content" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current W7indow");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test
    public void shareArticleOnFacebook() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("twitter");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("Facebook");
        facebookPage.fillEmail();
        facebookPage.fillPassword();
        facebookPage.clickOnLogin();
        facebookPage.clickOnPublishArticleButton();
        mainPage.handleWindowByTitle("Esports betting sites | Customize your need and find the best bookmaker");

        Thread.sleep(3000);
        facebookPage.clickOnProfile();
        facebookPage.clickOnViewProfile();

    }

    @AfterTest(description = "delete created article", alwaysRun = true)
    public void afterTest() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
