package e2e_Tugas2;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class registerEmployee {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Suite E2E running...");

        String randomString = RandomStringUtils.randomAlphabetic(10);

        staticVar.email = "e2etest" + randomString + "@mail.com";
        staticVar.password = "password";
        staticVar.fullName = "E2E Test Full Name " + randomString;
        staticVar.department = "Technology";
        staticVar.title = "QA";
    }

    @Test
    public void addEmployee() {
        System.out.println("addEmployee starting....");
        String body = "{\r\n" + //
                "    \"email\": \"" + staticVar.email + "\",\r\n" + //
                "    \"password\": \"" + staticVar.password + "\",\r\n" + //
                "    \"full_name\": \"" + staticVar.fullName + "\",\r\n" + //
                "    \"department\": \"" + staticVar.department + "\",\r\n" + //
                "    \"title\": \"" + staticVar.title + "\"\r\n" + //
                "}";

        // Add employee data
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post(staticVar.BASE_URL + "/employee/add");

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code add employee must be 200";
        assert res.jsonPath().get("[0].email").toString().equals(staticVar.email);
        assert res.jsonPath().get("[0].full_name").toString().equals(staticVar.fullName);
        assert res.jsonPath().get("[0].department").toString().equals(staticVar.department);
        assert res.jsonPath().get("[0].title").toString().equals(staticVar.title);
    }

    @Test(dependsOnMethods = "addEmployee")
    public void loginEmployee() {
        // This test must running after add employee test
        System.out.println("loginEmployee starting....");

        String body = "{\r\n" + //
                "    \"email\": \"" + staticVar.email + "\",\r\n" + //
                "    \"password\": \"" + staticVar.password + "\"\r\n" + //
                "}";

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post(staticVar.BASE_URL + "/employee/login");

        System.out.println(res.asPrettyString());

        staticVar.token = res.jsonPath().getString("[0].token");
        assert res.getStatusCode() == 200 : "Status code login employee must be 200";
        assert staticVar.token != null : "Token is null";
    }

    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void searchEmployee() {
        // This test must running after add employee test
        System.out.println("searchEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(staticVar.BASE_URL + "/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/"
                        + staticVar.fullName);

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code search employee must be 200";
        assert res.jsonPath().getString("[0].query").equals(staticVar.fullName) : "Query must be same as fullname";
        assert res.jsonPath().getString("[0].result.full_name").contains(staticVar.fullName)
                : "Fullname not expected, must contains " + staticVar.fullName;
    }

    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void getAllEmployee() {
        // This test must running after add employee test
        System.out.println("getAllEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(staticVar.BASE_URL + "/employee/get_all");

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code get all employee must be 200";

        int i = 0;
        boolean dataIsFound = false;
        while (true) {
            String fullName = res.jsonPath().getString("[" + i + "].full_name");
            if (fullName == null) {
                break;
            }
            if (fullName.equals(staticVar.fullName)) {
                dataIsFound = true;
            }
            i++;
        }
        assert dataIsFound : "Data not found in system";
    }
}