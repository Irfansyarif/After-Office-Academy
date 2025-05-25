Feature: Employee API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com/webhook"

  Scenario:
    When Send a http "POST" request to "/employee/add" with body:
      """
      {
        "email": "test123@test.com",
        "password": "password123",
        "full_name": "test name",
        "department": "IT",
        "title": "QA"
      }
      """
    Then The response status should equal to 200
    And The response schema should be match with schema "add_employee_schema.json"

  Scenario:
    When Send a http "POST" request to "/employee/login" with body:
      """
      {
        "email": "test123@test.com",
        "password": "password123"
      }
      """
    Then The response status shuold equal to 200
    And Save the token from the response to local storage

   Scenario:
    Given Make sure token in local storage not empty
    When Send a http "GET" request to "/employee/get" with body:
      """
      {}
      """
    Then The response status must be 200
    And Full name in the response must be "Ini nama yg udh diupdate ya"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"

    Scenario:
    Given Make sure token is not empty
    When Send http request "GET" to "/employee/get" with body:
      """
      {}
      """ 
    Then response status should be 200
    And Full name in the response must be "test name"
    And Department in the response must be "IT"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "DELETE" request to "/employee/delete" with body:
      """
      {}
      """
    Then The response status must be 200