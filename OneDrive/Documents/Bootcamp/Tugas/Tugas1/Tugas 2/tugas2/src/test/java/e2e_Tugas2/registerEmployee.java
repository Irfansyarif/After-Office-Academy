package e2e_Tugas2;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugas2.program.Model.EmployeeModel;
import com.tugas2.program.Model.response_model.addEmployeeResponse;
import com.tugas2.program.Model.response_model.loginEmployee;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class registerEmployee {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Suite E2E running...");

        String randomString = RandomStringUtils.randomAlphabetic(7);

        staticVar.employee = new EmployeeModel();
        staticVar.employee.setEmail("e2etest" + randomString + "@mail.com");
        staticVar.employee.setPassword(randomString);
        staticVar.employee.setFullName("Name" + randomString);
        staticVar.employee.setDepartment("Technology");
        staticVar.employee.setTitle("QA");

    }

    @Test(retryAnalyzer = retrySample.class)
    public void addEmployee() throws JsonProcessingException {
        System.out.println("addEmployee starting....");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(staticVar.employee);

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post(staticVar.BASE_URL + "/employee/add");

        System.out.println(res.asPrettyString());
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("add_employee_schema.json"));

        List<addEmployeeResponse> addEmployeeResponse = objectMapper.readValue(res.body().asString(),new TypeReference<List<addEmployeeResponse>>() {
                });

        assert addEmployeeResponse.size() > 0 : "Data is empty";
        assert addEmployeeResponse.get(0).getEmail().equals(staticVar.employee.getEmail()) : "email not expected";
        assert addEmployeeResponse.get(0).getPasswordHash() != null : "password hash is null";
    }

    @Test(dependsOnMethods = "addEmployee")
    public void loginEmployee() throws JsonProcessingException {
        // This test must running after add employee test
        System.out.println("loginEmployee starting....");

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(staticVar.employee);

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post(staticVar.BASE_URL + "/employee/login");

         res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("login.json"));

        List<loginEmployee> loginEmployeeResponse = objectMapper.readValue(res.body().asString(),
                new TypeReference<List<loginEmployee>>() {
                });

        assert loginEmployeeResponse.size() > 0 : "Data is empty";
        assert loginEmployeeResponse.get(0).getToken() != null : "token is null";

        // staticVar.token = res.jsonPath().getString("[0].token");
        // assert res.getStatusCode() == 200 : "Status code login employee must be 200";
        // assert staticVar.token != null : "Token is null";
    }

//     @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
//     public void searchEmployee() {
//         // This test must running after add employee test
//         System.out.println("searchEmployee starting....");

//         Response res = RestAssured
//                 .given()
//                 .contentType("application/json")
//                 .log()
//                 .all()
//                 .when()
//                 .get(staticVar.BASE_URL + "/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/"
//                         + staticVar.fullName);

//         System.out.println(res.asPrettyString());

//         assert res.getStatusCode() == 200 : "Status code search employee must be 200";
//         assert res.jsonPath().getString("[0].query").equals(staticVar.fullName) : "Query must be same as fullname";
//         assert res.jsonPath().getString("[0].result.full_name").contains(staticVar.fullName)
//                 : "Fullname not expected, must contains " + staticVar.fullName;
//     }

//     @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
//     public void getAllEmployee() {
//         // This test must running after add employee test
//         System.out.println("getAllEmployee starting....");

//         Response res = RestAssured
//                 .given()
//                 .contentType("application/json")
//                 .log()
//                 .all()
//                 .when()
//                 .get(staticVar.BASE_URL + "/employee/get_all");

//         System.out.println(res.asPrettyString());

//         assert res.getStatusCode() == 200 : "Status code get all employee must be 200";

//         int i = 0;
//         boolean dataIsFound = false;
//         while (true) {
//             String fullName = res.jsonPath().getString("[" + i + "].full_name");
//             if (fullName == null) {
//                 break;
//             }
//             if (fullName.equals(staticVar.fullName)) {
//                 dataIsFound = true;
//             }
//             i++;
//         }
//         assert dataIsFound : "Data not found in system";
//     }
}