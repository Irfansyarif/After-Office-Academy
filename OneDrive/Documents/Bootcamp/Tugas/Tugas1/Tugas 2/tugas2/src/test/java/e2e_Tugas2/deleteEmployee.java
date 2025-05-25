package e2e_Tugas2;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class deleteEmployee {
    @Test(dependsOnGroups = "assertEmployeeRegister")
    public void DeleteEmployee() {
        // This test must running after login employee test
        System.out.println("deleteEmployee starting....");
        Response res = RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + staticVar.token))
                .log()
                .all()
                .when()
                .delete(staticVar.BASE_URL + "/employee/delete");

        assert res.getStatusCode() == 200 : "Status code add employee must be 200";
        assert res.jsonPath().getBoolean("[0].success") == true : "Delete not success";
    }

    @Test(dependsOnMethods = "DeleteEmployee")
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
        assert !res.jsonPath().getString("[0].result.full_name").contains(staticVar.fullName)
                : "Fullname not expected, must not contains " + staticVar.fullName;
    }

    @Test(dependsOnMethods = "DeleteEmployee")
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
        assert !dataIsFound : "Data found in system";
    }
}