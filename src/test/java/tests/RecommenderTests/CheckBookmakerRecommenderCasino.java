package tests.RecommenderTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckBookmakerRecommenderCasino extends BaseTest {

    @Test(description = "Check bookmaker recommender functionality 'Casino' way")
    public void checkBookmakerRecommenderInSportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");

        Assert.assertEquals(recommenderPage.getBookmakersCount(), 27);
        recommenderPage.selectCountryInRecommenderPage("Croatia");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 29);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Casino");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 24);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Neteller");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 19);
        recommenderPage.clickOnViewButton();

        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("Betway", "William Hill", "VBET", "1XBET",
                "Unibet", "Bethard", "Unikrn", "Marathonbet", "GG.BET", "LOOT.BET", "18bet", "Rizk", "Mr.Play", "Bet90", "Bettilt", "EnergyBet", "Betiton",
                "Betmaster", "Parimatch")));
    }
}
