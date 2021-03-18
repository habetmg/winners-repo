package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest.RequestHelpers;
import rest.RequestMethods;
import utils.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ArticleApiCalls {

    public final String RESOURCES_PATH = "src/test/resources/data/article/";
    public final String CREATE_ARTICLE = RESOURCES_PATH + "create-article.json";
    public final String CREATE_ARTICLE_WITH_INCORRECT_SLUG = RESOURCES_PATH + "create-article-with-incorrect-slug.json";
    public final String INSERT_LINK_CURRENT_WINDOW = RESOURCES_PATH + "content/article-content-insert-link-current-window.json";
    public final String INSERT_LINK_NEW_WINDOW = RESOURCES_PATH + "content/article-content-insert-link-new-window.json";
    public final String INSERT_TAG_CURRENT_WINDOW = RESOURCES_PATH + "content/article-content-insert-tag-current-window.json";
    public final String INSERT_TAG_NEW_WINDOW = RESOURCES_PATH + "content/article-content-insert-tag-new-window.json";
    public final String INSERT_BULLET_AND_NUMBERED_LIST = RESOURCES_PATH + "content/content-bullet-and-numbered-list.json";
    public final String INSERT_TWITCH = RESOURCES_PATH + "content/article-content-twitch.json";
    public final String INSERT_CONTENT_MEDIA = RESOURCES_PATH + "content/article-content-insert-media.json";
    public final String INSERT_SOURCE_CODE_VIDEO_USING_YOUTUBE_EMBED_CODE = RESOURCES_PATH + "content/article-content-source-code-video.json";
    public final String INSERT_ARTICLE_CONTENT_USING_REDDIT_EMBED_CODE = RESOURCES_PATH + "content/article-content-reddit-embed-code.json";
    public final String ARTICLE_CONTENT = RESOURCES_PATH + "content/article-content.json";
    public final String IMAGE_CROP = RESOURCES_PATH + "update-article-image.json";
    public final String ARTICLE_SETTINGS = RESOURCES_PATH + "article-settings.json";
    public final String ARTICLE_WITHOUT_WINNERS_NET_CHANNEL = RESOURCES_PATH + "article-without-winners-net-channel.json";
    public final String PUBLISH_ARTICLE = RESOURCES_PATH + "publish-article.json";
    public final String ARTICLE_CONTENT_HEADERS = RESOURCES_PATH + "content/article-content-headings.json";
    public final String TAGS = "src/test/resources/data/backoffice/tags/tags.json";
    public final String IMAGE = "src/test/resources/screenshot.png";
    public final String IMAGE_WITH_WRONG_FORMAT = "src/test/resources/tifformat.tiff";
    public final String IMAGE_WITH_WRONG_SIZE = "src/test/resources/highresolution.jpg";

    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.now();

    AppConfig envConfig = ConfigFactory.create(AppConfig.class);
    public static String articleName = "selenium-article-test" + System.currentTimeMillis();
    public static String articleNameWithBackSlash = "selenium/article-test" + System.currentTimeMillis();
    Map<String, String> requestHeaders = new HashMap<>();
    public static int articleId;
    public static String filePath;
    RequestMethods requestMethods = new RequestMethods();
    RequestHelpers requestHelpers = new RequestHelpers();

    /*constructor for getting Bearer token*/
    public ArticleApiCalls() throws IOException {
       requestHeaders.put("Authorization", Authentication.token);
    }

    /*create article*/
    public void createArticle() throws Exception {
        Log.warn(articleName);
        Log.warn("Request Headers" + requestHeaders);
        Response response = makePostRequest(envConfig.getApiNewsServiceUrl() + "/api/news",
                setArticleName(CREATE_ARTICLE, articleName), requestHeaders, 201);
        articleId = requestHelpers.getNumberValueByKeyPath(response, "newsId");
        Log.warn(String.valueOf(articleId));
    }

    public void createArticleWithNameWhichContainsBackSlash() throws Exception {
        Response response = makePostRequest(envConfig.getApiNewsServiceUrl() + "/api/news",
                setArticleNameWithBackSlash(CREATE_ARTICLE_WITH_INCORRECT_SLUG, articleNameWithBackSlash), requestHeaders, 201);
        articleId = requestHelpers.getNumberValueByKeyPath(response, "newsId");
        Log.warn(String.valueOf(articleId));
    }

    public JSONObject setArticleNameWithBackSlash(String path, String name) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        jsonObject.replace("title", "selenium-title-test", name);
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }

    public void addArticleContent(String content) throws Exception {
        String path = switch (content) {
            case "Insert Link Current Window" -> INSERT_LINK_CURRENT_WINDOW;
            case "Insert Link New Window" -> INSERT_LINK_NEW_WINDOW;
            case "Insert Tag Current Window" -> INSERT_TAG_CURRENT_WINDOW;
            case "Insert Tag New Window" -> INSERT_TAG_NEW_WINDOW;
            case "Article Content Headers" -> ARTICLE_CONTENT_HEADERS;
            case "Simple Content" -> ARTICLE_CONTENT;
            case "Insert Twitch" -> INSERT_TWITCH;
            case "Insert Bullet and Numbered List" -> INSERT_BULLET_AND_NUMBERED_LIST;
            case "Article Content Insert Media" -> INSERT_CONTENT_MEDIA;
            case "Insert Source Code Video Using Youtube Embed Code" -> INSERT_SOURCE_CODE_VIDEO_USING_YOUTUBE_EMBED_CODE;
            case "Insert Reddit Embed code" -> INSERT_ARTICLE_CONTENT_USING_REDDIT_EMBED_CODE;
            default -> throw new IllegalArgumentException("Content not found");
        };
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        Response response = makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId, jsonObject, requestHeaders, 200);
        Log.warn(response.getBody().asString());
    }

    /*crop image for created article and save image*/
    public void uploadArticleImage() throws Exception {
        File file = new File(IMAGE);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id", articleId).multiPart("type", "news")
                .header("Authorization", Authentication.token);
        Log.warn(Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "/images/file");
        requestHelpers.checkResponseStatusCode(response, 200);
        filePath = JsonPath.read(response.asString(), "filePath");
        RequestSpecification requestPut = RestAssured.given().contentType(ContentType.JSON).header("Authorization",
                Authentication.token);
        Log.warn(String.valueOf(articleId));
        Response response1 = requestPut.when().body(getImageFilePathBody(IMAGE_CROP, filePath))
                .put(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId);
        Log.warn(String.valueOf(response1));
    }

    public int uploadArticleImageWithWrongFormat() throws Exception {
        File file = new File(IMAGE_WITH_WRONG_FORMAT);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id", articleId).multiPart("type", "news")
                .header("Authorization", Authentication.token);
        Log.warn(Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "images/file");
        return response.getStatusCode();
    }

    public int uploadArticleImageWithWrongSize() throws Exception {
        File file = new File(IMAGE_WITH_WRONG_SIZE);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id", articleId).multiPart("type", "news")
                .header("Authorization", Authentication.token);
        Log.warn(Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "images/file");
        filePath = JsonPath.read(response.asString(), "filePath");
        RequestSpecification requestPut = RestAssured.given().contentType(ContentType.JSON).header("Authorization",
                Authentication.token);
        Log.warn(String.valueOf(articleId));
        Response response1 = requestPut.when().body(getImageFilePathBody(IMAGE_CROP, filePath))
                .put(envConfig.getApiNewsServiceUrl() + "/news/" + articleId);
        return response.getStatusCode();
    }

    /*setting Winners.net in Article channels*/
    public void setWinnersNetInSettings() throws Exception {
        makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId,
                getRequestBody(ARTICLE_SETTINGS), requestHeaders, 200);
    }

    public void unSetWinnersNetFromSettings() throws Exception {
        makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId,
                getRequestBody(ARTICLE_WITHOUT_WINNERS_NET_CHANNEL), requestHeaders, 200);
    }

    public void setArticleStatusPublishNow() throws Exception {
        makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/status/" + articleId,
                setScheduledAtAndNewsDates(PUBLISH_ARTICLE), requestHeaders, 200);
    }

    public void setArticleScheduleData() throws Exception {
        makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/status/" + articleId,
                setScheduledDateTwoMinutesAfterCurrentTime(PUBLISH_ARTICLE), requestHeaders, 200);
    }

    public void setArticleTag(String tagGroupName, String tagName) throws Exception {
        makePutRequest(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId,
                setArticleTagBody(tagGroupName, tagName, TAGS), requestHeaders, 200).getBody().asString();
    }

    public void deleteArticle() throws Exception {
        Log.warn(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId);
        requestMethods.makeDeleteRequest(envConfig.getApiNewsServiceUrl() + "/api/news/" + articleId, requestHeaders, 204);
    }

    /*setting image file path*/
    public JSONObject getImageFilePathBody(String path, String filePath) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONObject images = (JSONObject) jsonObject.get("images");
        JSONObject defaultFilePath = (JSONObject) images.get("default");
        defaultFilePath.put("filePath", filePath);
        return jsonObject;
    }

    public JSONObject setArticleName(String path, String name) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        jsonObject.replace("title", "selenium-title-test", name);
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }

    public JSONObject setScheduledAtAndNewsDates(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        jsonObject.replace("scheduledAt", "scheduledAt_date", dateTimeFormat.format(localDateTime.minusHours(4)));
        jsonObject.replace("newsDate", "newsDate", dateTimeFormat.format(localDateTime.minusHours(4)));
        Log.warn(dateTimeFormat.format(localDateTime.minusHours(4)));
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }

    public JSONObject setScheduledDateTwoMinutesAfterCurrentTime(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        jsonObject.replace("scheduledAt", "scheduledAt_date", dateTimeFormat.format(localDateTime.minusHours(4).plusMinutes(2)));
        jsonObject.replace("newsDate", "newsDate", dateTimeFormat.format(localDateTime.minusHours(4).plusMinutes(2)));
        Log.warn(dateTimeFormat.format(localDateTime.minusHours(4)));
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }


    public JSONObject getRequestBody(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        return jsonObject;
    }

    public JSONObject setArticleTagBody(String tagGroup, String tagName, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray tagsObject = (JSONArray) jsonObject.get("tags");
        JSONObject tagNameObject = (JSONObject) tagsObject.get(0);
        tagNameObject.replace("name", "tag_name", tagName);
        tagNameObject.replace("type", "tagGroupType", tagGroup);
        JSONObject resourceIdObject = (JSONObject) tagsObject.get(0);
        resourceIdObject.replace("resourceId", "resource_id", getResourceIdByTagNameAndTagGroup(tagGroup, tagName));
        JSONObject imagesObject = (JSONObject) tagNameObject.get("images");
        if (tagGroup != "match-predictions-and-analysis") {
            JSONObject defaultObject = (JSONObject) imagesObject.get("default");
            defaultObject.replace("filePath", "file_path", this.filePath);
        }
        return jsonObject;
    }


    public Object getResourceIdByTagNameAndTagGroup(String tagGroup, String tagName) {
        Response tagsResponse = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tags?tagGroup=" + tagGroup, requestHeaders, 200);
        String resourceId = (JsonPath.read(tagsResponse.asString(), "$.documents[?(@.name ==" + "'" + tagName + "'" + ")].id")).toString().
                replace("\"", "").replace("[", "").replace("]", "");
        return resourceId;
    }


    public Response makePostRequest(String requestUrl, JSONObject jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body(jsonObject.toString());
        Response response = requestSpecification.post(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }

    public Response makePutRequest(String requestUrl, JSONObject jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body((jsonObject.toString()));
        Response response = requestSpecification.when().put(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }

}
