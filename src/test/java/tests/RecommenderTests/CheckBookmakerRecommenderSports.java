package tests.RecommenderTests;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckBookmakerRecommenderSports extends BaseTest {

    @Test(description = "Check bookmaker recommender functionality 'Sports' way")
    public void checkBookmakerRecommenderInSportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");

        Assert.assertEquals(recommenderPage.getBookmakersCount(),27);
        recommenderPage.selectCountryInRecommenderPage("Belarus");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),22);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Sports");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),18);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Basketball");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),18);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Mastercard");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),16);
        recommenderPage.clickOnViewButton();

        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("Betway", "Bet365", "William Hill", "Unibet",
                "1XBET", "VBET", "Marathonbet", "GG.BET", "Rizk", "Mr.Play", "BETWINNER", "EnergyBet", "22BET", "MELbet", "Betmaster", "Parimatch")));
    }
}
