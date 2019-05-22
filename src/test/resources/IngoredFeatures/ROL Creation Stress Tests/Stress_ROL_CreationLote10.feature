# new feature
# Tags: optional
    
Feature: ROL Creation Stress Testing

Background: get Token
    Given get Token
    
Scenario: Create a ROL for each month of 2018 for all the affiliates from a specific Headoffice
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Create ROL from affiliate "90" to "98" of each month from "2018"