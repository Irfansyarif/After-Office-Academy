package Cucumber.Definitions;
import java.sql.Array;
import java.util.Collection;
import java.util.List;

import com.tugas2.program.Model.response_model.addObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class employee_Definition {
    public static String baseUrl;
    public static Response response;
    public static String token;
    public static String id;

    @Given("The base url in this feature is {string}")
    public void set_base_url(String baseUrl) {
        employee_Definition.baseUrl = baseUrl;
    }


    @When("Send a http {string} request to {string} with body:")
    public void send_request_http(String method, String url, String body)throws Exception {
        // Add employee data
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + employee_Definition.token)
                .body(body)
                .when()
                .request(method, employee_Definition.baseUrl + url);
          
}
    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert response.statusCode() == statusCode : "Error, due to actual status code is " + response.statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        System.out.println("Response: " + response.asPrettyString());
        employee_Definition.token = response.jsonPath().getString("token");
        System.out.println(employee_Definition.token);
    }
    
    @And("Save id from response to local storage")
    public void save_id() {
        employee_Definition.id = response.jsonPath().getString("[0].id");
        System.out.println("ID: " + employee_Definition.id);
    }


    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert employee_Definition.token != null : "Token null";
    }

    @And("Name in the response must be {string}")
public void assert_full_name(String Name) throws Exception {
    
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = response.body().asString();
        System.out.println("Response body: " + responseBody);
        addObjectResponse addObjectResponseObj = objectMapper.readValue(responseBody, addObjectResponse.class);
        System.out.println("Deserialized name: " + addObjectResponseObj.getName());
        assert addObjectResponseObj.getName() != null : "name is null in response object";
        assert addObjectResponseObj.getName().equals(Name) : "name not expected";
   
}
    
    @And("Hard disk in the response must be {string}")
public void assert_department(String harddisk) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    addObjectResponse addObjectResponseObj = objectMapper.readValue(response.body().asString(), addObjectResponse.class);
    assert addObjectResponseObj.getData() != null : "data is null in response object";
    assert addObjectResponseObj.getData().getHardDiskSize().equals(harddisk) : "Hard Disk Size not expected";

}

    @When("Send update to http {string} request to {string} with body:")
    public void send_delete_request(String method, String url, String body) {
        System.out.println("url: " + employee_Definition.baseUrl + url + id);
        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + employee_Definition.token)
                .contentType("application/json")
                .when()
                .request(method, employee_Definition.baseUrl + url + id);
                System.out.println("Response body: " + response.body().asString());

       
    }
}