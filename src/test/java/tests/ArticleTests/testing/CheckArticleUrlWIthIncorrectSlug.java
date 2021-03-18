package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleUrlWIthIncorrectSlug extends BaseTest {
    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticleWithNameWhichContainsBackSlash();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check article Url with incorrect slug")
    public void checkArticleUrlWithIncorrectSlug() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleNameWithBackSlash);
        Assert.assertNotEquals(mainPage.getPageUrl(), "https://win:w1nPorta2@affiliates.priotix.xyz/analysis-and-predictions/" + ArticleApiCalls.articleId + "/" + ArticleApiCalls.articleNameWithBackSlash);
    }

    @AfterTest(description = "Delete created Article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}

