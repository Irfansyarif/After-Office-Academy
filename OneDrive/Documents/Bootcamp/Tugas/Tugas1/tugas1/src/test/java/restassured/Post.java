package restassured;

import org.testng.annotations.BeforeSuite;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class Post {
    public String token;

    @Test

    public void login () {
       String body =   "{\r\n" + //
                "    \"email\":\"albertsimanjuntak12@gmail.com\",\r\n" + //
                "    \"password\": \"@dmin123\"\r\n" + //
                "}";

       Response res = RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(body)
        .when()
        .post("https://whitesmokehouse.com/webhook/api/login")
        .then()
        .extract()
        .response();
        
    
    System.out.println(res.asPrettyString());

    token = res.jsonPath().getString("token");
    Assert.assertNotNull(token);

    
    }
}
