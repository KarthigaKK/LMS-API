Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service - Delete Method
    Given Skill User want to execute DELETE operation for "/Skills"
    When Skill User DELETE the skill <skillid>
    Then Skill User get 200 success Status code
    Then Skill User deleted success reuqest schema validation

    Examples: 
      | skillid |
      |     146 |
      |    2234 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given Skill User want to execute DELETE operation for "/Skills"
    When Skill User DELETE the skill <skillid> for invalid skillid
    Then Skill User get 401 unauthorized Status code

    Examples: 
      | skillid |
      |    2793 |
      |    1567 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given Skill User want to execute DELETE operation for "/Skills"
    When Skill User DELETE the skill <skillid>
    Then Skill User get 404 skill not found Status code

    Examples: 
      | skillid |
      |    3007 |
      |    2988 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given Skill User want to execute DELETE operation for "/Skills"
    When Skill User DELETE the skill <skillid>
    Then Skill User get 500 Internal server error as skill mapped to user

    Examples: 
      | skillid |
      |    2013 |
      |    2483 |
