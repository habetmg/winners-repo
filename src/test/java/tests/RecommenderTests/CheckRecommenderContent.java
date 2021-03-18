package tests.RecommenderTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckRecommenderContent extends BaseTest {

    @Test(description = "Check recommender pages content")
    public void checkBookmakerRecommenderTabsContent() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        Assert.assertEquals(recommenderPage.getCountriesList(), excel.readDataByColumn("src/test/resources/Countries.xls", "Sheet1", "Countries"));
        Assert.assertEquals(recommenderPage.getQuestion(), "Based on your IP, it seems like you are connecting from Armenia");
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Which type of entertainment are you interested in?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Esports", "Sports", "Casino")));
        recommenderPage.selectCheckbox("Esports");
        recommenderPage.selectCheckbox("Sports");
        Assert.assertEquals(recommenderPage.getOptionsFromOtherDropDown(), new ArrayList<>(Arrays.asList("Live Casino", "Virtual Sports",
                "Fantasy Sports","Poker","Skin Betting")));
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Esports that you would like to bet on?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Dota 2", "CS:GO", "LoL")));
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Sports that you would like to bet on?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Football", "Tennis", "Basketball")));
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Visa", "Mastercard", "Neteller")));
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Sports that you would like to bet on?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Football", "Tennis", "Basketball")));
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Esports that you would like to bet on?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Dota 2", "CS:GO", "LoL")));
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Which type of entertainment are you interested in?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Esports", "Sports", "Casino")));
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getCountriesList(), excel.readDataByColumn("src/test/resources/Countries.xls", "Sheet1", "Countries"));
        Assert.assertEquals(recommenderPage.getQuestion(), "Based on your IP, it seems like you are connecting from Armenia");
    }
}
