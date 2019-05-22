# new feature
# Tags: optional
    
Feature: Season Closing

Background: set Season Closing Page
    Given set Season Closing Page


Scenario: Save, Submit, Reject, Submit and Approve a Season Closing
    Given SC_An ADMINISTRATOR
        And SC_Year "2018"
        And Headoffice without Season Closing
    When Save Season Closing
    And Submit Season Closing
    And Reject Season Closing
    And Submit Season Closing
    And Approve Season Closing
    Then Verify that the Season Closing was approved

Scenario: Save a Season Closing
    Given SC_An ADMINISTRATOR
        And SC_Year "2018"
        And Headoffice without Season Closing
    When Save Season Closing
    Then Verify that the Season Closing was saved

Scenario: Save and Submit a Season Closing
    Given SC_An ADMINISTRATOR
        And SC_Year "2018"
        And Headoffice without Season Closing
    When Save Season Closing
    And Submit Season Closing
    Then Verify that the Season Closing was submitted

Scenario: Save, Submit and Reject a Season Closing
    Given SC_An ADMINISTRATOR
        And SC_Year "2018"
        And Headoffice without Season Closing
    When Save Season Closing
    And Submit Season Closing
    And Reject Season Closing
    Then Verify that the Season Closing was rejected

Scenario: Save, Submit, Reject and Submit again a Season Closing
    Given SC_An ADMINISTRATOR
        And SC_Year "2018"
        And Headoffice without Season Closing
    When Save Season Closing
    And Submit Season Closing
    And Reject Season Closing
    And Submit Season Closing
    Then Verify that the Season Closing was submitted


