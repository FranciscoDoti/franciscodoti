# new feature
# Tags: optional
    
Feature: ROL Export

    Background: Set Rol Export Page
        Given set Rol Export Page


Scenario: Export Rol with several status and several regions and ADMINISTRATOR
    Given RE_An ADMINISTRATOR
        And RE_Filter Date From "1"/"2018"
        And RE_Filter Date To "2"/"2019"
        And RE_Filter Headoffice Document "77863223000107"
        And RE_Affiliate Document "77863223000107"
        And RE_Filter Region "ALFENAS-SOJA"
        And RE_Filter Region "PRIMAVERA-SOJA/ICS"
        And RE_Filter Region "SINOP-SOJA"
        And RE_Filter Region "MARINGA-SOJA"
        And RE_Filter Region "CHAPECO-SOJA"
        And RE_Filter Status "REGISTERED"
        And RE_Filter Status "NOT REGISTERED"
        And RE_Filter Status "APPROVED"
        And RE_Filter Status "BILLED"
    When RE_Download Rol
    Then RE_Verify that rol was downloaded


Scenario: Export Rol with several status and ADMINISTRATOR
    Given RE_An ADMINISTRATOR
        And RE_Filter Date From "1"/"2018"
        And RE_Filter Date To "2"/"2019"
        And RE_Filter Headoffice Document "77863223000107"
        And RE_Affiliate Document "77863223000107"
        And RE_Filter Region "ALFENAS-SOJA"
        And RE_Filter Status "REGISTERED"
        And RE_Filter Status "NOT REGISTERED"
        And RE_Filter Status "APPROVED"
        And RE_Filter Status "BILLED"
    When RE_Download Rol
    Then RE_Verify that rol was downloaded

Scenario: Export Rol with all filters and ADMINISTRATOR
    Given RE_An ADMINISTRATOR
        And RE_Filter Date From "1"/"2018"
        And RE_Filter Date To "2"/"2019"
        And RE_Filter Headoffice Document "77863223000107"
        And RE_Affiliate Document "77863223000107"
        And RE_Filter Region "ALFENAS-SOJA"
        And RE_Filter Status "REGISTERED"
    When RE_Download Rol
    Then RE_Verify that rol was downloaded

Scenario: Export Rol with all filters and PARTICIPANT
    Given RE_A PARTICIPANT
        And RE_Filter Date From "1"/"2018"
        And RE_Filter Date To "2"/"2019"
        And RE_Filter Headoffice Document "77863223000107"
        And RE_Affiliate Document "77863223000107"
        And RE_Filter Region "ALFENAS-SOJA"
        And RE_Filter Status "REGISTERED"
    When RE_Download Rol
    Then RE_Verify that the user dont have permissions


Scenario: Export Rol with several regions and ADMINISTRATOR
    Given RE_An ADMINISTRATOR
        And RE_Filter Date From "1"/"2018"
        And RE_Filter Date To "2"/"2019"
        And RE_Filter Region "ALFENAS-SOJA"
        And RE_Filter Region "PRIMAVERA-SOJA/ICS"
        And RE_Filter Region "SINOP-SOJA"
        And RE_Filter Region "MARINGA-SOJA"
        And RE_Filter Region "CHAPECO-SOJA"
    When RE_Download Rol
    Then RE_Verify that rol was downloaded



