import api.BookmakerApiCalls;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class TestTest extends BaseTest {

    @Test
    public void Test1() throws Exception {
       bookmakerApiCalls.createFiveBookmakers();
       bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
       bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmaker_Id_1);
    }

}


