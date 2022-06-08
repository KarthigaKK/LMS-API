Feature: To verify UserSkillMap API automation with rest assured

  Scenario: List all users details by SKILL_ID Valid end point- Get Method
    Given User click GET operation for "/UsersSkillsMap/3" for getting list all user details by skill
    When User do GET request to List all user details by the skill id
    Then User get success Status code 200 with response body
    Then validate success all user details response with schema validation

  Scenario: List all users details by SKILL_ID invalid skill id - Get Method
    Given User click GET operation for "/UsersSkillsMap/90" for getting list all user details by invalid skill
    When User do GET request to List all user details by the invalid skill id
    Then User get not found Status code  404 with error message in response body
    Then validate not found skill response with schema validation
