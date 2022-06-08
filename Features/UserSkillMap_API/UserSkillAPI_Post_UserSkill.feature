Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service - Post Method for UserSkillMap API
    Given User perform POST operation in UserSkillMap API "/UserSkills"
    When User pass the UserSkill data from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User get the status code as 201 success code
    Then User Post UserSkill API success schema validate

    Examples: 
      | SheetName | RowNumber |
      | UserSkill |         0 |
      | UserSkill |         1 |
      | UserSkill |         2 |
      | UserSkill |         3 |

  Scenario Outline: To verify unauthorized Rest Service - Get Method for UserSkillMap API
    Given User perform POST operation in UserSkillMap API "/UserSkills"
    When User pass the UserSkill data from given sheetname "<SheetName>" and rownumber <RowNumber> as invalid user
    Then User should get 401 unauthorized code

    Examples: 
      | SheetName | RowNumber |
      | UserSkill |         0 |
      | UserSkill |         1 |
      | UserSkill |         2 |
      | UserSkill |         3 |

  Scenario Outline: To verify Rest Service for invalid endpoint - Get Method for UserSkillMap API
    Given User want to execute POST invalid endpoint in UserSkillMap API "/UserSkills1/"
    When User pass the UserSkill data from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should 404 notfound code
    Then User Post UserSkill url NotFound Schema validate

    Examples: 
      | SheetName | RowNumber |
      | UserSkill |         0 |
      | UserSkill |         1 |
      | UserSkill |         2 |
      | UserSkill |         3 |
