package tests.RecommenderTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckBookmakerRecommenderAll extends BaseTest {

    @Test(description = "Check bookmaker recommender functionality 'Casino', 'Esports', 'Sports' way")
    public void checkBookmakerRecommenderInAllWays() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");

        Assert.assertEquals(recommenderPage.getBookmakersCount(), 27);
        recommenderPage.selectCountryInRecommenderPage("Bahamas");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 24);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Esports");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 24);
        recommenderPage.selectCheckbox("Sports");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 19);
        recommenderPage.selectCheckbox("Casino");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Dota 2");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("CS:GO");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("LoL");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Football");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("Tennis");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("Basketball");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);

        recommenderPage.selectCheckbox("VISA");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("Neteller");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.selectCheckbox("MasterCard");
        Assert.assertEquals(recommenderPage.getBookmakersCount(), 18);
        recommenderPage.clickOnViewButton();
        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("Betway", "Bet365", "William Hill",
                "Unibet", "1XBET", "VBET", "Unikrn", "Marathonbet", "GG.BET", "Rizk", "Bettilt",
                "EnergyBet", "22BET", "MELbet", "Thunderpick", "Betmaster", "Parimatch", "OneHash")));
    }
}
