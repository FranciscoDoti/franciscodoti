# new feature
# Tags: optional
    
Feature: ROL Workflow tests

Background: Rol workflow background
    Given WF_Setup ROL Creation Page
        And WF_Setup ROL Models Page
        And Setup Workflow Page

Scenario: Reject a ROL and search it with the Approve Reject search
        Given Period without ROL for Headoffice "08707604000184"
        When WF_Create "CONSOLIDATED" ROL Model for "08707604000184"
            And WF_I load the ROL
            And WF_I create the ROL
            And WF_I load the ROL
            And WF_I submit the ROL
        Given Filter ROL by document "08707604000184"
        When Workflow search ROLs
            And I reject the ROL
        Then Verify that ROL was rejected
        Then Verify that the Approve Reject search return the approved ROL

Scenario: Approve a ROL and search it with the Approve Reject search
        Given Period without ROL for Headoffice "08707604000184"
        When WF_Create "CONSOLIDATED" ROL Model for "08707604000184"
            And WF_I load the ROL
            And WF_I create the ROL
            And WF_I load the ROL
            And WF_I submit the ROL
        Given Filter ROL by document "08707604000184"
        When Workflow search ROLs
            And I approve the ROL
        Then Verify that ROL was approved
        Then Verify that the Approve Reject search return the approved ROL

Scenario: Search a ROL that was created before from ROL Workflow search
        Given Period without ROL for Headoffice "08707604000184"
        When WF_Create "CONSOLIDATED" ROL Model for "08707604000184"
            And WF_I load the ROL
            And WF_I create the ROL
            And WF_I load the ROL
            And WF_I submit the ROL
        Given Filter ROL by document "08707604000184"
        When Workflow search ROLs
        Then Verify that Workflow search return results


#Scenario: Verify that if a ROL was approved out of the dates filtered, the results doesnt return it

#Scenario: Verify that if a ROL was rejected out of the dates filtered, the results doesnt return it

#Scenario: Verify that if a ROL was approved between the dates filtered, the results return it

#Scenario: Verify that if a ROL was rejected between the dates filtered, the results return it

#Scenario: Verify the Approve Rejected search filtering by a Affiliate Name

#Scenario: Verify the Approve Rejected search filtering by a Headoffice Name

#Scenario: Verify the Approve Rejected search filtering by a Affiliate document

#Scenario: Verify the Approve Rejected search filtering by a Headoffice document

#Scenario: Verify the Approve Rejected search filtering by one region

#Scenario: Verify the Approve Rejected search filtering by two or more regions

#Scenario: Verify the Approve Rejected search filtering a document rol who has results with the dates selected but it doesnt match with the regions filtered

#Scenario: Approve rejected search. Verify that the Excel was generated.

#Scenario: Verify the Approve Rejected search pagination with limit 5

#Scenario: Verify the Approve Rejected search pagination with limit 10

#Scenario: Verify the Approve Rejected search pagination with limit 25

#Scenario: Verify the Approve Rejected search pagination with limit 50

#Scenario: Pending requests search. Verify that the Excel was generated.

#Scenario: Verify the Pending requests search pagination with limit 5

#Scenario: Verify the Pending requests search pagination with limit 10

#Scenario: Verify the Pending requests search pagination with limit 25

#Scenario: Verify the Pending requests search pagination with limit 50


