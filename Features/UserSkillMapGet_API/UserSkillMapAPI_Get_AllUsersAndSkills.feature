Feature: To verify UserSkillMap API automation with rest assured

  Scenario: List all users with all skill details Valid end point- Get Method
    Given User execute GET operation for "/UserSkillsMap" for getting list of details
    When User do GET request and got the list of details
    Then User get 200 success Status code with response body
    Then validate success response with schema validation

  Scenario: List all users with all skill details invalid end point - Get Method
    Given User try GET operation for "/UserSkillsMap1" invalid end point
    When User do GET request for listing all users all skill details using invalid end point
    Then User get 404 not found Status code with error message
    Then validate not found response with schema validation
