package apitests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITests {

    static String loginToken;
    static Integer postID;

    @BeforeTest
    public void loginTest() throws JsonProcessingException {

        //Create login POJO object
        LoginPOJO login = new LoginPOJO();

        login.setUsernameOrEmail("angel131we");
        login.setPassword("Test123!");

        //convert pojo object to json via Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String convertedJson = objectMapper.writeValueAsString(login);

        baseURI = "http://training.skillo-bg.com:3100";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(convertedJson) //or .body(login) instead, so we do not have to use Jackson and we can remove row 24 and 25
                .when()
                .post("/users/login");

        response
                .then()
                .statusCode(201);

        //convert response body to a string
        String loginResponseBody = response.getBody().asString();
        //Object LoginResponseBody = new Object();

        loginToken = JsonPath.parse(loginResponseBody).read("$.token");
        System.out.println("Bearer Token is: " + loginToken);
    }

    @Test
    public void commentPost() {
        ActionsPOJO comment = new ActionsPOJO();
        comment.setContent("Angel's comment.");

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + loginToken)
                .body(comment)
                .when()
                .post("/posts/4628/comment")
                .then()
                .body("content", equalTo("Angel's comment."))
                .log()
                .all()
                .statusCode(201);

    }

    @Test(priority = 1)
    public void addPost() throws ClassCastException {
        ActionsPOJO addPost = new ActionsPOJO();
        addPost.setCaption("This is my automated (RestAssured) post.");
        addPost.setPostStatus("public");
        addPost.setCoverUrl("https://i.imgur.com/eWu9rUX.jpg");

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + loginToken)
                .body(addPost)
                .when()
                .post("/posts")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200 | 201)
                .assertThat().body("caption", equalTo(addPost.getCaption()));

        postID = validatableResponse.extract().path("id");

    }

    @Test(priority = 2)
    public void deletePost() {
        ActionsPOJO deletePost = new ActionsPOJO();

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + loginToken)
                .body(deletePost)
                .when()
                .delete("/posts/" + postID)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);
    }
}
