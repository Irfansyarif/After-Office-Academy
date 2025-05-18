package restassured;

import org.testng.annotations.BeforeSuite;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class Get {
    public String token;

    public final String EXPECT_EMAIL = "test2@test.com";
    public final String EXPECT_NAME = "fifi noela";

    @BeforeSuite

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

     @Test
    public void getDepartment() {
        // hit endpoint with token with get method
        Response res1 = RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + token))
                .log()
                .all()
                .when()
                .get("https://whitesmokehouse.com/webhook/api/department");

         System.out.println(res1.asPrettyString());
         String department1 = res1.jsonPath().getString("deparment[1]");
         
        }
}
