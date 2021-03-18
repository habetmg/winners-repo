package tests.BackOfficeTests.testing;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCreateTagGroupFunctionality extends BaseTest {

    @Test(description = "Check Create tag group functionality")
    public void checkCreateTagGroupFunctionality() throws Exception {
        backOfficeApiCalls.createTagGroup();
        Assert.assertEquals(backOfficeApiCalls.getTagGroupById(), backOfficeApiCalls.tagGroupId);
    }

    @AfterTest(description = "Delete created Tag group")
    public void deleteCreatedTagGroup() {
        backOfficeApiCalls.deleteTagGroupById();
    }
}
