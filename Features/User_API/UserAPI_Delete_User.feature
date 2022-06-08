Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service - Delete Method
    Given User want to execute DELETE operation for "/Users"
    When User DELETE the "<userid>"
    Then User get 200 success Status code
    Then User deleted successfully and validated through json schema

    Examples: 
      | userid |
      | U3347  |
      | U3346  |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given User want to execute DELETE operation for "/Users"
    When User DELETE the "<userid>" for invalid user
    Then User get 401 unauthorized Status code

    Examples: 
      | userid |
      |   2793 |
      |   1567 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given User want to execute DELETE operation for "/Users"
    When User DELETE the "<userid>"
    Then User get 404 User not found Status code
    Then User not found are validated through json schema

    Examples: 
      | userid |
      |   3007 |
      |   2988 |

  Scenario Outline: To verify invalid user Rest Service - Delete Method
    Given User want to execute DELETE operation for "/Users"
    When User DELETE the "<userid>"
    Then User get 500 Internal server error as skill mapped to user
    Then User cant delete user mapped to skill are validated through json schema

    Examples: 
      | userid |
      | U1387  |
      | U451   |
