package tests.RecommenderTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckBookmakerRecommenderEsports extends BaseTest {

    @Test(description = "Check bookmaker recommender functionality 'Esports' way")
    public void checkBookmakerRecommenderInEsportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");

        Assert.assertEquals(recommenderPage.getBookmakersCount(),27);
        recommenderPage.selectCountryInRecommenderPage("Congo");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),24);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Esports");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),24);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("LoL");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),24);

        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Visa");
        Assert.assertEquals(recommenderPage.getBookmakersCount(),22);
        recommenderPage.clickOnViewButton();

        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("Betway", "1XBET", "VBET", "Unibet", "Winners.bet", "Bethard", "Marathonbet"
                ,"GG.BET", "Pixel.bet", "LOOT.BET", "10bet", "Rizk", "BETWINNER", "Bettilt", "Luckbox", "EnergyBet", "EGB",
                "22BET", "MELbet", "Vulkanbet", "Betmaster", "Parimatch")));
    }
}
