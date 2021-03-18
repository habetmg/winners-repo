package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckArticleViewOnNewsPageToDo extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void createArticle() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleTag("sports", "Football");
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check article view on news page")
    public void checkArticleViewOnNewsPage() {
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article tag"), "Football");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article subtext"), "selenium-subtext-test12249");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article info"), "Football " +
                mainPage.getCurrentDate() + " • Zeus Πορτοκάλι");
    }

    @AfterTest(description = "Delete created article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
