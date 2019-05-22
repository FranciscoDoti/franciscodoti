# new feature
# Tags: optional
    
Feature: ROL History

Background: Background to set the ROL History Page
    Given setup ROL History Page






Scenario: Generate Report with filter results filtering by Headoffice with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice, Period and Status Registered with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
            And Filter status "REGISTERED"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice,Affiliate Period and Status Registered with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
            And Filter Affiliate by doc "77752293007362"
            And Filter status "REGISTERED"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice and Period with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice and Affiliate with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice, Affiliate and Period with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And Filter period year "2018"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated

Scenario: Generate Report with filter results filtering by Headoffice and status Registered with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
            And Filter status "REGISTERED"
        And RH_An ADMINISTRATOR
    When Generate Report
    Then Verify that the report was generated



Scenario: Search ROLs filtering by Headoffice, Affiliate and Status Registered with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And Filter status "REGISTERED"
        And RH_An ADMINISTRATOR
    When Search ROLs
    Then RH_Verify that it couldnt search cause of the year is mandatory

Scenario: Search ROLs filtering by Headoffice, Affiliate with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And RH_An ADMINISTRATOR
    When Search ROLs
    Then RH_Verify that it couldnt search cause of the year is mandatory


Scenario: Search ROLs filtering by Headoffice with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And RH_An ADMINISTRATOR
    When Search ROLs
    Then RH_Verify that it couldnt search cause of the year is mandatory



Scenario: Search ROLs filtering by Headoffice and status with an ADMINISTRATOR
    Given Filter Headoffice by document "77752293000198"
        And Filter status "REGISTERED"
        And RH_An ADMINISTRATOR
    When Search ROLs
    Then RH_Verify that it couldnt search cause of the year is mandatory


Scenario: Generate Report with filter results filtering by Headoffice with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice, Period and Status Registered with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
            And Filter status "REGISTERED"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice,Affiliate Period and Status Registered with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
            And Filter Affiliate by doc "77752293007362"
            And Filter status "REGISTERED"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice and Period with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
            And Filter period year "2018"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice and Affiliate with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice, Affiliate and Period with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And Filter period year "2018"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Generate Report with filter results filtering by Headoffice and status Registered with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
            And Filter status "REGISTERED"
        And RH_A PARTICIPANT
    When Generate Report
    Then RH_Verify that the user dont have permissions

Scenario: Search ROLs filtering by Headoffice, Period and Status Registered with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter period year "2018"
        And Filter status "REGISTERED"
        And RH_A PARTICIPANT
    When Search ROLs
    Then RH_Verify that the user dont have permissions

Scenario: Search ROLs filtering by Headoffice, Affiliate, Period, and Status Registered with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And Filter period year "2018"
        And Filter status "REGISTERED"
        And RH_A PARTICIPANT
    When Search ROLs
    Then RH_Verify that the user dont have permissions


Scenario: Search ROLs filtering by Headoffice, Affiliate with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And RH_A PARTICIPANT
        And Filter period year "2018"
    When Search ROLs
    Then RH_Verify that the user dont have permissions

Scenario: Search ROLs filtering by Headoffice, Affiliate and Period with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter Affiliate by doc "77752293007362"
        And Filter period year "2018"
        And RH_A PARTICIPANT
    When Search ROLs
    Then RH_Verify that the user dont have permissions

Scenario: Search ROLs filtering by Headoffice with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And RH_A PARTICIPANT
        And Filter period year "2018"
    When Search ROLs
    Then RH_Verify that the user dont have permissions

Scenario: Search ROLs filtering by Headoffice and Period with a PARTICIPANT
    Given Filter Headoffice by document "77752293000198"
        And Filter period year "2018"
        And RH_A PARTICIPANT
    When Search ROLs
    Then RH_Verify that the user dont have permissions


