# new feature
# Tags: optional
    
Feature: Fixed volume LOTE 4
Background: Background to get Token
    Given get Token

Scenario: Fixed volume - Massive Load Test. 1-9000 . LOTE 4
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Fixed volumes from Affiliate "18" to "23"