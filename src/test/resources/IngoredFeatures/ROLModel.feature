# new feature
# Tags: optional
    
Feature: ROL Models

Background:This is a background to setup the ROL Model Page and the enviroment
               Given Setup ROL Models Page






Scenario: Search filtering by Headoffice and User and Detailed Report Type with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
          And A User "STARK_A"
          And Detailed Report Type
    When Create "DETAILED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 5 doesnt bring more than 5 results with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "5"
    Then Verify that the ROL Model search dont return more than "5" results
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 10 doesnt bring more than 10 results with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "10"
    Then Verify that the ROL Model search dont return more than "10" results
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 25 doesnt bring more than 25 results with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "25"
    Then Verify that the ROL Model search dont return more than "25" results
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 50 doesnt bring more than 50 results with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "50"
    Then Verify that the ROL Model search dont return more than "50" results
    Then Delete ROL Model with id "77863223000107"


Scenario: Verify that in the Headoffice filter for ROL Model creation the system dont allow to enter an affiliate with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given An Affiliate Document Number "77863223009325"
    When Search For Headoffices to create ROL
    Then Verify that it couldnt search



Scenario: Search filtering by Headoffice and User and Partial Report Type with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
          And A User "STARK_A"
          And Partial Report Type
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by User with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A User "STARK_A"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
            And A User "STARK_A"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Consolidated ROL Model to a Detailed one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Edit to a "DETAILED" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Consolidated ROL Model to a Partial one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Edit to a "PARTIAL" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Detailed ROL Model to a Consolidated one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Edit to a "CONSOLIDATED" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Detailed ROL Model to a Partial one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Edit to a "PARTIAL" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Partial ROL Model to a Consolidated one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Edit to a "CONSOLIDATED" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Partial ROL Model to a Detailed one with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Edit to a "DETAILED" one
    Then Verify that it was edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a Consolidate ROL Model with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATE" ROL Model for "77863223000107"
    Then Verify that it was created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a Detailed ROL Model with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    Then Verify that it was created
    Then Delete ROL Model with id "77863223000107"


Scenario: Create a Partial ROL Model with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    Then Verify that it was created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a DETAILED ROL Model for a Headoffice that already has one created with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a CONSOLIDATED ROL Model for a Headoffice that already has one created with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a PARTIAL ROL Model for a Headoffice that already has one created with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"




Scenario: Search filtering by Headoffice and User and Consolidate Report Type with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given A headoffice "77863223000107"
          And A User "STARK_A"
          And Consolidate Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User and Consolidate Report Type and Detailed Report Type with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given A headoffice "77863223000107"
          And A User "STARK_A"
          And Consolidate Report Type
          And Detailed Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User and Consolidate Report Type and Detailed Report Type and Partial Report Type with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given A headoffice "77863223000107"
          And A User "STARK_A"
          And Consolidate Report Type
          And Detailed Report Type
          And Partial Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the search return results



Scenario: Search filtering by an inexistent user with an ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given A User "NON_USER"
    When Search ROL Models
    Then Verify that the search dont return results








 ####################################################################################################################################
  ####################################################################################################################################


Scenario: Verify that the pagination with limit 5 doesnt bring more than 5 results with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "5"
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"








Scenario: Verify that the pagination with limit 10 doesnt bring more than 10 results with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "10"
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 25 doesnt bring more than 25 results with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "25"
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Verify that the pagination with limit 50 doesnt bring more than 50 results with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When RM_Search ROL Models with limit "50"
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"


Scenario: Verify that in the Headoffice filter for ROL Model creation the system dont allow to enter an affiliate with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Given An Affiliate Document Number "77863223009325"
    When Search For Headoffices to create ROL
    Then Verify that it couldnt search


Scenario: Search filtering by Headoffice and User and Detailed Report Type with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
          And A User "STARK_P"
          And Detailed Report Type
    When Create "DETAILED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User and Partial Report Type with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
          And A User "STARK_P"
          And Partial Report Type
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by User with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A User "STARK_P"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
            And A User "STARK_P"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Consolidated ROL Model to a Detailed one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Edit to a "DETAILED" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Consolidated ROL Model to a Partial one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Edit to a "PARTIAL" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Detailed ROL Model to a Consolidated one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Edit to a "CONSOLIDATED" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Detailed ROL Model to a Partial one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Edit to a "PARTIAL" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Partial ROL Model to a Consolidated one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Edit to a "CONSOLIDATED" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Edit a Partial ROL Model to a Detailed one with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Edit to a "DETAILED" one
    Then Verify that it wasnt edited
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a Consolidate ROL Model with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATE" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"


Scenario: Create a Detailed ROL Model with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"



Scenario: Create a Partial ROL Model with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a DETAILED ROL Model for a Headoffice that already has one created with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    When Create "DETAILED" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a CONSOLIDATED ROL Model for a Headoffice that already has one created with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"

Scenario: Create a PARTIAL ROL Model for a Headoffice that already has one created with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Then Delete ROL Model with id "77863223000107"
    Given A headoffice "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    When Create "PARTIAL" ROL Model for "77863223000107"
    Then Verify that it wasnt created
    Then Delete ROL Model with id "77863223000107"




Scenario: Search filtering by Headoffice and User and Consolidate Report Type with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Given A headoffice "77863223000107"
          And A User "STARK_P"
          And Consolidate Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User and Consolidate Report Type and Detailed Report Type with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Given A headoffice "77863223000107"
          And A User "STARK_P"
          And Consolidate Report Type
          And Detailed Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search
    Then Delete ROL Model with id "77863223000107"

Scenario: Search filtering by Headoffice and User and Consolidate Report Type and Detailed Report Type and Partial Report Type with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Given A headoffice "77863223000107"
          And A User "STARK_P"
          And Consolidate Report Type
          And Detailed Report Type
          And Partial Report Type
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    When Search ROL Models
    Then Verify that the user dont have permissions to search



Scenario: Search filtering by an inexistent user with a PARTICIPANT
    Given RMod_A PARTICIPANT
    Given A User "NON_USER"
    When Search ROL Models
    Then Verify that the user dont have permissions to search

