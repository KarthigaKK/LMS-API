Feature: To verify API automation with rest assured

  Scenario Outline: To verify DB Validation - Put Method
    Given Skill User want to execute PUT operation for "/Skills" for DB Validation
    When Skill User submit the PUT request in SQL DB
    Then Data base validation falied due to invalid database credentials
