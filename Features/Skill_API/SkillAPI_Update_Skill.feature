Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given Skill User want to execute PUT operation for "/Skills"
    When Skill User submit the PUT request for update  "<skillname>" for "<skillid>"
    Then Skill User should get 201 success Status code along with response body
    Then Skill User update request schema validation

    Examples: 
      | skillid | skillname       |
      |       3 | selenium update |
      |       9 | java update     |

  Scenario Outline: To verify Rest Service for update user endpoint as invalid user - Put Method
    Given Skill User want to execute PUT operation for "/Skills"
    When Skill User submit the PUT request for update  "<skillname>" for "<skillid>" as invalid user
    Then Skill User should get 401 Unauthorized Status code along with response body

    Examples: 
      | skillid | skillname       |
      |       3 | selenium update |

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given Skill User want to execute PUT operation for "/Skills"
    When Skill User submit the PUT request for update  "<skillname>" for "<skillid>"
    Then Skill User should get 404 not found Status code along with response body
    Then Skill User skill not found schema validation

    Examples: 
      | skillid | skillname       |
      |   12345 | selenium update |

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given Skill User want to execute PUT operation for "/Skills"
    When Skill User submit the PUT request for update  "<skillname>" for "<skillid>"
    Then Skill User should get 400 bad request Status code along with response body
    Then Skill User update existing skill name response schema validation

    Examples: 
      | skillid | skillname |
      |       3 | java      |
