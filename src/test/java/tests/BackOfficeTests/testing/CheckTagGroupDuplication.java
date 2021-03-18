package tests.BackOfficeTests.testing;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckTagGroupDuplication extends BaseTest {

    @BeforeMethod(description = "Create Tag group")
    public void createTagGroup() throws Exception {
        backOfficeApiCalls.createTagGroup();
    }

    @Test(description = "Check no duplication for tag group functionality")
    public void checkNoDuplicationForTagGroupFunctionality() throws Exception {
        Assert.assertTrue(backOfficeApiCalls.createTagGroupWithSameName(409));
    }

    @AfterTest(description = "Delete created Tag group")
    public void deleteCreatedTagGroup() {
        backOfficeApiCalls.deleteTagGroupById();
    }
}
