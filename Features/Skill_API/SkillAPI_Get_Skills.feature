Feature: To verify API automation with rest assured

  Scenario: To verify Rest Service - Get Method
    Given Skill User want to execute GET operation for "/Skills"
    When Skill User submit the GET request
    Then Skill User should get 200 success Status code
    Then Skill User success validate response schema

  Scenario: To verify Rest Service for singleuser endpoint - Get Method
    Given Skill User want to execute GET operation for "/Skills/2760"
    When Skill User submit the GET request
    Then Skill User should get 200 success Status code
    Then Skill User Single skill success validate response schema

  Scenario: To verify Rest Service for singleuser endpoint - Get Method
    Given Skill User want to execute GET operation for "/Skills/2760AA"
    When Skill User submit the GET request
    Then Skill User should get 400 invalid Status code
    Then Skill User invalid skillid validate response schema

  Scenario: To verify Rest Service for singleuser endpoint - Get Method
    Given Skill User want to execute GET operation for "/Skills/11111"
    When Skill User submit the GET request
    Then Skill User should get 404 not found Status code
    Then Skill User notfound validate response schema

  Scenario: To verify unauthorized Rest Service - Get Method
    Given Skill User want to execute GET operation for "/Skills/"
    When Skill User submit the GET request for invalid user
    Then Skill User should get 401 unauthorized Status code

  Scenario: To verify Rest Service for invalid endpoint - Get Method
    Given Skill User want to execute GET invalid endpoint for "/Skills1/"
    When Skill User submit the GET request
    Then Skill User should 404 not found Status code
    Then Skill User invalidurl validate response schema
