Feature: To verify UserSkillMap API automation with rest assured

  Scenario: List user with the skill details Valid end point- Get Method
    Given User click GET operation for "/UserSkillsMap/U69" for getting list of details
    When User do GET request to List user with the skill details
    Then User see 200 success Status code with response body
    Then validate success details response with schema validation

  Scenario: List user with the skill details invalid end point - Get Method
    Given User click GET operation for "/UserSkillsMap/H65" invalid end point
    When User do GET request to List user with the skill details using invalid userid
    Then User get 404 not found Status code with error message in response
    Then validate not found user response with schema validation
