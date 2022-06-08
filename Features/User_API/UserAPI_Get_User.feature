Feature: To verify API automation with rest assured

  Scenario: To verify Rest Service - Get Method
    Given User try to execute GET operation for "/Users"
    When User submit the GET request
    Then User should get 200 success Status code
    Then User validated with response schema

  Scenario: To verify Rest Service for singleuser endpoint - Get Method
    Given User try to execute GET operation for "/Users/U18"
    When User submit the GET request
    Then User should get 200 success Status code

  Scenario: To verify Rest Service for singleuser endpoint - Get Method
    Given User want to execute GET operation for "/Users/11111"
    When User submit the GET request
    Then User should get 404 not found Status code
    Then User notfound validate response schema

  Scenario: To verify unauthorized Rest Service - Get Method
    Given User want to execute GET operation for "/Users/"
    When User submit the GET request for invalid user
    Then User should get 401 unauthorized Status code

  Scenario: To verify Rest Service for invalid endpoint - Get Method
    Given User want to execute GET invalid endpoint for "/Users1/"
    When User submit the GET request
    Then User should 404 not found Status code
    Then User invalidurl validate response schema
