# new feature
# Tags: optional
    
Feature: ROL Parameters

Background: Setup Parameters Page and eviroment
    Given Setup Parameters Page


Scenario: Edit a Parameter as an external user
    Given External User
    When Edit a Parameter with Royalty "11.25"
    Then Verify that parameter wasnt edited
    When Edit a Parameter with Royalty "11.2"

Scenario: Edit a Parameter as an internal user
    Given Internal User
    When Edit a Parameter with Royalty "11.25"
    Then Verify that parameter was edited
    When Edit a Parameter with Royalty "11.2"




