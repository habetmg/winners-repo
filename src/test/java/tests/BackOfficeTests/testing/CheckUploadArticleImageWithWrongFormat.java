package tests.BackOfficeTests.testing;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckUploadArticleImageWithWrongFormat extends BaseTest {

    @Test(description = "Check upload article image with wrong format")
    public void checkUploadArticleImageWithWrongFormat() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        Assert.assertEquals(articleApiCalls.uploadArticleImageWithWrongFormat(), 400);
    }

    @AfterTest(description = "Delete created article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
