package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterTest;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckDeleteArticleFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Insert Content 3. Upload article image" +
            "4. Set winners.net in settings 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check Article Delete Functionality")
    public void checkArticleDeleteFunctionality() throws Exception {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        articleApiCalls.deleteArticle();
        mainPage.refreshPage();
        Assert.assertEquals(mainPage.getPageNotFoundMessage(), "404\n" +
                "Page not Found\n" +
                "GO TO HOMEPAGE");
    }

}
