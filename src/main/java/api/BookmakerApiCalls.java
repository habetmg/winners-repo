package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest.RequestHelpers;
import rest.RequestMethods;
import utils.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookmakerApiCalls {
    public final String BOOKMAKER_IMAGE = "src/test/resources/bookmakerlogo.png";
    public final String RESOURCES_PATH = "src/test/resources/data/bookmaker/";
    public final String CREATE_BOOKMAKER = RESOURCES_PATH + "create-bookmaker.json";
    public final String CREATE_BOOKMAKER_IMAGE = RESOURCES_PATH + "upload-bookmaker-logo.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB = RESOURCES_PATH + "create-bookmaker-profile-tab.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_ARMENIA = RESOURCES_PATH + "create-bookmaker-profile-tab-restricted-armenia.json";
    public final String CREATE_BOOKMAKER_BONUS_TAB = RESOURCES_PATH + "bonus/update-bookmaker-bonus-tab.json";
    public final String CREATE_MULTIPLE_BONUSES = RESOURCES_PATH + "bonus/create-multiple-bonuses.json";
    public final String BONUS_VALUE_SIX_DIGITS = RESOURCES_PATH + "bonus/bonus-value-six-digits.json";
    public final String BONUS_WITHOUT_COUNTRY = RESOURCES_PATH + "bonus/bonus-without-country.json";
    public final String CREATE_BOOKMAKER_REVIEW_TAB = RESOURCES_PATH + "create-bookmaker-review-tab.json";
    public final String CREATE_BOOKMAKER_SEO_TAB = RESOURCES_PATH + "create-bookmaker-seo-tab.json";
    public final String UPDATE_BOOKMAKER_SEO_TAB = RESOURCES_PATH + "update-bookmaker-seo-tab.json";
    public final String UPDATE_BOOKMAKER_PROFILE_TAB = RESOURCES_PATH + "update-bookmaker-profile-tab.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_WITH_MINESOTA_RESTRICTED_COUNTRY = RESOURCES_PATH + "";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_OVERVIEW_SPACE = RESOURCES_PATH + "create-bookmaker-profile-tab-space-ignoring.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_WITHOUT_COMPANY_TERMSURL_YEAR = RESOURCES_PATH + "create-bookmaker-profile-tab-without-company-name-termsurl-year.json";

    public final String BOOKMAKER_PUBLISHING_TAB = RESOURCES_PATH + "update-bookmaker-publishing-tab.json";
    public final String UPDATE_BOOKMAKER_REVIEW_TAB = RESOURCES_PATH + "update-bookmaker-review-tab.json";
    public final String ADD_BOOKMAKER_REVIEW_WITH_TWENTY_TWO_ESPORTS_TAGS = RESOURCES_PATH + "reviews/bookmaker-with-twenty-esport-tags.json";

    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_1 = RESOURCES_PATH + "reviews/review-tab-for-first-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_2 = RESOURCES_PATH + "reviews/review-tab-for-second-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_3 = RESOURCES_PATH + "reviews/review-tab-for-third-bookmaker-name.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_4 = RESOURCES_PATH + "reviews/review-tab-for-fourth-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_5 = RESOURCES_PATH + "reviews/review-tab-for-fifth-bookmaker.json";

    public final String SCORE_FOR_FIRST_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-first-bookmaker-case-score-is-three.json";
    public final String SCORE_FOR_SECOND_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-second-bookmaker-case-score-is-six.json";
    public final String SCORE_FOR_THIRD_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-third-bookmaker-case-score-is-eight.json";
//    public final String SCORE_FOR_FOURTH_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-first-bookmaker-case-score-is-ten.json";

    public static String bookmakerName = "selenium-bookmaker-test" + System.currentTimeMillis();
    public static String bookmaker_Name_1 = "selenium-bookmaker-1" + System.currentTimeMillis();
    public static String bookmaker_Name_2 = "selenium-bookmaker-2" + System.currentTimeMillis();
    public static String bookmaker_Name_3 = "selenium-bookmaker-3" + System.currentTimeMillis();
    public static String bookmaker_Name_4 = "selenium-bookmaker-4" + System.currentTimeMillis();
    public static String bookmaker_Name_5 = "selenium-bookmaker-5" + System.currentTimeMillis();

    public static String bookmakerId;
    public static String bookmaker_Id_1;
    public static String bookmaker_Id_2;
    public static String bookmaker_Id_3;
    public static String bookmaker_Id_4;
    public static String bookmaker_Id_5;


    public String filePath;
    Map<String, String> requestHeaders = new HashMap<>();
    RequestMethods requestMethods = new RequestMethods();
    RequestHelpers requestHelpers = new RequestHelpers();
    AppConfig envConfig = ConfigFactory.create(AppConfig.class);

    public BookmakerApiCalls() throws IOException {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public void createBookmaker() throws Exception {
        Log.warn(bookmakerName);
        Response response = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers",
                setBookmakerName(CREATE_BOOKMAKER, bookmakerName), requestHeaders, 200);
        bookmakerId = (JsonPath.read(response.asString(), "$.document.id"));
        Log.warn(String.valueOf(bookmakerId));
    }

    public JSONObject setBookmakerName(String path, String bookmakerName) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        jsonObject.replace("name", "bookmaker_name", bookmakerName);
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }


    public void uploadBookmakerLogo(String bookmakerId) throws Exception {
        File file = new File(BOOKMAKER_IMAGE);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id", bookmakerId).multiPart("type", "bookmakers")
                .header("Authorization", Authentication.token);
        Log.warn("Bearer Token" + Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "/images/file");
        Log.warn("Crop Call" + response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, 200);
        filePath = JsonPath.read(response.asString(), "filePath");
        RequestSpecification requestPut = RestAssured.given().contentType(ContentType.JSON).header("Authorization",
                Authentication.token);
        Log.warn(String.valueOf(bookmakerId));
        Response response1 = requestPut.when().body(getBookmakerImageFilePathBody(CREATE_BOOKMAKER_IMAGE, filePath))
                .put(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId);
        Log.warn(response1.getBody().asString());
    }

    //  common method
    public JSONObject getBookmakerImageFilePathBody(String path, String filePath) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONObject images = (JSONObject) jsonObject.get("images");
        images.put("logo", filePath);
        return jsonObject;
    }

    public void addBookmakerProfile(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                getRequestBody(CREATE_BOOKMAKER_PROFILE_TAB), requestHeaders, 200);
    }
    public void addBookmakerProfileWithRestrictedArmenia(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                getRequestBody(CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_ARMENIA), requestHeaders, 200);
    }

    public void addBookmakerProfileWithRestrictedState(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                getRequestBody(CREATE_BOOKMAKER_PROFILE_TAB_WITH_MINESOTA_RESTRICTED_COUNTRY), requestHeaders, 200);
    }

    public String addBookmakerProfileWithoutYearCompanyTermsUrl(String bookmakerId) throws Exception {
        return makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                getRequestBody(CREATE_BOOKMAKER_PROFILE_TAB_WITHOUT_COMPANY_TERMSURL_YEAR), requestHeaders, 200).getBody().asString();
    }

    public String addBookmakerProfileWithOverviewSpace(String bookmakerId) throws Exception {
        return makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                getRequestBody(CREATE_BOOKMAKER_PROFILE_TAB_OVERVIEW_SPACE), requestHeaders, 200).getBody().asString();
    }

    public void updateBookmakerProfile(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + bookmakerId,
                getRequestBody(UPDATE_BOOKMAKER_PROFILE_TAB), requestHeaders, 200);
    }

    public void addBookmakerBonus(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(CREATE_BOOKMAKER_BONUS_TAB), requestHeaders, 200);
    }

    public void addMultipleBonuses(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(CREATE_MULTIPLE_BONUSES), requestHeaders, 200);
    }

    public void addBookmakerBonusWithoutCountry(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(BONUS_WITHOUT_COUNTRY), requestHeaders, 200);
    }


    public String addIncorrectBookmakerBonusValue(String bookmakerId) throws Exception {
        return makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(BONUS_VALUE_SIX_DIGITS), requestHeaders, 200).getBody().asString();
    }

    public void addBookmakerReviews(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(CREATE_BOOKMAKER_REVIEW_TAB), requestHeaders, 200);
    }

    public void addBookmakerWithTwentyTwoEsportTags(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(ADD_BOOKMAKER_REVIEW_WITH_TWENTY_TWO_ESPORTS_TAGS), requestHeaders, 200);
    }

    public void addReviewsForSorting(String bookmakerId, String bookmakerName) throws Exception {
        String reviewBody = null;
        if (bookmakerName.equals(bookmaker_Name_1)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_1;
        }
        if (bookmakerName.equals(bookmaker_Name_2)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_2;
        }
        if (bookmakerName.equals(bookmaker_Name_3)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_3;
        }
        if (bookmakerName.equals(bookmaker_Name_4)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_4;
        }
        if (bookmakerName.equals(bookmaker_Name_5)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_5;
        }
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(reviewBody), requestHeaders, 200);
    }

    public void addReviewsForRatingColorBoundaryValues(String bookmakerId, String bookmakerName) throws Exception {
        String reviewBody = null;
        if (bookmakerName.equals(bookmaker_Name_1)) {
            reviewBody = SCORE_FOR_FIRST_BOOKMAKER;
        }
        if (bookmakerName.equals(bookmaker_Name_2)) {
            reviewBody = SCORE_FOR_SECOND_BOOKMAKER;
        }
        if (bookmakerName.equals(bookmaker_Name_3)) {
            reviewBody = SCORE_FOR_THIRD_BOOKMAKER;
        }
//       bug if (bookmakerName.equals(bookmaker_Name_4)) {
//            reviewBody = SCORE_FOR_FIFTH_BOOKMAKER;
//        }
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(reviewBody), requestHeaders, 200);
    }


    public void updateBookmakerReviewTab(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                getRequestBody(UPDATE_BOOKMAKER_REVIEW_TAB), requestHeaders, 200);
    }

    public void addBookmakerSEO(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/seo",
                getRequestBody(CREATE_BOOKMAKER_SEO_TAB), requestHeaders, 200);
    }

    public void updateBookmakerSEO(String bookmakerId) throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/seo",
                getRequestBody(UPDATE_BOOKMAKER_SEO_TAB), requestHeaders, 200);
    }

    public String publishBookmaker(String bookmakerId) throws Exception {
        return makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/status",
                getRequestBody(BOOKMAKER_PUBLISHING_TAB), requestHeaders, 200).getBody().asString();
    }

    public String publishBookmakerWithIncorrectRating(String bookmakerId) throws Exception {
        Thread.sleep(5000);
        return makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/status",
                getRequestBody(BOOKMAKER_PUBLISHING_TAB), requestHeaders, 409).getBody().asString();
    }

    public void deleteBookmakerById(String bookmakerId) throws Exception {
        requestMethods.makeDeleteRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId, requestHeaders, 204);
    }

    public void createTwoBookmakers() throws Exception {
        Response response1 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_1), requestHeaders, 200);
        Log.warn("first bookmaker name " + bookmaker_Name_1);
        bookmaker_Id_1 = (JsonPath.read(response1.asString(), "$.document.id"));
        Log.warn("first bookmaker " + bookmaker_Id_1);
        Response response2 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_2), requestHeaders, 200);
        Log.warn("first bookmaker name " + bookmaker_Name_2);
        bookmaker_Id_2 = (JsonPath.read(response2.asString(), "$.document.id"));
        Log.warn("second bookmaker " + bookmaker_Id_2);
    }

    public void createFiveBookmakers() throws Exception {
        Response response1 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_1), requestHeaders, 200);
        bookmaker_Id_1 = (JsonPath.read(response1.asString(), "$.document.id"));
        Log.warn("first bookmaker id " + bookmaker_Id_1);
        Log.warn("first bookmaker name " + BookmakerApiCalls.bookmaker_Name_1);

        Response response2 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_2), requestHeaders, 200);
        bookmaker_Id_2 = (JsonPath.read(response2.asString(), "$.document.id"));
        Log.warn("second bookmaker id " + bookmaker_Id_2);
        Log.warn("second bookmaker name " + BookmakerApiCalls.bookmaker_Name_2);

        Response response3 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_3), requestHeaders, 200);
        bookmaker_Id_3 = (JsonPath.read(response3.asString(), "$.document.id"));
        Log.warn("third bookmaker id " + bookmaker_Id_3);
        Log.warn("third bookmaker name " + BookmakerApiCalls.bookmaker_Name_3);

        Response response4 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_4), requestHeaders, 200);
        bookmaker_Id_4 = (JsonPath.read(response4.asString(), "$.document.id"));
        Log.warn("fourth bookmaker id " + bookmaker_Id_4);
        Log.warn("fourth bookmaker name " + BookmakerApiCalls.bookmaker_Name_4);

        Response response5 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_5), requestHeaders, 200);
        bookmaker_Id_5 = (JsonPath.read(response5.asString(), "$.document.id"));
        Log.warn("fifth bookmaker id " + bookmaker_Id_5);
        Log.warn("fifth bookmaker name " + BookmakerApiCalls.bookmaker_Name_5);
    }

    public void createThreeBookmakers() throws Exception {
        Response response1 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_1), requestHeaders, 200);
        bookmaker_Id_1 = (JsonPath.read(response1.asString(), "$.document.id"));
        Log.warn("first bookmaker id " + bookmaker_Id_1);
        Log.warn("first bookmaker name " + BookmakerApiCalls.bookmaker_Name_1);

        Response response2 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_2), requestHeaders, 200);
        bookmaker_Id_2 = (JsonPath.read(response2.asString(), "$.document.id"));
        Log.warn("second bookmaker id " + bookmaker_Id_2);
        Log.warn("second bookmaker name " + BookmakerApiCalls.bookmaker_Name_2);

        Response response3 = makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                setBookmakerName(CREATE_BOOKMAKER, bookmaker_Name_3), requestHeaders, 200);
        bookmaker_Id_3 = (JsonPath.read(response3.asString(), "$.document.id"));
        Log.warn("third bookmaker id " + bookmaker_Id_3);
        Log.warn("third bookmaker name " + BookmakerApiCalls.bookmaker_Name_3);
    }

    public int getBookmakersGiven22Parameters() {
        Response response = requestMethods.makeGetRequest("https://api-bookmakers.priotix.xyz/bookmakers?filter[0][type]=countries&filter[0][values][0][id]=5f0d5576daa7100010adc764&filter[1][type]=" +
                "esports&filter[1][values][0][id]=602a252555bb5f00109c2ba2&filter[1][values][1][id]=602a252555bb5f00109c2ba3&filter[1][values][2][id]=602a252555bb5f00109c2ba4&filter[1][values][3][id]=" +
                "602a252555bb5f00109c2ba5&filter[1][values][4][id]=602a252555bb5f00109c2ba6&filter[1][values][5][id]=602a252555bb5f00109c2ba7&filter[1][values][6][id]=602a252555bb5f00109c2ba8&filter[1][values][7][id]=602a252555bb5f00109c2baa&filter[1][values][8][id]=" +
                "602a252555bb5f00109c2ba9&filter[1][values][9][id]=602a252555bb5f00109c2bab&filter[1][values][10][id]=602a252555bb5f00109c2bac&filter[1][values][11][id]=602a252555bb5f00109c2bad&filter[1][values][12][id]=602a252555bb5f00109c2bae&filter[1][values][13][id]=602a252555bb5f00109c2baf&filter[1][values][14][id]=" +
                "602a252555bb5f00109c2bb0&filter[1][values][15][id]=602a252555bb5f00109c2bb1&filter[1][values][16][id]=602a252555bb5f00109c2bb2&filter[1][values][17][id]=602a252555bb5f00109c2bb3&filter[1][values][18][id]=602a252555bb5f00109c2bb4&filter[1][values][19][id]=" +
                "602a252555bb5f00109c2bb5&filter[1][values][20][id]=602a252555bb5f00109c2bb6&filter[1][values][21][id]=5ebd631a224ed80010a0fafe&sort=-reviews.overall.rating&limit=60", requestHeaders, 409);
        return response.getStatusCode();
    }

    public JSONObject getRequestBody(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        return jsonObject;
    }

    public Response makePostRequest(String requestUrl, JSONObject jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body(jsonObject.toString());
        Response response = requestSpecification.post(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }

    public Response makePutRequest(String requestUrl, JSONObject jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body((jsonObject));
        Response response = requestSpecification.when().put(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }

}
