Feature: To verify API automation with rest assured

  Scenario: To verify UserSkill API Rest Service - Get Method
    Given User execute GET operation for "/UserSkills"
    When User do the GET request
    Then User receive 200 success Status code
    Then validate schema for get all UserSkill request 

  Scenario: To verify UserSkill API Rest Service for single UserSkill - Get Method
    Given User execute GET operation for "/UserSkills/US3222"
    When User do the GET request
    Then User receive 200 success Status code
    Then validate schema for single UserSkill request 

  Scenario: To verify UserSkill API unauthorized Rest Service - Get Method
    Given User execute GET operation for "/UserSkills/"
    When User submit the GET request as invalid user
    Then User get user skill API 401 unauthorized Status code

  Scenario: To verify UserSkill API Rest Service  invalid end point - Get Method
    Given User want to execute GET invalid url "/UserSkill/"
    When User do the GET request
    Then User get 404 not found Status code
