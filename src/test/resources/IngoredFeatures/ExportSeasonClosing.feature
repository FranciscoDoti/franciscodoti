# new feature
# Tags: optional
    
Feature: Export Season Closing

Background: set Export Season Closing Page
    Given set Export Season Closing Page
    
Scenario: Export Season Closing with all filters
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
        And ESC_Filter by region "PRIMAVERA-SOJA/ICS"
        And ESC_Filter by date from "01" / "12" / "2018"
        And ESC_Filter by date to "05" / "02" / "2019"
        And ESC_Filter by status "APPROVED"
    When Export Season Closing report
    Then ESC_Verify that the report was generated

Scenario: Export Season Closing filtering by Headoffice Document, Grower, region and date
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
        And ESC_Filter by region "PRIMAVERA-SOJA/ICS"
        And ESC_Filter by date from "01" / "12" / "2018"
        And ESC_Filter by date to "05" / "02" / "2019"
    When Export Season Closing report
    Then ESC_Verify that the report was generated

Scenario: Export Season Closing filtering by Headoffice Document, Grower and region
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
        And ESC_Filter by region "PRIMAVERA-SOJA/ICS"
    When Export Season Closing report
    Then ESC_Verify that the report was generated

Scenario: Export Season Closing filtering by Headoffice Document and  Grower
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
    When Export Season Closing report
    Then ESC_Verify that the report was generated

Scenario: Export Season Closing filtering by Headoffice Document
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
    When Export Season Closing report
    Then ESC_Verify that the report was generated

Scenario: Export Season Closing filtering by Headoffice Document and several regions
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
        And ESC_Filter by region "PRIMAVERA-SOJA/ICS"
        And ESC_Filter by region "ASSIS-SOJA"
        And ESC_Filter by region "ITAPEVA-SOJA"
        And ESC_Filter by region "FORMOSA-SOJA/ICS"
    When Export Season Closing report
    Then ESC_Verify that the report was generated


Scenario: Export Season Closing filtering by Headoffice Document, several regions and several status
    Given ESC_An ADMINISTRATOR
        And ESC_Filter by Year "2018"
        And ESC_Filter by Headoffice document "11636261000119"
        And ESC_Filter by Grower document "59677015915"
        And ESC_Filter by region "PRIMAVERA-SOJA/ICS"
        And ESC_Filter by region "ASSIS-SOJA"
        And ESC_Filter by region "ITAPEVA-SOJA"
        And ESC_Filter by region "FORMOSA-SOJA/ICS"
        And ESC_Filter by status "APPROVED"
        And ESC_Filter by status "REGISTERED"
        And ESC_Filter by status "SUBMITTED"
        And ESC_Filter by status "REJECTED"
    When Export Season Closing report
    Then ESC_Verify that the report was generated