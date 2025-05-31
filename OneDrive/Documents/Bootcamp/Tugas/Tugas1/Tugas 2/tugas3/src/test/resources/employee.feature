Feature: Employee API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com/webhook"
  # Scenario:
  #   When Send a http "POST" request to "/api/register" with body:
  #     """
  #     {
  #     "email": "fan2@gmail.com",
  #     "full_name": "irfan anwar",
  #     "password": "@123admin",
  #     "department": "Executive",
  #     "phone_number": "0888888123123"
  #     }
  #     """
  #   Then The response status must be 200
  #   And The response schema should be match with schema "add_employee_schema.json"

  Scenario:
    When Send a http "POST" request to "/api/login" with body:
      """
      {
        "email": "fan2@gmail.com",
        "password": "@123admin"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "POST" request to "/api/objects" with body:
      """
      {
      "name": "Apple MacBook Pro 1231",
        "data": {
            "year": 2020,
            "price": 1500.99,
            "cpu_model": "Apple M3",
            "hard_disk_size": "1 TB",
            "capacity": "2 cpu",
            "screen_size": "14 Inch",
            "color": "white"
        }}
      """
    Then The response status must be 200
    And The response schema should be match with schema "add_object_schema.json"
    And Save id from response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send update to http "PUT" request to "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" with body:
      """
      {
      "name": "Apple MacBook Pro 1500",
        "data": {
            "year": 2017,
            "price": 1500.99,
            "cpu_model": "Apple M4",
            "hard_disk_size": "10 TB",
            "capacity": "2 cpu",
            "screen_size": "14 Inch",
            "color": "white"
        }
      }
      """
    Then The response status must be 200
    And Name in the response must be "Apple MacBook Pro 1500"
    And Hard disk in the response must be "10 TB"

  Scenario:
    Given Make sure token in local storage not empty
    When Send update to http "GET" request to "/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" with body:
      """
      {}
      
      """
    Then The response status must be 200
    And Name in the response must be "Apple MacBook Pro 1500"
    And Hard disk in the response must be "10 TB"

  Scenario:
    Given Make sure token in local storage not empty
    When Send update to http "DELETE" request to "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" with body:
      """
      {}
      """
    Then The response status must be 200
