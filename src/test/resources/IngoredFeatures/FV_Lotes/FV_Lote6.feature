# new feature
# Tags: optional
    
Feature: Fixed volume LOTE 6
Background: Background to get Token
    Given get Token

Scenario: Fixed volume - Massive Load Test. LOTE 6
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Fixed volumes from Affiliate "30" to "35"