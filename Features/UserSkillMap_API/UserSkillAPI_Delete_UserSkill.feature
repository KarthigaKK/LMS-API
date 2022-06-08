Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service - Delete Method
    Given Skill User API want to execute DELETE operation for "/Skills"
    When Skill User API DELETE the skill "<skillid>"
    Then Skill User API get 200 success Status code

    Examples: 
      | skillid |
      |    2793 |
      |    1567 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given Skill User API want to execute DELETE operation for "/Skills"
    When Skill User API DELETE the skill "<skillid>" for invalid user
    Then Skill User API get 401 unauthorized Status code

    Examples: 
      | skillid |
      |    2793 |
      |    1567 |
