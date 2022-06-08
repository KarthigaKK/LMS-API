Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given User want to execute PUT operation for UserSkillAPI "/UserSkills"
    When User submit the PUT request as <monthofexp> for "<userskillid>", "<userid>", <skillid>
    Then User get 201 success Status code along with response body

    Examples: 
      | monthofexp | userskillid | userid | skillid |
      |         10 | US185       | U02    |       2 |
      |         12 | US1566      | U03    |       3 |

  Scenario Outline: To verify Rest Service for update user endpoint as invalid user - Put Method
    Given User want to execute PUT operation for UserSkillAPI "/UserSkills"
    When User submit the PUT request as  <monthofexp> for "<userskillid>", "<userid>", <skillid> invalid user
    Then User get 401 Unauthorized Status code

    Examples: 
      | monthofexp | userskillid | userid | skillid |
      |         10 | US185       | U02    |       2 |
      |         12 | US1566      | U03    |       3 |

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given User want to execute PUT operation for UserSkillAPI "/UserSkills1"
    When User submit the PUT request as <monthofexp> for "<userskillid>", "<userid>", <skillid>
    Then User get 404 not found Status code along with response body

    Examples: 
      | monthofexp | userskillid | userid | skillid |
      |         10 | US185       | U02    |       2 |
      |         12 | US1566      | U03    |       3 |
